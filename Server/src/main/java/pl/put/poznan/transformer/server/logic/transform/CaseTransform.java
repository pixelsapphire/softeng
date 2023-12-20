package pl.put.poznan.transformer.server.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.TextTransformerDecorator;
import pl.put.poznan.transformer.server.util.JSONFieldDescription;

/**
 * The {@code Transform} class represents a text transformer that performs various
 * transformations such as converting to uppercase, lowercase, or capitalizing the text.
 */
public class CaseTransform extends TextTransformerDecorator {

    /**
     * The type of transformation to be applie.
     */
    private final Type typeOfTransform;

    /**
     * Constructs a {@code Transform} object with a specified type of transformation.
     *
     * @param textToTransform the text transformer to decorate
     * @param typeOfTransform the type of transformation to apply ("upper", "lower", or "capitalize")
     */
    public CaseTransform(@NotNull TextTransformer textToTransform, @NotNull Type typeOfTransform) {
        super(textToTransform);
        this.typeOfTransform = typeOfTransform;
    }

    /**
     * Transforms the text based on the specified type of transformation.
     *
     * @return the transformed text
     */
    @Override
    public @NotNull String transform() {
        final String transformedText = textToTransform.transform();
        return switch (typeOfTransform) {
            case UPPER -> transformedText.toUpperCase();
            case LOWER -> transformedText.toLowerCase();
            case CAPITALIZE -> caseTransformation(transformedText);
            case SENTECE_CAPITALIZE -> SentenceCaseTransformation(transformedText);
            default -> transformedText;
        };
    }

    @Override
    public @NotNull JSONFieldDescription description() {
        return new JSONFieldDescription(
                "caseTransform",
                "string (\"upper\", \"lower\", \"capitalize\" or \"identity\")",
                "Case transform. Converts the text to uppercase, lowercase, or capitalizes it. Example: \"Ala ma kota\" -> \"ALA MA KOTA\", \"ala ma kota\", \"Ala Ma Kota\"",
                false
        );
    }

    /**
     * Capitalizes the first letter of each word in the given text.
     *
     * @param text the text to capitalize
     * @return capitalized text
     */
    private @NotNull String caseTransformation(@NotNull String text) {
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

    private @NotNull String SentenceCaseTransformation(@NotNull String text) {
        final StringBuilder result = new StringBuilder();
        boolean dot = true;
        char[] Chararray = text.toCharArray();
        int k = Chararray.length;
        for (int i = 0; i < k; i++) {
            if (Character.isLetter(Chararray[i]) && dot) {
                dot = false;
                result.append(Character.toUpperCase(Chararray[i]));
                continue;
            } else if (".!?".contains(String.valueOf(Chararray[i])) && (i + 1) < k) {
                if (Character.isWhitespace(Chararray[i + 1])) {
                    dot = true;
                }
            }
            result.append(Chararray[i]);
        }
        return result.toString().trim();
    }

    /**
     * The type of transformation to be applied.
     */
    public enum Type {

        /**
         * Uppercase transformation.
         */
        UPPER,
        /**
         * Lowercase transformation.
         */
        LOWER,
        /**
         * Capitalize transformation (first letter of each word is capitalized).
         */
        CAPITALIZE,
        /**
         * No transformation.
         */
        IDENTITY,

        SENTECE_CAPITALIZE;

        /**
         * Returns the type of transformation from the given name.
         *
         * @param name the name of the type
         * @return the type of transformation
         * @throws IllegalArgumentException if the given name is not a valid type name
         */
        public static Type fromName(@NotNull String name) {
            return Type.valueOf(name.toUpperCase());
        }
    }
}
