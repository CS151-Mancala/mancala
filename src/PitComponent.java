import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a pit on the game board
 */
public class PitComponent extends MancalaComponent implements ChangeListener {
    private static final int PIT_WIDTH = 100;
    private static final int PIT_HEIGHT = 100;

    private static final int STONE_WIDTH = 10;
    private static final int STONE_HEIGHT = 10;
    private static final int STONE_MARGIN = 3; // margin between each stone

    private final int id;
    private final Dimension dimension;

    /**
     * Constructor for a PitComponent object
     * @param player the player the PitComponent is assigned to
     * @param id the ID of the PitComponent (from 1 to 6)
     * @param numStones the number of stones in the pit
     * @param dataModel the DataModel object
     */
    public PitComponent(String player, int id, int numStones, DataModel dataModel) {
        super(player, numStones, dataModel);
        this.id = id;
        dimension = new Dimension(PIT_WIDTH, PIT_HEIGHT);
    }

    /**
     * Retrieves the ID of the pit (from 1 to 6)
     * @return the pit ID
     */
    public int getID() { return id; }

    /**
     * Retrieves the string representation of the pit
     * @return pit as a String
     */
    public String toString() {
        return getPlayer() + id;
    }

    /**
     * Retrieves the preferred size of the pit
     * @return the Dimension object of the pit
     */
    public Dimension getPreferredSize() {
        return dimension;
    }

    /**
     * Draws the pit with the stones inside
     * @param g the Graphics object
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        // draws the pit as an Ellipse2D object
        g2.setColor(getForeground());
        Ellipse2D pit = new Ellipse2D.Double(0,0, PIT_WIDTH, PIT_HEIGHT);
        g2.draw(pit);

        // draws the stones in each pit
        double centerX = pit.getCenterX() - (STONE_WIDTH / 2);
        double centerY = pit.getCenterY() / 4;
        for(int i = 0; i < getNumStones(); i++) {
            Ellipse2D marble = new Ellipse2D.Double(centerX, centerY + (i * STONE_MARGIN),
                    STONE_WIDTH, STONE_HEIGHT);
            g2.fill(marble);
            g2.draw(marble);
            centerY = centerY + STONE_HEIGHT;
        }
    }

    /**
     * Repaints the pit whenever the state is changed
     * @param e the ChangeEvent object
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}