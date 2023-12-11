package pl.put.poznan.transformer.logic.functionality;

import org.junit.Test;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;

import static org.junit.Assert.assertEquals;

public class ToLaTeXTest {

    @Test
    public void transformLatex1() {

        TextTransformer startText;
        startText = new TextClass(" &,^! $_,,{}sadasdasda~~~*");
        ToLaTeX testCapitalize = new ToLaTeX(startText, true);
        assertEquals(" \\&,^! \\$\\_,,\\{\\}sadasdasda\\~\\~\\~\\*", testCapitalize.transform());
    }

    @Test
    public void transformLatex2() {

        TextTransformer startText;
        startText = new TextClass(" &, $_,,{}sadasdasda~~~*");
        ToLaTeX testCapitalize = new ToLaTeX(startText, false);
        assertEquals(" &, $_,,{}sadasdasda~~~*", testCapitalize.transform());
    }

}