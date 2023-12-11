package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

public class Transform extends TextTransformerDecorator {

    private String typeOfTransform;

    public Transform(TextTransformer textToTransform, String typeOfTransform) {
        super(textToTransform);
        this.typeOfTransform = typeOfTransform;
    }

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

    private String capitalize(@NotNull String text) {
        final StringBuilder result = new StringBuilder();
        for (String ss : text.split(" ")) {
            if (ss.isEmpty()) {
                result.append(" ");
                continue;
            }
            if (Character.isLetter(ss.charAt(0))) {
                result.append(Character.toUpperCase(ss.charAt(0)))
                      .append(ss.substring(1))
                      .append(" ");
            } else {
                result.append(ss).append(" ");
            }
        }

        return result.toString().trim();
    }
}
