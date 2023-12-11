package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.Assert.assertEquals;

public class ExpNumTest {

    @Test
    public void transformNumExp1() {
        TextTransformer startText;
        startText = new TextClass("1 jeden 154 69 814");
        ExpNum testUpper = new ExpNum(startText, true);
        assertEquals("jeden sto pięćdziesiąt cztery sześćdziesiąt dziewięć osiemset czternaście", testUpper.transform());
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
}