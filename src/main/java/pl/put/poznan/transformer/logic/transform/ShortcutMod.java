package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

import java.util.HashMap;
import java.util.Map;

/**
 * The ShortcutMod class is a decorator for TextTransformer that can expand or compress
 * specified shortcuts in the input text.
 */
public class ShortcutMod extends TextTransformerDecorator {

    private final String shortcutType;
    private final Map<String, String> shortcutMap;

    /**
     * Constructs a new ShortcutMod object.
     *
     * @param textToTransform The TextTransformer to decorate.
     * @param shortcutType    The type of shortcut modification ("expand" or "compress").
     */
    public ShortcutMod(TextTransformer textToTransform, String shortcutType) {
        super(textToTransform);
        this.shortcutType = shortcutType;
        this.shortcutMap = initializeShortcutMap();
    }

    /**
     * Initializes the shortcut map with predefined shortcuts and their expansions.
     *
     * @return The initialized shortcut map.
     */
    private Map<String, String> initializeShortcutMap() {
        Map<String, String> map = new HashMap<>();
        map.put("dr", "doktor ");
        map.put("Dr", "Doktor ");
        map.put("prof.", "profesor ");
        map.put("Prof.", "Profesor ");
        map.put("np.", "na przykład ");
        map.put("Np.", "Na przykład ");
        map.put("itp.", "i tym podobne ");
        map.put("Itp.", "I tym podobne ");
        map.put("inż.", "inżynier ");
        map.put("Inż.", "Inżynier ");
        map.put("br.", "bieżącego roku ");
        map.put("Br.", "Bieżącego roku ");
        map.put("nr", "numer ");
        map.put("Nr", "Numer ");
        map.put("mgr", "magister ");
        map.put("Mgr", "Magister ");
        // Add more shortcuts as needed
        return map;
    }

    /**
     * Transforms the input text based on the specified shortcut modification type.
     *
     * @return The transformed text.
     */
    @Override
    public @NotNull String transform() {
        return applyShortcutModification(textToTransform.transform());
    }

    /**
     * Applies the specified shortcut modification to the input text.
     *
     * @param inputText The input text to be transformed.
     * @return The transformed text.
     */
    public @NotNull String applyShortcutModification(@NotNull String inputText) {
        String text = inputText;
        final StringBuilder result = new StringBuilder();

        if ("expand".equals(shortcutType)) {
            String[] arr = inputText.split(" ");
            for (String ss : arr) {
                result.append(shortcutMap.getOrDefault(ss, ss + " "));
            }
            return result.toString();
        } else if ("compress".equals(shortcutType)) {
            for (Map.Entry<String, String> entry : shortcutMap.entrySet()) {
                text = text.replaceAll(entry.getValue(), entry.getKey());
            }
            return text;
        } else {
            return text;
        }
    }
}
