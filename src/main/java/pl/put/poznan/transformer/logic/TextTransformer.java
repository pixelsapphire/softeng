package pl.put.poznan.transformer.logic;

import org.jetbrains.annotations.NotNull;

public abstract class TextTransformer {

    protected String description;

    public @NotNull String getText() {
        return description;
    }

    public abstract @NotNull String transform();

}
