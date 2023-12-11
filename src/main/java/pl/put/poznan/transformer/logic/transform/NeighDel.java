package pl.put.poznan.transformer.logic.transform;

import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

public class NeighDel extends TextTransformerDecorator {

    private boolean removeAllow;

    public NeighDel(TextTransformer textToTransform, boolean removeAllow) {
        super(textToTransform);
        this.removeAllow = removeAllow;
    }

    @Override
    public String transform() {
        if (removeAllow) {
            return removeAdjacentDuplicates(textToTransform.transform());
        } else {
            return textToTransform.transform();
        }
    }

    private String removeAdjacentDuplicates(String input) {
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
