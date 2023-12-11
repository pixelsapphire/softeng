package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

import java.util.HashMap;
import java.util.Map;

public class ToLaTeX extends TextTransformerDecorator {

    private static final Map<Character, String> latexCharacterMappings;

    static {
        latexCharacterMappings = new HashMap<>();
        latexCharacterMappings.put('&', "\\&");
        latexCharacterMappings.put('$', "\\$");
        latexCharacterMappings.put('#', "\\#");
        latexCharacterMappings.put('_', "\\_");
        latexCharacterMappings.put('{', "\\{");
        latexCharacterMappings.put('}', "\\}");
        latexCharacterMappings.put('~', "\\~");
        latexCharacterMappings.put('*', "\\*");
    }

    private final boolean latexCharactersAllowed;

    public ToLaTeX(@NotNull TextTransformer textToTransform, boolean latexCharactersAllowed) {
        super(textToTransform);
        this.latexCharactersAllowed = latexCharactersAllowed;
    }

    @Override
    public @NotNull String transform() {
        return latexCharactersAllowed ? transformText() : textToTransform.transform();
    }

    private @NotNull String transformText() {
        StringBuilder result = new StringBuilder();
        for (char c : textToTransform.transform().toCharArray()) {
            result.append(latexCharacterMappings.getOrDefault(c, String.valueOf(c)));
        }
        return result.toString();
    }
}
