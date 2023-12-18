package com.pixelsapphire.keqingui.gui;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class WrapLabel extends JLabel {

    public WrapLabel(@Nullable String text) {
        super("<html>"+text+"</html");
    }

    @Override
    public void setText(@Nullable String text) {
        super.setText("<html>" + text + "</html");
    }
}
