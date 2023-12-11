package pl.put.poznan.transformer.logic;

import org.jetbrains.annotations.NotNull;

public abstract class TextTransformerDecorator extends TextTransformer {

    protected TextTransformer textToTransform;

    public TextTransformerDecorator(TextTransformer textToTransform) {
        this.textToTransform = textToTransform;
    }

    @Override
    public abstract @NotNull String transform();
}
