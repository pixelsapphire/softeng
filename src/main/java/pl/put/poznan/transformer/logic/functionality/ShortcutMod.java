package pl.put.poznan.transformer.logic.functionality;

import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.TextTransformerDecorator;

import java.util.HashMap;
import java.util.Map;

public class ShortcutMod extends TextTransformerDecorator {

    private final String shortcutType;
    private final Map<String, String> shortcutMap;

    public ShortcutMod(TextTransformer textToTransform, String shortcutType) {
        super(textToTransform);
        this.shortcutType = shortcutType;
        this.shortcutMap = initializeShortcutMap();
    }

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
        return map;
    }

    @Override
    public String transform() {
        return function(textToTransform.transform());
    }

    public String function(String s) {
        String text = s;
        StringBuilder result = new StringBuilder();
        if ("expand".equals(shortcutType)) {
            String[] arr = s.split(" ");
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
