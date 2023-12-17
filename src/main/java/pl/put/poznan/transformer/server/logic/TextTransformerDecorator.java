package pl.put.poznan.transformer.server.logic;

import org.jetbrains.annotations.NotNull;

/**
 * The base class for text transformation decorators.
 * Extends {@link TextTransformer} to provide a common interface for text transformation.
 */
public abstract class TextTransformerDecorator extends TextTransformer {

    /**
     * The text transformer to be decorated.
     */
    protected TextTransformer textToTransform;

    /**
     * Constructs a TextTransformerDecorator with the specified text transformer to decorate.
     *
     * @param textToTransform the text transformer to be decorated
     */
    public TextTransformerDecorator(@NotNull TextTransformer textToTransform) {
        this.textToTransform = textToTransform;
    }

    /**
     * Transforms the text using the decorated text transformer.
     *
     * @return the transformed text
     */
    @Override
    public abstract @NotNull String transform();

    /**
     * Returns the description of the transformation performed by the decorator.
     *
     * @return description of the transformation
     */
    public abstract @NotNull String description();
}
