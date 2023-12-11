package pl.put.poznan.transformer.logic;

public abstract class TextTransformerDecorator extends TextTransformer {

    protected TextTransformer textToTransform;

    public TextTransformerDecorator(TextTransformer textToTransform) {
        this.textToTransform = textToTransform;
    }

    @Override
    public abstract String transform();
}
