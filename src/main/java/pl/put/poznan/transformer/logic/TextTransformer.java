package pl.put.poznan.transformer.logic;

import org.jetbrains.annotations.NotNull;

/**
 * The TextTransformer class is an abstract base class for text transformation operations.
 * Subclasses must implement the transform() method to define specific transformation logic.
 */
public abstract class TextTransformer {

    /**
     * The description of the text to be transformed.
     */
    protected String description;

    /**
     * Gets the current text description.
     *
     * @return The current text description.
     */
    public @NotNull String getText() {
        return description;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Performs the actual transformation of the text.
     *
     * @return The transformed text.
     */
    public abstract @NotNull String transform();
}
