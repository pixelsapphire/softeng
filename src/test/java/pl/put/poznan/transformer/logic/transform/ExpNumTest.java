package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.Assert.assertEquals;

public class ExpNumTest {

    @Test
    public void transformNumExp1() {
        TextTransformer startText;
        startText = new TextClass("1 dwa 154 69 814");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("jeden dwa sto pięćdziesiąt cztery sześćdziesiąt dziewięć osiemset czternaście", testUpper.transform());
    }

    @Test
    public void transformNumExp2() {
        TextTransformer startText;
        startText = new TextClass("11");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("jedenaście", testUpper.transform());
    }

    @Test
    public void transformNumExp3() {
        TextTransformer startText;
        startText = new TextClass("999");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("dziewięćset dziewięćdziesiąt dziewięć", testUpper.transform());
    }

    @Test
    public void transformNumExp4() {
        TextTransformer startText;
        startText = new TextClass("11 2");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("jedenaście dwa", testUpper.transform());
    }

    @Test
    public void transformNumExp5() {
        TextTransformer startText;
        startText = new TextClass("1 dwadzieścia");
        ExpNum testUpper = new ExpNum(startText, false);
        assertEquals("1 dwadzieścia", testUpper.transform());
    }

    @Test
    public void transformNumExp6() {
        TextTransformer startText;
        startText = new TextClass("1 dwadzieścia 59");
        ExpNum testUpper = new ExpNum(startText, false);
        assertEquals("1 dwadzieścia 59", testUpper.transform());
    }

    @Test
    public void transformNumExp7() {
        TextTransformer startText;
        startText = new TextClass("w 3 minuty zostało zrobionych 5 zadań, poświęcono około 1500 minut na 1 zadanie");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("w trzy minuty zostało zrobionych pięć zadań, poświęcono około tysiąc pięćset minut na jeden zadanie", testUpper.transform());
    }

    @Test
    public void transformNumExp8() {
        TextTransformer startText;
        startText = new TextClass("9223372036854775807");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("dziewięć trylionów dwieście dwadzieścia trzy biliardy trzysta siedemdziesiąt dwa biliony trzydzieści sześć miliardów osiemset pięćdziesiąt cztery miliony siedemset siedemdziesiąt pięć tysięcy osiemset siedem", testUpper.transform());
    }

    @Test
    public void transformNumExp9() {
        TextTransformer startText;
        startText = new TextClass("-523485234097562");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("minus pięćset dwadzieścia trzy biliony czterysta osiemdziesiąt pięć miliardów dwieście trzydzieści cztery miliony dziewięćdziesiąt siedem tysięcy pięćset sześćdziesiąt dwa", testUpper.transform());
    }
}