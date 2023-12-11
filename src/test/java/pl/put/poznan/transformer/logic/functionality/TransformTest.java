package pl.put.poznan.transformer.logic.functionality;

import org.junit.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.Assert.assertEquals;

public class TransformTest {

    @Test
    public void transformUpper() {

        TextTransformer startText;
        startText = new TextClass("ala ma kota a jan psa");
        Transform testUpper = new Transform(startText, "upper");
        assertEquals("ALA MA KOTA A JAN PSA", testUpper.transform());

    }

    @Test
    public void transformLower() {

        TextTransformer startText;
        startText = new TextClass("AlA Ma KoTa A JaN pSa");
        Transform testLower = new Transform(startText, "lower");
        assertEquals("ala ma kota a jan psa", testLower.transform());
    }

    @Test
    public void transformCapitalize0() {

        TextTransformer startText;
        startText = new TextClass("ala Ma koTa a JaN pSa");
        Transform testCapitalize = new Transform(startText, "capitalize");
        assertEquals("Ala Ma KoTa A JaN PSa", testCapitalize.transform());

    }

    @Test
    public void transformCapitalize1() {

        TextTransformer startText;
        startText = new TextClass("ala i jan");
        Transform testCapitalize = new Transform(startText, "capitalize");
        assertEquals("Ala I Jan", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize2() {

        TextTransformer startText;
        startText = new TextClass("ala alaA jAaaaan");
        Transform testCapitalize = new Transform(startText, "capitalize");
        assertEquals("Ala AlaA JAaaaan", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize3() {

        TextTransformer startText;
        startText = new TextClass("1 kot 1 Pies");
        Transform testCapitalize = new Transform(startText, "capitalize");
        assertEquals("1 Kot 1 Pies", testCapitalize.transform());
    }

    @Test
    public void transformCapitalize4() {

        TextTransformer startText;
        startText = new TextClass("aLa Ma KoTa a JaN pSa");
        Transform testCapitalize = new Transform(startText, "none");
        assertEquals("aLa Ma KoTa a JaN pSa", testCapitalize.transform());
    }
}