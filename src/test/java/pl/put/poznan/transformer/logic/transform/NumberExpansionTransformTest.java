package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.server.logic.TransformerBase;
import pl.put.poznan.transformer.server.logic.TextTransformer;
import pl.put.poznan.transformer.server.logic.transform.NumberExpansionTransform;

import static org.junit.Assert.assertEquals;

public class NumberExpansionTransformTest {

    @Test
    public void transformNumExp1() {
        TextTransformer startText;
        startText = new TransformerBase("1 dwa 154 69 814");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("jeden dwa sto pięćdziesiąt cztery sześćdziesiąt dziewięć osiemset czternaście", testUpper.transform());
    }

    @Test
    public void transformNumExp2() {
        TextTransformer startText;
        startText = new TransformerBase("11");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("jedenaście", testUpper.transform());
    }

    @Test
    public void transformNumExp3() {
        TextTransformer startText;
        startText = new TransformerBase("999");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("dziewięćset dziewięćdziesiąt dziewięć", testUpper.transform());
    }

    @Test
    public void transformNumExp4() {
        TextTransformer startText;
        startText = new TransformerBase("11 2");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("jedenaście dwa", testUpper.transform());
    }

    @Test
    public void transformNumExp5() {
        TextTransformer startText;
        startText = new TransformerBase("1 dwadzieścia");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, false);
        assertEquals("1 dwadzieścia", testUpper.transform());
    }

    @Test
    public void transformNumExp6() {
        TextTransformer startText;
        startText = new TransformerBase("1 dwadzieścia 59");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, false);
        assertEquals("1 dwadzieścia 59", testUpper.transform());
    }

    @Test
    public void transformNumExp7() {
        TextTransformer startText;
        startText = new TransformerBase("w 3 minuty zostało zrobionych 5 zadań, poświęcono około 1500 minut na 1 zadanie");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("w trzy minuty zostało zrobionych pięć zadań, poświęcono około tysiąc pięćset minut na jeden zadanie", testUpper.transform());
    }

    @Test
    public void transformNumExp8() {
        TextTransformer startText;
        startText = new TransformerBase("9223372036854775807");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("dziewięć trylionów dwieście dwadzieścia trzy biliardy trzysta siedemdziesiąt dwa biliony trzydzieści sześć miliardów osiemset pięćdziesiąt cztery miliony siedemset siedemdziesiąt pięć tysięcy osiemset siedem", testUpper.transform());
    }

    @Test
    public void transformNumExp9() {
        TextTransformer startText;
        startText = new TransformerBase("-523485234097562");
        NumberExpansionTransform testUpper = new NumberExpansionTransform(startText, true);
        assertEquals("minus pięćset dwadzieścia trzy biliony czterysta osiemdziesiąt pięć miliardów dwieście trzydzieści cztery miliony dziewięćdziesiąt siedem tysięcy pięćset sześćdziesiąt dwa", testUpper.transform());
    }
}