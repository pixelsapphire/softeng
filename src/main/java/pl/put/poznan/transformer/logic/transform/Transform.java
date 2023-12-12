package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

/**
 * The {@code Transform} class represents a text transformer that performs various
 * transformations such as converting to uppercase, lowercase, or capitalizing the text.
 */
public class Transform extends TextTransformerDecorator {

    /**
     * The type of transformation to be applied ("upper", "lower", or "capitalize").
     */
    private final String typeOfTransform;

    /**
     * Constructs a {@code Transform} object with a specified type of transformation.
     *
     * @param textToTransform the text transformer to decorate
     * @param typeOfTransform the type of transformation to apply ("upper", "lower", or "capitalize")
     */
    public Transform(@NotNull TextTransformer textToTransform, @NotNull String typeOfTransform) {
        super(textToTransform);
        this.typeOfTransform = typeOfTransform;
    }

    /**
     * Transforms the text based on the specified type of transformation.
     *
     * @return the transformed text
     */
    @Override
    public String transform() {
        String transformedText = textToTransform.transform();

        switch (typeOfTransform) {
            case "upper":
                return transformedText.toUpperCase();
            case "lower":
                return transformedText.toLowerCase();
            case "capitalize":
                return capitalize(transformedText);
            default:
                return transformedText;
        }
    }

    /**
     * Capitalizes the first letter of each word in the given text.
     *
     * @param text the text to capitalize
     * @return capitalized text
     */
    private String capitalize(@NotNull String text) {
        final StringBuilder result = new StringBuilder();
        for (String word : text.split(" ")) {
            if (word.isEmpty()) {
                result.append(" ");
                continue;
            }
            if (Character.isLetter(word.charAt(0))) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1))
                      .append(" ");
            } else {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }
}
