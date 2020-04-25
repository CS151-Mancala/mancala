import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class DataModel {
    private static final int NUM_PITS = 12; // default number of pits in board

    private final ArrayList<Pit> pits;
    private Pit aMancala; // mancala for player A
    private Pit bMancala; // mancala for player B

    private final ArrayList<ChangeListener> listeners;

    public DataModel(int numStones) {
        pits = new ArrayList<>();
        for(int i = 0; i < NUM_PITS; i++) {
            // The first (NUM_PITS / 2) pits are for player A
            if(i < (NUM_PITS / 2)) {
                pits.add(new Pit("A", numStones));
            } else {
                pits.add(new Pit("B", numStones));
            }
        }
        aMancala = new Pit("A", 0);
        bMancala = new Pit("B", 0);
        this.listeners = new ArrayList<>();
    }

    public void attach(ChangeListener listener) {
        listeners.add(listener);
    }
}
