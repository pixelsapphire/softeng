package pl.put.poznan.transformer.rest;

import org.jetbrains.annotations.NotNull;

public class TextTransformerClass {

    private String transform;
    private boolean numbers;
    private boolean inverse;
    private String shortcuts;
    private boolean latex;
    private boolean neighbors;

    public TextTransformerClass(@NotNull String transform, boolean numbers, boolean inverse,
                                @NotNull String shortcuts, boolean latex, boolean neighbors) {
        this.transform = transform;
        this.numbers = numbers;
        this.shortcuts = shortcuts;
        this.inverse = inverse;
        this.latex = latex;
        this.neighbors = neighbors;
    }

    public @NotNull String getBasicTransform() {
        return transform;
    }

    public void setBasicTransform(@NotNull String transform) {
        this.transform = transform;
    }

    public boolean isInverse() {
		return inverse;
	}

	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}

    public boolean isNumbers() {
        return numbers;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public @NotNull String getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(@NotNull String shortcuts) {
        this.shortcuts = shortcuts;
    }

    public boolean isLatex() {
        return latex;
    }

    public void setLatex(boolean latex) {
        this.latex = latex;
    }

    public boolean isNeighbors() {
        return neighbors;
    }

    public void setNeighbors(boolean neighbors) {
        this.neighbors = neighbors;
    }
}
