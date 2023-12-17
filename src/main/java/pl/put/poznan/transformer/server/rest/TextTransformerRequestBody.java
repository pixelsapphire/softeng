package pl.put.poznan.transformer.server.rest;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a request body for the text transformer. Contains the text various options for
 * the transformation. This class is used by Spring to deserialize the POST request body.
 */
@SuppressWarnings("unused")
public class TextTransformerRequestBody {

    private String text;
    private String caseTransform;
    private boolean numbers;
    private boolean reverse;
    private String shortcuts;
    private boolean latex;
    private boolean neighbors;

    /**
     * Constructs a {@code TextTransformerClass} with specified parameters.
     *
     * @param caseTransform the name of the case transformation
     * @param numbers       indicates whether to transform numbers
     * @param reverse       indicates whether to apply the reverse transformation
     * @param shortcuts     the name of the shortcuts modification
     * @param latex         indicates whether to use LaTeX formatting
     * @param neighbors     indicates whether to consider neighboring elements
     */
    public TextTransformerRequestBody(String text, String caseTransform, boolean numbers, boolean reverse,
                                      String shortcuts, boolean latex, boolean neighbors) {
        this.text = text != null ? text : "";
        this.caseTransform = caseTransform != null ? caseTransform : "identity";
        this.numbers = numbers;
        this.shortcuts = shortcuts != null ? shortcuts : "identity";
        this.reverse = reverse;
        this.latex = latex;
        this.neighbors = neighbors;
    }

    /**
     * Gets the text to be transformed.
     *
     * @return the text to be transformed
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text to be transformed.
     *
     * @param text the text to be transformed
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the basic transformation string.
     *
     * @return the basic transformation string
     */
    public @NotNull String getCaseTransform() {
        return caseTransform;
    }

    /**
     * Sets the basic transformation string.
     *
     * @param transform the new basic transformation string
     */
    public void setCaseTransform(@NotNull String transform) {
        this.caseTransform = transform;
    }

    /**
     * Checks if the transformation is applied inversely.
     *
     * @return true if the transformation is applied inversely, false otherwise
     */
    public boolean isReverse() {
        return reverse;
    }

    /**
     * Sets whether the transformation should be applied inversely.
     *
     * @param reverse true to apply the transformation inversely, false otherwise
     */
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    /**
     * Checks if the transformation includes numbers.
     *
     * @return true if the transformation includes numbers, false otherwise
     */
    public boolean isNumbers() {
        return numbers;
    }

    /**
     * Sets whether the transformation should include numbers.
     *
     * @param numbers true to include numbers in the transformation, false otherwise
     */
    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    /**
     * Gets the shortcuts for the transformation.
     *
     * @return the shortcuts for the transformation
     */
    public @NotNull String getShortcuts() {
        return shortcuts;
    }

    /**
     * Sets the shortcuts for the transformation.
     *
     * @param shortcuts the new shortcuts for the transformation
     */
    public void setShortcuts(@NotNull String shortcuts) {
        this.shortcuts = shortcuts;
    }

    /**
     * Checks if LaTeX formatting is used in the transformation.
     *
     * @return true if LaTeX formatting is used, false otherwise
     */
    public boolean isLatex() {
        return latex;
    }

    /**
     * Sets whether LaTeX formatting should be used in the transformation.
     *
     * @param latex true to use LaTeX formatting, false otherwise
     */
    public void setLatex(boolean latex) {
        this.latex = latex;
    }

    /**
     * Checks if neighboring elements are considered in the transformation.
     *
     * @return true if neighboring elements are considered, false otherwise
     */
    public boolean isNeighbors() {
        return neighbors;
    }

    /**
     * Sets whether neighboring elements should be considered in the transformation.
     *
     * @param neighbors true to consider neighboring elements, false otherwise
     */
    public void setNeighbors(boolean neighbors) {
        this.neighbors = neighbors;
    }
}
