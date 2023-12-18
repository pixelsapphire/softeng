package pl.put.poznan.transformer.client.gui;

import org.jetbrains.annotations.NotNull;

import javax.swing.JOptionPane;

public class Messages {

    public static void error(@NotNull String details) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + details,
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }
}
