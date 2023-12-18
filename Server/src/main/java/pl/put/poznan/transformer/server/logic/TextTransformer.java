package pl.put.poznan.transformer.server.logic;

import org.jetbrains.annotations.NotNull;

/**
 * The {@code TextTransformer} class is an abstract base class for text transformation operations.
 * Subclasses must implement the {@link #transform()} method to define specific transformation logic.
 */
public abstract class TextTransformer {

    /**
     * The text to be transformed.
     */
    protected String text;

    /**
     * Gets the text to be transformed.
     *
     * @return the text to be transformed
     */
    public @NotNull String getText() {
        return text;
    }

    /**
     * Abstract method to be implemented by subclasses. Performs the actual transformation of the text.
     *
     * @return the transformed text
     */
    public abstract @NotNull String transform();
}
