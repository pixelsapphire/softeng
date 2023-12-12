package pl.put.poznan.transformer.logic;

import org.jetbrains.annotations.NotNull;

/**
 * The base class for text transformation decorators.
 * Extends TextTransformer to provide a common interface for text transformation.
 */
public abstract class TextTransformerDecorator extends TextTransformer {

    /**
     * The text transformer to be decorated.
     */
    protected TextTransformer textToTransform;

    /**
     * Constructs a TextTransformerDecorator with the specified text transformer to decorate.
     *
     * @param textToTransform The text transformer to be decorated.
     */
    public TextTransformerDecorator(TextTransformer textToTransform) {
        this.textToTransform = textToTransform;
    }

    /**
     * Transforms the text using the decorated text transformer.
     *
     * @return The transformed text.
     */
    @Override
    public abstract @NotNull String transform();
}
