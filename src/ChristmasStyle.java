import javax.swing.*;
import java.awt.*;

/**
 * Represents a Christmas graphical style with green and red colors
 */
public class ChristmasStyle implements BoardStyle {
    private static final Color DARK_GREEN = new Color(0, 100, 0);

    /**
     * Styles the given JComponent
     * @param component the JComponent to stylize
     */
    @Override
    public void stylize(JComponent component) {
        component.setBackground(DARK_GREEN);
        component.setForeground(Color.RED);
    }

    /**
     * Retrieves the ChristmasStyle object as a string
     * @return String of the ChristmasStyle object
     */
    public String toString() {
        return "Christmas Style";
    }
}
