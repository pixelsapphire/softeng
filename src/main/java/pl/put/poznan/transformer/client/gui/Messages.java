package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.client.GlobalContext;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * A collection of popup windows used by the application.
 */
public class Messages {

    /**
     * Displays a modal popup window with an error message.
     *
     * @param details the error message to display
     */
    public static void error(@NotNull String details) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + details,
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * {@code PleaseWaitPopup} represents a popup window with a "please wait" message.
     */
    public static class PleaseWaitPopup extends JFrame {

        /**
         * Creates a new popup window with a "please wait" message. The
         * popup window is automatically closed when the data is updated.
         */
        public PleaseWaitPopup() {
            setLocationRelativeTo(null);
            final var label = new JLabel("Proszę czekać...");
            final Border currentBorder = label.getBorder(), emptyBorder = new EmptyBorder(16, 16, 16, 16);
            label.setBorder(currentBorder == null ? emptyBorder : new CompoundBorder(emptyBorder, currentBorder));
            add(label);
            pack();
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            GlobalContext.addUpdateListener(this::dispose);
        }
    }
}
