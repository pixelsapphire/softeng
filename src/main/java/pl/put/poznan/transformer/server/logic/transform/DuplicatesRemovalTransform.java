package pl.put.poznan.transformer.server.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.TextTransformerDecorator;

/**
 * The {@code NeighDel} class is a decorator that removes adjacent duplicates from the text,
 * based on the specified condition.
 */
public class DuplicatesRemovalTransform extends TextTransformerDecorator {

    private final boolean removeAllow;

    /**
     * Constructs a {@code NeighDel} object.
     *
     * @param textToTransform the TextTransformer to be decorated
     * @param removingAllowed indicates whether to remove adjacent duplicates or not
     */
    public DuplicatesRemovalTransform(@NotNull TextTransformer textToTransform, boolean removingAllowed) {
        super(textToTransform);
        this.removeAllow = removingAllowed;
    }

    /**
     * Transforms the text by removing adjacent duplicates if {@code removeAllow} is {@code true}.
     *
     * @return the transformed text
     */
    @Override
    public @NotNull String transform() {
        if (removeAllow) return removeAdjacentDuplicates(textToTransform.transform());
        else return textToTransform.transform();
    }

    @Override
    public @NotNull String description() {
        return "Duplicates removal transform. Removes adjacent duplicates from the text. Example: \"Ala ma ma kota\" -> \"Ala ma kota\"";
    }

    /**
     * Removes adjacent duplicates from the input text.
     *
     * @param input the input text
     * @return the text with adjacent duplicates removed
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
