package pl.put.poznan.transformer.logic.transform;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseTest {

    @Test
    void testTransform() {
        TextTransformer startText;
        startText = new TextClass("kot pies");
        Reverse test = new Reverse(startText, true);
        assertEquals("seip tok", test.transform());
    }
}
