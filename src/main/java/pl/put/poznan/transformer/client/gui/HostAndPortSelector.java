package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.client.GlobalContext;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.net.URL;

public class HostAndPortSelector {

    @Contract(" -> new")
    public static @NotNull URL prompt() {

        JTextField hostname = new JTextField(20), port = new JTextField(5);
        hostname.setText("localhost");
        port.setText("1203");

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("nazwa hosta:"));
        myPanel.add(hostname);
        myPanel.add(Box.createHorizontalStrut(8)); // a spacer
        myPanel.add(new JLabel("port:"));
        myPanel.add(port);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                                                   "Wprowadzanie parametrów połączenia", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (hostname.getText().isEmpty()) {
                Messages.error("Nie podano nazwy hosta!");
                return prompt();
            } else if (port.getText().isEmpty()) {
                Messages.error("Nie podano numeru portu!");
                return prompt();
            }
            new PleaseWaitPopup();
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

    private static class PleaseWaitPopup extends JFrame {

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
