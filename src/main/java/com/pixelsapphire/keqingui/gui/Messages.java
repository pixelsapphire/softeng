package com.pixelsapphire.keqingui.gui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class Messages {

    public static void error(@NotNull String details) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + details,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
