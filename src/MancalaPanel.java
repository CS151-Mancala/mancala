import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the panel displaying the mancala game board
 */
public class MancalaPanel extends JPanel implements ChangeListener {
    private static final int NUM_PLAYERS = 2; // number of players
    private static final int NUM_PITS = 6; // number of pits per player

    private final DataModel dataModel;
    private final BoardStyle style;

    private JPanel gameBoard;
    private final JPanel[][] pitPanels = new JPanel[NUM_PLAYERS][NUM_PITS];

    private final JPanel mancalaAPanel = new JPanel(new BorderLayout());
    private final JPanel mancalaBPanel = new JPanel(new BorderLayout());

    private final JLabel mancalaALabel = new JLabel();
    private final JLabel mancalaBLabel = new JLabel();

    private final JLabel turnLabel = new JLabel();

    /**
     * Constructor for a MancalaPanel object
     * @param dataModel the DataModel object
     */
    public MancalaPanel(DataModel dataModel, BoardStyle style) {
        this.dataModel = dataModel;
        dataModel.attach(this);

        this.style = style;
        setLayout(new BorderLayout());

        // add a label to indicate whose turn it is
        turnLabel.setText("Current Turn: " + dataModel.getTurn());
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel, BorderLayout.NORTH);

        // create the game board with pits and mancalas for each player
        gameBoard = createGameBoard();
        addComponents();
        add(gameBoard, BorderLayout.CENTER);

        // add button to revert to the previous turn
        JButton undoButton = new JButton("Undo Turn");
        undoButton.addActionListener(e -> dataModel.undoTurn());
        add(undoButton, BorderLayout.SOUTH);
    }

    /**
     * Creates the game board as a JPanel object
     * @return the JPanel object storing the components for the game board
     */
    private JPanel createGameBoard() {
        gameBoard = new JPanel(new BorderLayout());
        JPanel pitsGrid = new JPanel(new GridLayout(NUM_PLAYERS, NUM_PITS));
        // create pits for each player
        for(int i = 0; i < NUM_PLAYERS; i++) {
            for(int j = 0; j < NUM_PITS; j++) {
                pitPanels[i][j] = new JPanel(new BorderLayout());
                style.stylize(pitPanels[i][j]);
                pitsGrid.add(pitPanels[i][j]);
            }
        }
        gameBoard.add(pitsGrid, BorderLayout.CENTER);

        // create mancalas for players A and B
        style.stylize(mancalaAPanel);
        add(mancalaAPanel, BorderLayout.EAST);
        style.stylize(mancalaBPanel);
        add(mancalaBPanel, BorderLayout.WEST);
        gameBoard.add(mancalaAPanel, BorderLayout.EAST);
        gameBoard.add(mancalaBPanel, BorderLayout.WEST);

        return gameBoard;
    }

    /**
     * Adds various components to the game board
     */
    private void addComponents() {
        PitComponent[][] pits = dataModel.getPits();

        // add PitComponent objects to the JPanels
        for(int i = 0; i < NUM_PITS; i++) {
            // add player A's pits
            PitComponent aPit = pits[0][i];
            aPit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dataModel.update((PitComponent) e.getSource());
                }
            });
            pitPanels[1][i].add(aPit, BorderLayout.NORTH);
            JLabel aLabel = new JLabel(aPit.getPlayer() + aPit.getID());
            style.stylize(aLabel);
            aLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pitPanels[1][i].add(aLabel, BorderLayout.SOUTH);

            // add player B's pits
            PitComponent bPit = pits[1][i];
            bPit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dataModel.update((PitComponent) e.getSource());
                }
            });
            pitPanels[0][NUM_PITS-i-1].add(bPit, BorderLayout.SOUTH);
            JLabel bLabel = new JLabel(bPit.getPlayer() + bPit.getID());
            style.stylize(bLabel);
            bLabel.setHorizontalAlignment(SwingConstants.CENTER);
            pitPanels[0][NUM_PITS-i-1].add(bLabel, BorderLayout.NORTH);
        }

        // add mancalas
        mancalaAPanel.add(dataModel.getMancalaA(), BorderLayout.SOUTH);
        mancalaALabel.setText("Mancala A");
        style.stylize(mancalaALabel);
        mancalaALabel.setHorizontalAlignment(SwingConstants.CENTER);
        mancalaAPanel.add(mancalaALabel, BorderLayout.NORTH);

        mancalaBPanel.add(dataModel.getMancalaB(), BorderLayout.SOUTH);
        mancalaBLabel.setText("Mancala B");
        style.stylize(mancalaBLabel);
        mancalaBLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mancalaBPanel.add(mancalaBLabel, BorderLayout.NORTH);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        turnLabel.setText("Current Turn: " + dataModel.getTurn());
        revalidate();
    }
}