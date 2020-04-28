import java.util.Scanner;

/**
 * This class is used to test the Mancala program and its features.
 *
 * @author Daniel Saneel, Jay Tat, Jennifer Nguyen
 */
public class MancalaTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the starting number of stones in each pit: ");
        int numStones = input.nextInt();

        DataModel dataModel = new DataModel(numStones);
        MancalaFrame mancalaFrame = new MancalaFrame(dataModel);
        dataModel.attach(mancalaFrame);

        dataModel.updateBoard("B5");
    }
}
