package pl.put.poznan.transformer.client;

import org.jetbrains.annotations.NotNull;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.net.URL;

public class ClientController {

    public static String getMethod () throws IOException {
        return getMethod( "localhost", "8080");
    }

    public static String getMethod (@NotNull String host, @NotNull String port) throws IOException {
        URL url = null;
        url = new URL("http://" + host + ":" + port + "/transform");

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setDoOutput(true);

        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        final OutputStream out = http.getOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        writer.write("{\"text\": \"Hello Hello World & np. 25\", \"caseTransform\": \"upper\", \"numbers\": true," +
                " \"shortcuts\": \"expand\", \"latex\": true, \"reverse\": true, \"neighbors\": true}");
        writer.flush();
        writer.close();
        out.close();
        http.connect();
        return new String(http.getInputStream().readAllBytes());
    }
}
