package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

/**
 * {@code HostAndPortSelectPopup} is responsible for displaying a popup
 * window that prompts the user to enter the hostname and port number.
 */
public class HostAndPortSelectPopup {

    /**
     * Displays a popup window that prompts the user to enter the hostname and port number.
     * If the user clicks OK, the popup window is closed and the entered values are returned
     * as a {@link URL} object. If the user clicks Cancel, the application is terminated.
     *
     * @return a {@link URL} object representing the entered hostname and port number
     */
    @Contract(" -> new")
    @SuppressWarnings("HttpUrlsUsage")
    public static @NotNull URL prompt() {

        final JTextField hostname = new JTextField(20), port = new JTextField(5);
        hostname.setText("localhost");
        port.setText("1203");

        final var controls = new JPanel();
        controls.add(new JLabel("nazwa hosta:"));
        controls.add(hostname);
        controls.add(Box.createHorizontalStrut(8));
        controls.add(new JLabel("port:"));
        controls.add(port);

        if (JOptionPane.showConfirmDialog(null, controls, "Wprowadzanie parametrów połączenia",
                                          JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            if (hostname.getText().isEmpty()) {
                Messages.error("Nie podano nazwy hosta!");
                return prompt();
            } else if (port.getText().isEmpty()) {
                Messages.error("Nie podano numeru portu!");
                return prompt();
            }
            new Messages.PleaseWaitPopup();
            try {
                return new URL("http://" + hostname.getText() + ":" + port.getText() + "/transform");
            } catch (IOException e) {
                Messages.error(e.getMessage());
                return prompt();
            }
        } else {
            System.exit(0);
            return prompt();
        }
    }
}
