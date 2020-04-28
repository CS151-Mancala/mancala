import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private static final int NUM_PITS = 6; // number of Pits for one player

    private static final int OUTER_MARGIN = 10;
    private static final int INNER_MARGIN = 20;

    private static final int PIT_WIDTH = 75;
    private static final int PIT_HEIGHT = 75;

    private static final int MANCALA_WIDTH = 100;
    private static final int MANCALA_HEIGHT = 200;

    private static final int ARC = 50; // arc for rounded rectangles

    private DataModel dataModel;

    public GameBoard(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * Draw a row of Pits
     * @param g2 the Graphics2D object
     * @param x initial x-coordinate
     * @param y initial y-coordinate
     */
    public void drawPits(Graphics2D g2, int x, int y) {
        for (int i = 0; i < NUM_PITS; i++) {
            g2.drawOval(x, y, PIT_WIDTH, PIT_HEIGHT);
            x += PIT_WIDTH + OUTER_MARGIN;
        }
    }

    /**
     * Draw the Mancalas for each player
     * @param g2 the Graphics2D object
     */
    public void drawMancalas(Graphics2D g2) {
        // Draw Mancala for Player A
        // g2.setColor(getCurrentPlayerColor());
        g2.drawRoundRect(OUTER_MARGIN, OUTER_MARGIN, MANCALA_WIDTH,
                MANCALA_HEIGHT, ARC, ARC);

        // Draw Mancala for Player B
        int x = OUTER_MARGIN + MANCALA_WIDTH + (NUM_PITS) * (INNER_MARGIN + PIT_WIDTH);

        // g2.setColor(getOtherPlayerColor());
        g2.drawRoundRect(x, OUTER_MARGIN, MANCALA_WIDTH,
                MANCALA_HEIGHT, ARC, ARC);
    }

    /**
     * Draw the GameBoard's Pits and Mancalas
     * @param g2 the Graphics2D object
     */
    public void drawGameBoard(Graphics2D g2) {
        int x = MANCALA_WIDTH + (INNER_MARGIN * 2);

        // g.setColor(getCurrentPlayerColor());
        drawPits(g2, x, OUTER_MARGIN);

        // g.setColor(getOtherPlayerColor());
        drawPits(g2, x, OUTER_MARGIN + PIT_HEIGHT + INNER_MARGIN);

        drawMancalas(g2);
    }

    /**
     * Draw the stones in each Pit
     * @param g2 the Graphics2D object
     */
    public void drawStones(Graphics2D g2) {
        ArrayList<Pit> pits = dataModel.getPits();
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 36));
        for (int i = 0; i < NUM_PITS * 2; i++) {
            Pit pit = pits.get(i);
            g2.drawString(Integer.toString(pit.getNumStones()), getPitX(i), getPitY(i));
        }
    }

    /**
     * Get x-coordinate of a Pit
     * @param n number of the Pit
     * @return x-coordinate of a Pit
     */
    public int getPitX(int n) {
        int x = 0;
        // Account for the reversal in the top row
        if (n >= NUM_PITS) {
            n = -n + (NUM_PITS * 2);
            x -= PIT_WIDTH;
        } else {
            x += PIT_WIDTH * 0.15;
        }
        // Add margin and width of Mancala to x-coordinate
        x += OUTER_MARGIN + MANCALA_WIDTH;

        // Add padding for each Pit
        x += (n + 1) * OUTER_MARGIN;

        // Add widths for each Pit
        x += n * PIT_WIDTH;

        return x + (PIT_WIDTH / 2);
    }

    /**
     * Get y-coordinate of a Pit
     * @param n number of the Pit
     * @return y-coordinate of a Pit
     */
    public int getPitY(int n) {
        int y = 0;
        // Check if Pit is in bottom row
        if (n < NUM_PITS) {
            y += OUTER_MARGIN * 3 + PIT_HEIGHT;
        } else {
            y += OUTER_MARGIN;
        }
        return (int) (y + (PIT_HEIGHT / 1.5));
    }

    /**
     * Get the size of the GameBoard based on number of Pits, widths, and margins
     * @return the Dimension of the GameBoard
     */
    public Dimension getSize() {
        int width = (NUM_PITS) * (PIT_WIDTH + INNER_MARGIN) + 2 * (MANCALA_WIDTH + OUTER_MARGIN);
        int height = (int) (2.5 * (OUTER_MARGIN + PIT_HEIGHT) + INNER_MARGIN);
        return new Dimension(width, height);
    }
}
