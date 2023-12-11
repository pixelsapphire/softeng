package pl.put.poznan.transformer.rest;

public class TextTransformerClass {

    private String transform;
    private boolean numbers;
    private String shortcuts;
    private boolean latex;
    private boolean neighbors;

    public TextTransformerClass() {
    }

    public TextTransformerClass(String transform, boolean numbers, String shortcuts, boolean latex, boolean neighbors) {
        this.transform = transform;
        this.numbers = numbers;
        this.shortcuts = shortcuts;
        this.latex = latex;
        this.neighbors = neighbors;
    }

    public String getBasicTransform() {
        return transform;
    }

    public void setBasicTransform(String transform) {
        this.transform = transform;
    }

    public boolean isNumbers() {
        return numbers;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public String getShortcuts() {
        return shortcuts;
    }

    public void setShortcuts(String shortcuts) {
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
