package pl.put.poznan.transformer.server.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.server.logic.TransformerBase;
import pl.put.poznan.transformer.server.logic.TransformsRegister;
import pl.put.poznan.transformer.server.logic.transform.*;

import java.util.Collections;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.server.rest"})
public class TextTransformerServer {

    public static void main(String[] args) {

        TransformsRegister.register(new CaseTransform(new TransformerBase(""), CaseTransform.Type.IDENTITY));
        TransformsRegister.register(new DuplicatesRemovalTransform(new TransformerBase(""), false));
        TransformsRegister.register(new LaTeXEscapesTransform(new TransformerBase(""), false));
        TransformsRegister.register(new NumberExpansionTransform(new TransformerBase(""), false));
        TransformsRegister.register(new ReverseTransform(new TransformerBase(""), false));
        TransformsRegister.register(new ShortcutTransform(new TransformerBase(""), ShortcutTransform.Type.IDENTITY));

        final SpringApplication server = new SpringApplication(TextTransformerServer.class);
        server.setDefaultProperties(Collections.singletonMap("server.port", "1203"));
        server.run(args);
    }
}
