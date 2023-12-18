package pl.put.poznan.transformer.client;

import org.jetbrains.annotations.NotNull;

import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.IOException;
import java.net.URL;

public class ClientController {

    public static String sendGetRequest() throws IOException {
        return sendGetRequest( "localhost", "8080");
    }

    public static String sendGetRequest(@NotNull String host, @NotNull String port) throws IOException {
        URL url = null;
        url = new URL("http://" + host + ":" + port + "/transform");

        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setDoOutput(true);

        http.setRequestMethod("GET");
        http.connect();
        return new String(http.getInputStream().readAllBytes());
    }
}
