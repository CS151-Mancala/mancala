import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaFrame extends JFrame implements ChangeListener {
    public MancalaFrame(DataModel dataModel) {

        setTitle("Mancala");
        add(new MancalaPanel(dataModel));

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        repaint();
    }
}
