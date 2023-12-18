package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.server.logic.TransformerBase;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.transform.DuplicatesRemovalTransform;

import static org.junit.Assert.assertEquals;

public class DuplicatesRemovalTransformTest {

    @Test
    public void transformNeighborRemove1() {

        TextTransformer startText;
        startText = new TransformerBase("ala ala lala ala ala");
        DuplicatesRemovalTransform testCapitalize = new DuplicatesRemovalTransform(startText, true);
        assertEquals("ala lala ala", testCapitalize.transform());
    }

    @Test
    public void transformNeighborRemove3() {

        TextTransformer startText;
        startText = new TransformerBase("ala ala aLa ala ala");
        DuplicatesRemovalTransform testCapitalize = new DuplicatesRemovalTransform(startText, false);
        assertEquals("ala ala aLa ala ala", testCapitalize.transform());
    }

    @Test
    public void transformNeighborRemove4() {

        TextTransformer startText;
        startText = new TransformerBase("ala ma kota a a a jan jan jan psa psa");
        DuplicatesRemovalTransform testCapitalize = new DuplicatesRemovalTransform(startText, true);
        assertEquals("ala ma kota a jan psa", testCapitalize.transform());
    }
}