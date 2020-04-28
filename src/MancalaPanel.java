import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MancalaPanel extends JPanel implements ChangeListener {
    private final GameBoard gameBoard;

    public MancalaPanel(DataModel dataModel) {
        gameBoard = new GameBoard(dataModel);
    }

    /**
     * Set the size of the window to the size of the board
     * @return the size of the Mancala board
     */
    @Override
    public Dimension getPreferredSize() {
        return gameBoard.getSize();
    }

    /**
     * Draw the board and stones on the screen
     * @param g frame Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        gameBoard.drawGameBoard(g2);
        gameBoard.drawStones(g2);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
