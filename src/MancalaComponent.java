import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MancalaComponent extends JComponent implements ChangeListener {
    private static final int MANCALA_WIDTH = 100;
    private static final int MANCALA_HEIGHT = 200;

    private static final int STONE_WIDTH = 10;
    private static final int STONE_HEIGHT = 10;
    private static final int STONE_MARGIN = 3; // margin between each stone

    private final String player;
    private int numStones;
    private final Dimension dimension;

    public MancalaComponent(String player, int numStones, DataModel dataModel) {
        this.player = player;
        this.numStones = numStones;
        dataModel.attach(this);
        dimension = new Dimension(MANCALA_WIDTH, MANCALA_HEIGHT);
    }

    public String getPlayer() {
        return player;
    }

    public int getNumStones() {
        return numStones;
    }

    public void setNumStones(int numStones) {
        this.numStones = numStones;
    }

    public Dimension getPreferredSize() {
        return dimension;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        Ellipse2D pit = new Ellipse2D.Double(0,0, MANCALA_WIDTH, MANCALA_HEIGHT);
        double centerX = pit.getCenterX() - (STONE_WIDTH / 2);
        double centerY = pit.getCenterY() / 4;
        g2.draw(pit);

        // Draws the stones in each pit
        for(int i = 0; i < numStones; i++) {
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