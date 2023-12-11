package pl.put.poznan.transformer.logic;

import org.jetbrains.annotations.NotNull;

public class TextClass extends TextTransformer {

    public TextClass(@NotNull String str) {
        this.description = str;
    }

    @Override
    public @NotNull String transform() {
        return description;
    }

}
