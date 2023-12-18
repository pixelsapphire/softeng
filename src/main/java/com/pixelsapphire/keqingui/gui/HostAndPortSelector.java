package com.pixelsapphire.keqingui.gui;

import com.pixelsapphire.keqingui.GlobalContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
        hostname.setText("192.168.1.106"); // TODO change this to localhost
        port.setText("1203");

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("nazwa hosta:"));
        myPanel.add(hostname);
        myPanel.add(Box.createHorizontalStrut(8)); // a spacer
        myPanel.add(new JLabel("port:"));
        myPanel.add(port);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                final var popup = new PleaseWaitPopup();
                return new URL("http://" + hostname.getText()  + ":" + port.getText()  + "/transform");
            } catch (IOException ignored) {
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
