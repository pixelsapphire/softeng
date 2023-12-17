package pl.put.poznan.transformer.client;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.put.poznan.transformer.server.rest.ResponseBody;

import java.net.URI;

public class ClientController {

    public String getMethod (String endpoint) {
        return getMethod(endpoint, "localhost", "8080");
    }

    public String getMethod (String endpoint, @NotNull String host, @NotNull String port) {
        String url = "http://" + host + ":" + port + '/' + (endpoint == null ? "" : endpoint);

        // Tworzenie URI z parametrami
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("text", "pozdro 600")
                .queryParam("caseTransform", "upper")
                .queryParam("numbers", "true")
                .queryParam("reverse", "true")
                .queryParam("latex", "true")
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        // Wysłanie żądania GET z parametrami
        ResponseBody response = restTemplate.getForObject(uri, ResponseBody.class);

        return response != null ? response.toString() : null;
    }
}
