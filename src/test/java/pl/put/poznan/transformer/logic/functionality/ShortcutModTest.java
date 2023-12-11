package pl.put.poznan.transformer.logic.functionality;

import org.junit.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.Assert.assertEquals;

public class ShortcutModTest {

    @Test
    public void transformExp1() {
        TextTransformer startText;
        startText = new TextClass("Prof. ");
        ShortcutMod test = new ShortcutMod(startText, "expand");
        assertEquals("Profesor ", test.transform());
    }

    @Test
    public void transformExp2() {
        TextTransformer startText;
        startText = new TextClass("I tym podobne ");
        ShortcutMod test = new ShortcutMod(startText, "compress");
        assertEquals("Itp.", test.transform());
    }

    @Test
    public void transformNumExp3() {
        TextTransformer startText;
        startText = new TextClass("Mgr ");
        ShortcutMod test = new ShortcutMod(startText, "expand");
        assertEquals("Magister ", test.transform());
    }

    @Test
    public void transformNumExp4() {
        TextTransformer startText;
        startText = new TextClass("bieżącego roku ");
        ShortcutMod test = new ShortcutMod(startText, "compress");
        assertEquals("br.", test.transform());
    }

    @Test
    public void transformNumExp5() {
        TextTransformer startText;
        startText = new TextClass("Np.");
        ShortcutMod test = new ShortcutMod(startText, "expand");
        assertEquals("Na przykład ", test.transform());
    }
}