package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.server.logic.TransformerBase;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.transform.ShortcutTransform;

import static org.junit.Assert.assertEquals;

public class ShortcutTransformTest {

    @Test
    public void transformExp1() {
        TextTransformer startText;
        startText = new TransformerBase("Prof. ");
        ShortcutTransform test = new ShortcutTransform(startText, ShortcutTransform.Type.EXPAND);
        assertEquals("Profesor ", test.transform());
    }

    @Test
    public void transformExp2() {
        TextTransformer startText;
        startText = new TransformerBase("I tym podobne ");
        ShortcutTransform test = new ShortcutTransform(startText, ShortcutTransform.Type.COMPRESS);
        assertEquals("Itp.", test.transform());
    }

    @Test
    public void transformNumExp3() {
        TextTransformer startText;
        startText = new TransformerBase("Mgr ");
        ShortcutTransform test = new ShortcutTransform(startText, ShortcutTransform.Type.EXPAND);
        assertEquals("Magister ", test.transform());
    }

    @Test
    public void transformNumExp4() {
        TextTransformer startText;
        startText = new TransformerBase("bieżącego roku ");
        ShortcutTransform test = new ShortcutTransform(startText, ShortcutTransform.Type.COMPRESS);
        assertEquals("br.", test.transform());
    }

    @Test
    public void transformNumExp5() {
        TextTransformer startText;
        startText = new TransformerBase("Np.");
        ShortcutTransform test = new ShortcutTransform(startText, ShortcutTransform.Type.EXPAND);
        assertEquals("Na przykład ", test.transform());
    }
}