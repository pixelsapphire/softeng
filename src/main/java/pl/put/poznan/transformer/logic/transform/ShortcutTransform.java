package pl.put.poznan.transformer.logic.transform;

import org.jetbrains.annotations.NotNull;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code ShortcutMod} class is a decorator for {@link TextTransformer} that can expand or compress
 * specified shortcuts in the input text.
 */
public class ShortcutTransform extends TextTransformerDecorator {

    private final Type shortcutType;
    private final Map<String, String> shortcutMap;

    /**
     * Constructs a new {@code ShortcutMod} object.
     *
     * @param textToTransform the {@link TextTransformer} to decorate
     * @param shortcutType    the type of shortcut modification
     */
    public ShortcutTransform(@NotNull TextTransformer textToTransform, @NotNull Type shortcutType) {
        super(textToTransform);
        this.shortcutType = shortcutType;
        this.shortcutMap = initializeShortcutMap();
    }

    /**
     * Initializes the shortcut map with predefined shortcuts and their expansions.
     *
     * @return the initialized shortcut map
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
     * @return the transformed text
     */
    @Override
    public @NotNull String transform() {
        return applyShortcutModification(textToTransform.transform());
    }

    @Override
    public @NotNull String description() {
        return "Shortcut expansion/compression. Expands or compresses pre-defined shortcuts" +
               " in the input text. Example: 'dr' -> 'doktor', 'na przykład' -> 'np.'";
    }

    /**
     * Applies the specified shortcut modification to the input text.
     *
     * @param inputText the text to be transformed
     * @return the transformed text
     */
    public @NotNull String applyShortcutModification(@NotNull String inputText) {
        String text = inputText;
        final StringBuilder result = new StringBuilder();

        if (shortcutType.equals(Type.EXPAND)) {
            final String[] arr = inputText.split(" ");
            for (final String ss : arr)
                result.append(shortcutMap.getOrDefault(ss, ss + " "));
            return result.toString();
        } else if (shortcutType.equals(Type.COMPRESS)) {
            for (final Map.Entry<String, String> entry : shortcutMap.entrySet())
                text = text.replaceAll(entry.getValue(), entry.getKey());
            return text;
        } else return text;
    }

    /**
     * Represents the type of shortcut modification.
     */
    public enum Type {

        /**
         * Replaces known shortcuts with their expansions.
         */
        EXPAND,
        /**
         * Replaces known shortcut expansions with the shortcuts.
         */
        COMPRESS,
        /**
         * Does not modify the input text.
         */
        IDENTITY;

        /**
         * Returns the type of shortcut modification from the given name.
         *
         * @param name the name of the type
         * @return the type of shortcut modification
         * @throws IllegalArgumentException if the given name is not a valid type name
         */
        public static Type fromName(@NotNull String name) {
            return Type.valueOf(name.toUpperCase());
        }
    }
}
