import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Data model for the Mancala program
 */
public class DataModel {
    private static final int NUM_PITS = 6; // number of Pits for one player

    private final ArrayList<Pit> pits;
    private final Mancala aMancala; // mancala for player A
    private final Mancala bMancala; // mancala for player B

    private final ArrayList<ChangeListener> listeners;

    /**
     * Constructor for the DataModel
     * @param numStones starting number of stones in each Pit
     */
    public DataModel(int numStones) {
        pits = new ArrayList<>();
        for(int i = 0; i < NUM_PITS * 2; i++) {
            // The first (NUM_PITS / 2) pits are for player A
            if(i < (NUM_PITS)) {
                pits.add(new Pit("A", (i % 6) + 1, numStones));
            } else {
                pits.add(new Pit("B", (i % 6) + 1, numStones));
            }
        }
        aMancala = new Mancala("A", 0);
        bMancala = new Mancala("B", 0);
        this.listeners = new ArrayList<>();
    }

    public ArrayList<Pit> getPits() {
        return pits;
    }

    /**
     * Attaches a ChangeListener to the DataModel
     * @param listener the ChangeListener to attach
     */
    public void attach(ChangeListener listener) {
        listeners.add(listener);
    }

    public void updateBoard(String pitString) {
        int counter = 0;
        // Find the Pit specified by pitString
        for(int i = 0; i < NUM_PITS * 2; i++) {
            Pit pit = pits.get(i);
            if((pit.getPlayer() + pit.getID()).equals(pitString)) {
                counter = pit.getNumStones();
                pit.setNumStones(0);
            } else if(counter > 0) {
                pit.setNumStones(pit.getNumStones() + 1);
                counter--;
                System.out.println(pit);
                // Check if a Mancala is the next hole
                if((i + 1) % 6 == 0) {
                    if(pit.getPlayer().equals("A")) {
                        aMancala.setNumStones(pit.getNumStones() + 1);
                        System.out.println(aMancala);
                    } else {
                        bMancala.setNumStones(pit.getNumStones() + 1);
                        i = -1; // Reset index if Player B's Mancala is reached
                        System.out.println(bMancala);
                    }
                    counter--;
                }
            }
        }
    }
}
