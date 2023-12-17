package pl.put.poznan.transformer.logic.transform;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.server.logic.TextClass;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.transform.ReverseTransform;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseTransformTest {

    @Test
    void testTransform() {
        TextTransformer startText;
        startText = new TextClass("kot pies");
        ReverseTransform test = new ReverseTransform(startText, true);
        assertEquals("seip tok", test.transform());
    }
}
