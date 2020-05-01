import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PitComponent extends MancalaComponent implements ChangeListener {
    private static final int PIT_WIDTH = 100;
    private static final int PIT_HEIGHT = 100;

    private static final int STONE_WIDTH = 10;
    private static final int STONE_HEIGHT = 10;
    private static final int STONE_MARGIN = 3; // margin between each stone

    private final int id;
    private final Dimension dimension;
    
    public PitComponent(String player, int id, int numStones, DataModel dataModel) {
        super(player, numStones, dataModel);
        this.id = id;
        dimension = new Dimension(PIT_WIDTH, PIT_HEIGHT);
    }

    public int getID() { return id; }

    public String toString() {
        return getPlayer() + id;
    }

    public Dimension getPreferredSize() {
        return dimension;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        Ellipse2D pit = new Ellipse2D.Double(0,0, PIT_WIDTH, PIT_HEIGHT);
        double centerX = pit.getCenterX() - (STONE_WIDTH / 2);
        double centerY = pit.getCenterY() / 4;
        g2.draw(pit);

        // Draws the stones in each pit
        for(int i = 0; i < getNumStones(); i++) {
            Ellipse2D marble = new Ellipse2D.Double(centerX, centerY + (i * STONE_MARGIN),
                    STONE_WIDTH, STONE_HEIGHT);
            g2.draw(marble);
            g2.setColor(Color.black);
            g2.fill(marble);
            centerY = centerY + STONE_HEIGHT;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}