package pl.put.poznan.transformer.client;

import pl.put.poznan.transformer.client.gui.HostAndPortSelectPopup;
import pl.put.poznan.transformer.client.gui.MainWindow;
import pl.put.poznan.transformer.client.gui.Messages;

import java.io.IOException;

public class TextTransformerClient {

    public static void main(String[] args) {
        GlobalContext.setURL(HostAndPortSelectPopup.prompt());
        try {
            GlobalContext.updateServices();
        } catch (final IOException e) {
            Messages.error(e.getMessage());
            System.exit(0);
        }
        new MainWindow();
    }
}