import javax.swing.*;
import java.awt.*;

/**
 * Represents a dark style with predominantly dark colors
 */
public class DarkStyle implements BoardStyle {
    /**
     * Styles the given JComponent
     * @param component the JComponent to stylize
     */
    @Override
    public void stylize(JComponent component) {
        component.setBackground(Color.BLACK);
        component.setForeground(Color.WHITE);
    }

    /**
     * Retrieves the DarkStyle object as a string
     * @return String of the DarkStyle object
     */
    public String toString() {
        return "Dark Style";
    }
}
