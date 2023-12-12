package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

import java.util.HashMap;
import java.util.Map;

/**
 * A decorator class that transforms text into LaTeX format.
 */
public class ToLaTeX extends TextTransformerDecorator {

    // Mapping of special characters to their LaTeX equivalents
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

    /**
     * Constructs a {@code ToLaTeX} object with the specified {@link TextTransformer} and LaTeX character allowance.
     *
     * @param textToTransform        the TextTransformer to be decorated
     * @param latexCharactersAllowed indicates whether LaTeX characters are allowed or not
     */
    public ToLaTeX(@NotNull TextTransformer textToTransform, boolean latexCharactersAllowed) {
        super(textToTransform);
        this.latexCharactersAllowed = latexCharactersAllowed;
    }

    /**
     * Transforms the text into LaTeX format if LaTeX characters are
     * allowed, otherwise delegates to the underlying {@link TextTransformer}.
     *
     * @return the transformed text
     */
    @Override
    public @NotNull String transform() {
        return latexCharactersAllowed ? transformText() : textToTransform.transform();
    }

    // Transforms the text into LaTeX format by replacing special characters
    private @NotNull String transformText() {
        StringBuilder result = new StringBuilder();
        for (char c : textToTransform.transform().toCharArray()) {
            result.append(latexCharacterMappings.getOrDefault(c, String.valueOf(c)));
        }
        return result.toString();
    }
}
