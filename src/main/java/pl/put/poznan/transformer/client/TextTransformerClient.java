package pl.put.poznan.transformer.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class TextTransformerClient {

    private static void testGet() throws IOException {

        URL url = new URL("http://localhost:8080/transform");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setDoOutput(true);

        http.setRequestMethod("GET");
        http.connect();
        System.out.println(new String(http.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
    }

    private static void testPost() throws IOException {

        URL url = new URL("http://localhost:8080/transform");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setDoOutput(true);

        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type", "application/json");
        final OutputStream out = http.getOutputStream();
        final OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        writer.write("{\"text\": \"Hello Hello World & np. 25\", \"caseTransform\": \"upper\", \"numbers\": true, \"shortcuts\": \"expand\", \"latex\": true, \"reverse\": true, \"neighbors\": true}");
        writer.flush();
        writer.close();
        out.close();
        http.connect();
        System.out.println(new String(http.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
    }

    public static void main(String[] args) throws IOException {
        System.out.println("I'm a super cool Text Transformer Client!");
        testGet();
        testPost();
    }
}
