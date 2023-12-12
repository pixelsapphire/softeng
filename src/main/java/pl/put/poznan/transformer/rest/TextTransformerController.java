package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextClass;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.logic.transform.*;

import java.util.Arrays;

/**
 * Controller class for handling text transformation requests.
 */
@RestController
@RequestMapping("/{text}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private TextTransformer startText;

    /**
     * Handles GET requests for text transformation.
     *
     * @param text       the input text to be transformed
     * @param transforms array of transformation types specified in the request
     * @return JSON representation of transformation details
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text, @RequestParam(value = "transforms", defaultValue = "upper,escape") String[] transforms) {
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));
        return "{\n" +
               "\"Transform\": \"none\",\n" +
               "\"Shortcuts\": \"none\",\n" +
               "\"inverse\": true,\n" +
               "\"Numbers\": false,\n" +
               "\"Latex\": false,\n" +
               "\"Neighbors\": false,\n" +
               "}";
    }

    /**
     * Handles POST requests for text transformation.
     *
     * @param text       the input text to be transformed
     * @param transforms object containing transformation preferences specified in the request body
     * @return transformed text based on the specified preferences
     */
    @RequestMapping(method = RequestMethod.POST)
    public String post(@PathVariable String text, @RequestBody TextTransformerClass transforms) {
        logger.debug(text);
        startText = new TextClass(text);
        startText = new ExpNum(startText, transforms.isNumbers());
        startText = new ShortcutMod(startText, ShortcutMod.Type.fromName(transforms.getShortcuts()));
        startText = new ToLaTeX(startText, transforms.isLatex());
        startText = new NeighDel(startText, transforms.isNeighbors());
        startText = new Transform(startText, Transform.Type.fromName(transforms.getBasicTransform()));
        return startText.transform();
    }
}
