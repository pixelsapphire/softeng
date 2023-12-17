package pl.put.poznan.transformer.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerServer {

    public static void main(String[] args) {
        SpringApplication.run(TextTransformerServer.class, args);
    }
}
