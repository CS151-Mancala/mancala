import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a mancala on the game board
 */
public class MancalaComponent extends JComponent implements ChangeListener {
    private static final int MANCALA_WIDTH = 100;
    private static final int MANCALA_HEIGHT = 200;

    private static final int STONE_WIDTH = 10;
    private static final int STONE_HEIGHT = 10;
    private static final int STONE_MARGIN = 3; // margin between each stone

    private final String player;
    private int numStones;
    private int previousNumStones;
    private final Dimension dimension;

    /**
     * Constructor for a MancalaComponent object
     * @param player the player the mancala is assigned to
     * @param numStones the number of stones in the mancala
     * @param dataModel the DataModel object
     */
    public MancalaComponent(String player, int numStones, DataModel dataModel) {
        this.player = player;
        this.numStones = numStones;
        previousNumStones = this.numStones;
        dataModel.attach(this);
        dimension = new Dimension(MANCALA_WIDTH, MANCALA_HEIGHT);
    }

    /**
     * Retrieves the player the mancala is assigned to
     * @return String of the player (either A or B)
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Retrieves the number of stones in the mancala
     * @return the number of stones as an integer
     */
    public int getNumStones() {
        return numStones;
    }

    /**
     * Sets the number of stones in the mancala
     * @param numStones the new number of stones
     */
    public void setNumStones(int numStones) {
        this.numStones = numStones;
    }

    /**
     * Sets the previous number of stones in the mancala
     * @param previousNumStones the new previous number of stones
     */
    public void setPreviousNumStones(int previousNumStones) {
        this.previousNumStones = previousNumStones;
    }

    /**
     * Reverts the number of stones to the previous number
     */
    public void revertStones() {
        numStones = previousNumStones;
    }

    /**
     * Retrieves the preferred size of the mancala
     * @return the Dimension object of the mancala
     */
    public Dimension getPreferredSize() {
        return dimension;
    }

    /**
     * Draws the mancala with the stones inside
     * @param g the Graphics object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(getForeground());
        Ellipse2D pit = new Ellipse2D.Double(0,0, MANCALA_WIDTH, MANCALA_HEIGHT);
        g2.draw(pit);

        // Draws the stones in each pit
        double centerX = pit.getCenterX() - (STONE_WIDTH / 2);
        double centerY = pit.getCenterY() / 4;
        for(int i = 0; i < numStones; i++) {
            Ellipse2D marble = new Ellipse2D.Double(centerX, centerY + (i * STONE_MARGIN),
                    STONE_WIDTH, STONE_HEIGHT);
            g2.fill(marble);
            g2.draw(marble);
            centerY = centerY + STONE_HEIGHT;
        }
    }

    /**
     * Repaints the mancala whenever the state is changed
     * @param e the ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}