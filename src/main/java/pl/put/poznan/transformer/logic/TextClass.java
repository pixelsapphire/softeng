package pl.put.poznan.transformer.logic;

import org.jetbrains.annotations.NotNull;

/**
 * The {@code TextClass} represents a simple text transformer that does not modify the input text.
 * It extends the {@link TextTransformer} class and implements the transform method to return the input text unchanged.
 */
public class TextClass extends TextTransformer {

    /**
     * Constructs a {@code TextClass} object with the specified description.
     *
     * @param str the input text description to be used by the text transformer
     */
    public TextClass(@NotNull String str) {
        this.text = str;
    }

    /**
     * Transforms the input text and returns it unchanged.
     *
     * @return the transformed text, which is the same as the input text
     */
    @Override
    public @NotNull String transform() {
        return text;
    }

}
