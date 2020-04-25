import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MancalaFrame extends JFrame implements ChangeListener {
    private final DataModel dataModel;

    public MancalaFrame(DataModel dataModel) {
        this.dataModel = dataModel;

        setPreferredSize(new Dimension(600, 400));
        setLocation(0, 0);
        setTitle("Mancala");
        setLayout(new BorderLayout());

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
