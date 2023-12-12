package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

/**
 * The {@code Reverse} class represents a text transformer decorator that performs
 * a reverse transformation on the input text based on specified conditions.
 */
public class Reverse extends TextTransformerDecorator {

    /**
     * Indicates whether inverse transformation is allowed.
     */
    private final boolean inverseAllow;

    /**
     * Constructs an Inverse object with the given text transformer and inverse
     * transformation allowance.
     *
     * @param textToTransform the text transformer to decorate
     * @param inverseAllow    true if inverse transformation is allowed, false otherwise
     */
    public Reverse(@NotNull TextTransformer textToTransform, boolean inverseAllow) {
        super(textToTransform);
        this.inverseAllow = inverseAllow;
    }

    /**
     * Transforms the input text based on the specified conditions and returns the transformed text.
     *
     * @return the transformed text
     */
    @Override
    public @NotNull String transform() {
        return inverseAllow ? invertString(textToTransform.transform()) : textToTransform.transform();
    }

    /**
     * Inverts the given input string.
     *
     * @param input the string to invert
     * @return the inverted input string
     */
    private @NotNull String invertString(@NotNull String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
