import javax.swing.*;
import java.util.Scanner;

/**
 * This class is used to test the Mancala program and its features.
 *
 * @author Daniel Saneel, Jay Tat, Jennifer Nguyen
 */
public class MancalaTest {
    public static void main(String[] args) {
        // display startup screen and ask user to enter starting number of stones and select graphical style
        int numStones = Integer.parseInt(JOptionPane.showInputDialog("Enter starting number of stones in each pit"));

        BoardStyle[] styles = { new ChristmasStyle(), new DarkStyle() };
        BoardStyle selectedStyle = (BoardStyle) JOptionPane.showInputDialog(null,
                "Select a graphical style", "Input", JOptionPane.INFORMATION_MESSAGE, null,
                styles, styles[0]);

        DataModel dataModel = new DataModel(numStones);
        MancalaFrame mancalaFrame = new MancalaFrame(dataModel, selectedStyle);
        dataModel.attach(mancalaFrame);
    }
}