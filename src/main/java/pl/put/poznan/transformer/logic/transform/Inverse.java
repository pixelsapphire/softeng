package pl.put.poznan.transformer.logic.transform;

import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

/**
 * The Inverse class represents a text transformer decorator that performs
 * an inverse transformation on the input text based on specified conditions.
 */
public class Inverse extends TextTransformerDecorator {

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
    public Inverse(TextTransformer textToTransform, boolean inverseAllow) {
        super(textToTransform);
        this.inverseAllow = inverseAllow;
    }

    /**
     * Inverts the given input string.
     *
     * @param input the string to invert
     * @return the inverted input string
     */
    private String invertString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Transforms the input text based on the specified conditions and returns the transformed text.
     *
     * @return the transformed text
     */
    @Override
    public String transform() {
        return inverseAllow ? invertString(textToTransform.transform()) : textToTransform.transform();
    }
}
