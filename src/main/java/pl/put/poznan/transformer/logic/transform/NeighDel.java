package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

public class NeighDel extends TextTransformerDecorator {

    private boolean removeAllow;

    public NeighDel(@NotNull TextTransformer textToTransform, boolean removeAllow) {
        super(textToTransform);
        this.removeAllow = removeAllow;
    }

    @Override
    public @NotNull String transform() {
        if (removeAllow) {
            return removeAdjacentDuplicates(textToTransform.transform());
        } else {
            return textToTransform.transform();
        }
    }

    private @NotNull String removeAdjacentDuplicates(@NotNull String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            if (!(words[i].equals(words[i + 1]) && !words[i].isEmpty())) {
                result.append(words[i]).append(" ");
            }
        }
        result.append(words[words.length - 1]);
        return result.toString();
    }
}
