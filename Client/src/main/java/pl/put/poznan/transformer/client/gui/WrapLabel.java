package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.Nullable;

import javax.swing.JLabel;

/**
 * A label that automatically wraps its text.
 */
public class WrapLabel extends JLabel {

    /**
     * Creates a new label with the specified text.
     *
     * @param text the single line of text this component will display
     */
    public WrapLabel(@Nullable String text) {
        super("<html>" + text + "</html");
    }

    /**
     * Sets the text of this component.
     *
     * @param text the single line of text this component will display
     */
    @Override
    public void setText(@Nullable String text) {
        super.setText("<html>" + text + "</html");
    }
}
