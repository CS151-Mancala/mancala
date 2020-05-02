import javafx.scene.paint.Color;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Represents the frame displaying the mancala game
 */
public class MancalaFrame extends JFrame implements ChangeListener {

    /**
     * Constructor for a MancalaFrame object
     * @param dataModel the DataModel object
     */
    public MancalaFrame(DataModel dataModel, BoardStyle style) {
        setTitle("Mancala");
        add(new MancalaPanel(dataModel, style));

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Revalidates the frame whenever the state is changed
     * @param e the ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        revalidate();
    }
}