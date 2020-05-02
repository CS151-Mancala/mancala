import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Data model for the Mancala program
 */
public class DataModel {
    private static final int NUM_PLAYERS = 2; // number of players
    private static final int NUM_PITS = 6; // number of pits per player

    private final PitComponent[][] pits;
    private final MancalaComponent mancalaA; // mancala for player A
    private final MancalaComponent mancalaB; // mancala for player B
    private String turn; // tracks which player's turn it is (A or B)
    private String previousTurn;

    private final ArrayList<ChangeListener> listeners;

    /**
     * Constructor for the DataModel
     * @param numStones starting number of stones in each Pit
     */
    public DataModel(int numStones) {
        this.listeners = new ArrayList<>();
        pits = new PitComponent[NUM_PLAYERS][NUM_PITS];
        mancalaA = new MancalaComponent("A", 0, this);
        mancalaB = new MancalaComponent("B", 0, this);
        for(int i = 0; i < NUM_PITS; i++) {
            pits[0][i] = new PitComponent("A", (i % NUM_PITS) + 1, numStones, this);
            pits[1][i] = new PitComponent("B", (i % NUM_PITS) + 1, numStones, this);
        }
        turn = "A";
        previousTurn = turn;
    }

    /**
     * Retrieves the 2D array of pits
     * @return the 2D array of PitComponent objects
     */
    public PitComponent[][] getPits() {
        return pits;
    }

    /**
     * Retrieves the mancala for player A
     * @return MancalaComponent for player A
     */
    public MancalaComponent getMancalaA() {
        return mancalaA;
    }

    /**
     * Retrieves the mancala for player B
     * @return MancalaComponent for player B
     */
    public MancalaComponent getMancalaB() {
        return mancalaB;
    }

    /**
     * Retrieves the current turn
     * @return String representing which player's turn it is
     */
    public String getTurn() {
        return turn;
    }

    /**
     * Attaches a ChangeListener to the DataModel
     * @param listener the ChangeListener to attach
     */
    public void attach(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Updates the game whenever a player makes a turn
     * @param selectedPit the pit the player selected
     */
    public void update(PitComponent selectedPit) {
        // validate turn order
        if (!turn.equals(selectedPit.getPlayer())) {
            return;
        }
        // iterate through the 2D list and keep track of current number of stones and turn
        for(int i = 0; i < NUM_PLAYERS; i++) {
            for(int j = 0; j < NUM_PITS; j++) {
                pits[i][j].setPreviousNumStones(pits[i][j].getNumStones());
            }
        }
        mancalaA.setPreviousNumStones(mancalaA.getNumStones());
        mancalaB.setPreviousNumStones(mancalaB.getNumStones());
        previousTurn = turn;

        int counter = 0;
        // find the pit that was selected and update the other pits accordingly
        boolean found = false;
        // tracks whether a player's last stone was dropped in a mancala
        boolean lastStoneInMancala = false;
        for (int i = 0; i < NUM_PLAYERS; i++) {
            for (int j = 0; j < NUM_PITS; j++) {
                PitComponent pit = pits[i][j];
                if (pit == selectedPit && !found) {
                    counter = pit.getNumStones();
                    pit.setNumStones(0);
                    found = true;
                } else if (counter > 0) {
                    pit.setNumStones(pit.getNumStones() + 1);
                    counter--;
                } else if (found) {
                    break;
                }
                // check if a mancala is the next hole
                if (j == NUM_PITS - 1 && counter > 0) {
                    if (pit.getPlayer().equals("A")) {
                        mancalaA.setNumStones(mancalaA.getNumStones() + 1);
                    } else {
                        mancalaB.setNumStones(mancalaB.getNumStones() + 1);
                        // reset indices if player B's mancala is reached
                        i = 0;
                        j = -1;
                    }
                    counter--;
                    // check if last stone was dropped in a mancala
                    if(counter == 0) {
                        lastStoneInMancala = true;
                    }
                }
            }
        }

        // switch turns
        if(turn.equals("A") && !lastStoneInMancala) {
            turn = "B";
        } else if(!lastStoneInMancala) {
            turn = "A";
        }
        notifyListeners();
    }

    /**
     * Reverts to the previous turn
     */
    public void undoTurn() {
        // Change all pits' number of stones to their previous numbers
        for(int i = 0; i < NUM_PLAYERS; i++) {
            for(int j = 0; j < NUM_PITS; j++) {
                pits[i][j].revertStones();
            }
        }
        mancalaA.revertStones();
        mancalaB.revertStones();

        // Change current turn back to the previous player
        turn = previousTurn;

        notifyListeners();
    }

    /**
     * Notify each ChangeListener to change state
     */
    private void notifyListeners() {
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }
}