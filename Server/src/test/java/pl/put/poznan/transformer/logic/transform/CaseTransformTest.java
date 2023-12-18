package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.server.logic.TransformerBase;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.transform.CaseTransform;

import static org.junit.Assert.assertEquals;

public class CaseTransformTest {

    @Test
    public void transformUpper() {
        TextTransformer startText;
        startText = new TransformerBase("ala ma kota a jan psa");
        CaseTransform testUpper = new CaseTransform(startText, CaseTransform.Type.UPPER);
        assertEquals("ALA MA KOTA A JAN PSA", testUpper.transform());
    }

    @Test
    public void transformLower() {
        TextTransformer startText;
        startText = new TransformerBase("AlA Ma KoTa A JaN pSa");
        CaseTransform testLower = new CaseTransform(startText, CaseTransform.Type.LOWER);
        assertEquals("ala ma kota a jan psa", testLower.transform());
    }

    @Test
    public void transformCapitalize0() {
        TextTransformer startText;
        startText = new TransformerBase("ala Ma koTa a JaN pSa");
        CaseTransform testCapitalize = new CaseTransform(startText, CaseTransform.Type.CAPITALIZE);
        assertEquals("Ala Ma KoTa A JaN PSa", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize1() {
        TextTransformer startText;
        startText = new TransformerBase("ala i jan");
        CaseTransform testCapitalize = new CaseTransform(startText, CaseTransform.Type.CAPITALIZE);
        assertEquals("Ala I Jan", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize2() {
        TextTransformer startText;
        startText = new TransformerBase("ala alaA jAaaaan");
        CaseTransform testCapitalize = new CaseTransform(startText, CaseTransform.Type.CAPITALIZE);
        assertEquals("Ala AlaA JAaaaan", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize3() {
        TextTransformer startText;
        startText = new TransformerBase("1 kot 1 Pies");
        CaseTransform testCapitalize = new CaseTransform(startText, CaseTransform.Type.CAPITALIZE);
        assertEquals("1 Kot 1 Pies", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize4() {
        TextTransformer startText;
        startText = new TransformerBase("aLa Ma KoTa a JaN pSa");
        CaseTransform testCapitalize = new CaseTransform(startText, CaseTransform.Type.IDENTITY);
        assertEquals("aLa Ma KoTa a JaN pSa", testCapitalize.transform());
    }
}