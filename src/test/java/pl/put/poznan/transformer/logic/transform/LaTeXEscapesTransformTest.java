package pl.put.poznan.transformer.logic.transform;

import org.junit.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.Assert.assertEquals;

public class LaTeXEscapesTransformTest {

    @Test
    public void transformLatex1() {

        TextTransformer startText;
        startText = new TextClass(" &,^! $_,,{}sadasdasda~~~*");
        LaTeXEscapesTransform testCapitalize = new LaTeXEscapesTransform(startText, true);
        assertEquals(" \\&,^! \\$\\_,,\\{\\}sadasdasda\\~\\~\\~\\*", testCapitalize.transform());
    }

    @Test
    public void transformLatex2() {

        TextTransformer startText;
        startText = new TextClass(" &, $_,,{}sadasdasda~~~*");
        LaTeXEscapesTransform testCapitalize = new LaTeXEscapesTransform(startText, false);
        assertEquals(" &, $_,,{}sadasdasda~~~*", testCapitalize.transform());
    }

}