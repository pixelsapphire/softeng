package pl.put.poznan.transformer.server.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.TextTransformerDecorator;
import pl.put.poznan.transformer.server.util.DictionaryBuilder;
import pl.put.poznan.transformer.server.util.JSONFieldDescription;

import java.util.Map;

/**
 * A decorator class that transforms text into LaTeX format.
 */
public class LaTeXEscapesTransform extends TextTransformerDecorator {

    // Mapping of special characters to their LaTeX equivalents
    private static final Map<Character, String> latexCharacterMappings = new DictionaryBuilder<Character, String>()
            .with('&', "\\&").with('$', "\\$").with('#', "\\#").with('_', "\\_")
            .with('{', "\\{").with('}', "\\}").with('~', "\\~").with('*', "\\*").build();

    private final boolean latexCharactersAllowed;

    /**
     * Constructs a {@code ToLaTeX} object with the specified {@link TextTransformer} and LaTeX character allowance.
     *
     * @param textToTransform        the TextTransformer to be decorated
     * @param latexCharactersAllowed indicates whether LaTeX characters are allowed or not
     */
    public LaTeXEscapesTransform(@NotNull TextTransformer textToTransform, boolean latexCharactersAllowed) {
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
        return latexCharactersAllowed ? addLaTeXEscapes() : textToTransform.transform();
    }

    @Override
    public @NotNull JSONFieldDescription description() {
        return new JSONFieldDescription(
                "latex",
                "boolean",
                "LaTeX escapes transform. Transforms the text into LaTeX format, applying LaTeX escapes to special characters. Example: \"&\" -> \"\\&\"",
                false
        );
    }

    // Transforms the text into LaTeX format by replacing special characters
    private @NotNull String addLaTeXEscapes() {
        StringBuilder result = new StringBuilder();
        for (char c : textToTransform.transform().toCharArray()) {
            result.append(latexCharacterMappings.getOrDefault(c, String.valueOf(c)));
        }
        return result.toString();
    }
}
