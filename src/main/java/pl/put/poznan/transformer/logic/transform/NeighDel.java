package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

/**
 * The NeighDel class is a decorator that removes adjacent duplicates from the text,
 * based on the specified condition.
 */
public class NeighDel extends TextTransformerDecorator {

    private final boolean removeAllow;

    /**
     * Constructs a NeighDel object.
     *
     * @param textToTransform The TextTransformer to be decorated.
     * @param removeAllow     A boolean flag indicating whether to remove adjacent duplicates or not.
     */
    public NeighDel(@NotNull TextTransformer textToTransform, boolean removeAllow) {
        super(textToTransform);
        this.removeAllow = removeAllow;
    }

    /**
     * Transforms the text by removing adjacent duplicates if the 'removeAllow' flag is set to true.
     *
     * @return The transformed text.
     */
    @Override
    public @NotNull String transform() {
        if (removeAllow) {
            return removeAdjacentDuplicates(textToTransform.transform());
        } else {
            return textToTransform.transform();
        }
    }

    /**
     * Removes adjacent duplicates from the input text.
     *
     * @param input The input text.
     * @return The text with adjacent duplicates removed.
     */
    private @NotNull String removeAdjacentDuplicates(@NotNull String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            // Check if the current word is not equal to the next word and is not empty
            if (!(words[i].equals(words[i + 1]) && !words[i].isEmpty())) {
                result.append(words[i]).append(" ");
            }
        }
        result.append(words[words.length - 1]);
        return result.toString();
    }
}
