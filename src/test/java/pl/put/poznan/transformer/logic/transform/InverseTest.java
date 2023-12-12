package pl.put.poznan.transformer.logic.transform;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

public class InverseTest {
    @Test
    void testTransform() {
         TextTransformer startText;
        startText = new TextClass("kot pies");
        Inverse test = new Inverse(startText, true);
        assertEquals("seip tok", test.transform());
    }
}
