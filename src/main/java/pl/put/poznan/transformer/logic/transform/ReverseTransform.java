package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

/**
 * The {@code Reverse} class represents a text transformer decorator that performs
 * a reverse transformation on the input text based on specified conditions.
 */
public class ReverseTransform extends TextTransformerDecorator {

    /**
     * Indicates whether inverse transformation is allowed.
     */
    private final boolean inverseAllow;

    /**
     * Constructs a {@code Reverse} object with the given text transformer and inverse transformation allowance.
     *
     * @param textToTransform the text transformer to decorate
     * @param reversalAllowed true if reverse transformation is allowed, false otherwise
     */
    public ReverseTransform(@NotNull TextTransformer textToTransform, boolean reversalAllowed) {
        super(textToTransform);
        this.inverseAllow = reversalAllowed;
    }

    /**
     * Transforms the input text based on the specified conditions and returns the transformed text.
     *
     * @return the transformed text
     */
    @Override
    public @NotNull String transform() {
        return inverseAllow ? revertString(textToTransform.transform()) : textToTransform.transform();
    }

    @Override
    public @NotNull String description() {
        return "Reverse transformation. Reverses the input text. Example: \"abc\" -> \"cba\"";
    }

    /**
     * Inverts the given input string.
     *
     * @param input the string to invert
     * @return the inverted input string
     */
    private @NotNull String revertString(@NotNull String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
