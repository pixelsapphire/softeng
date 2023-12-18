package pl.put.poznan.transformer.client.rest;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.client.data.TransformRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * This class contains methods responsible for communication with the REST API.
 */
public class RESTController {

    @Contract("_ -> new")
    public static @NotNull String getServices(@NotNull URL url) throws IOException {

        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setDoOutput(true);

        http.setRequestMethod("GET");
        http.connect();
        return new String(http.getInputStream().readAllBytes());
    }

    @Contract("_, _ -> new")
    public static @NotNull String transform(@NotNull URL url, @NotNull TransformRequest request) throws IOException {

        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setDoOutput(true);

        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        final OutputStream out = http.getOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        writer.write(request.toString());
        writer.flush();
        writer.close();
        out.close();
        http.connect();
        return new String(http.getInputStream().readAllBytes());
    }
}
