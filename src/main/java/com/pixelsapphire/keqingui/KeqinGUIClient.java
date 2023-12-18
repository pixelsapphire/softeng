package com.pixelsapphire.keqingui;

import com.pixelsapphire.keqingui.gui.HostAndPortSelector;
import com.pixelsapphire.keqingui.gui.MainWindow;
import com.pixelsapphire.keqingui.gui.Messages;

import java.io.IOException;

public class KeqinGUIClient {

    public static void main(String[] args) {
        GlobalContext.setURL(HostAndPortSelector.prompt());
        try {
            GlobalContext.updateServices();
        } catch (final IOException e) {
            Messages.error(e.getMessage());
            System.exit(0);
        }
        new MainWindow();
    }
}