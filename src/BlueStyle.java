import javax.swing.*;
import java.awt.*;

/**
 * Represents a blue graphical style with predominantly blue colors
 */
public class BlueStyle implements BoardStyle {
    private static final Color DARK_TURQUOISE = new Color(0,175,175);
    private static final Color LIGHT_TURQUOISE = new Color(175,240,240);

    /**
     * Styles the given JComponent
     * @param component the JComponent to stylize
     */
    @Override
    public void stylize(JComponent component) {
        component.setBackground(DARK_TURQUOISE);
        component.setForeground(LIGHT_TURQUOISE);
    }

    /**
     * Retrieves the BlueStyle object as a string
     * @return String of the BlueStyle object
     */
    public String toString() {
        return "Blue Style";
    }
}
