import javax.swing.*;
import java.awt.*;

/**
 * Represents a Christmas graphical style with green and red colors
 */
public class ChristmasStyle implements BoardStyle {
    /**
     * Styles the given JComponent
     * @param component the JComponent to stylize
     */
    @Override
    public void stylize(JComponent component) {
        component.setBackground(Color.GREEN.darker().darker());
        component.setForeground(Color.RED.brighter().brighter());
    }

    /**
     * Retrieves the ChristmasStyle object as a string
     * @return String of the ChristmasStyle object
     */
    public String toString() {
        return "Christmas Style";
    }
}
