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

        StringBuilder transformOperations = null;
        //StringBuilder shortcuts = null;
        boolean inverse = false;
        boolean numbers = false;
        boolean latex = false;
        boolean neighbours = false;

        try {
            for (String transformation : transforms) {
                transformation = transformation.toLowerCase();
                if (Arrays.asList("upper", "lower", "capitalize").contains(transformation)) {
                    if (transformOperations == null) transformOperations = new StringBuilder(transformation);
                    else {
                        transformOperations.append(", ");
                        transformOperations.append(transformation);
                    }
                    continue;
                }

                switch (transformation) {
                    case "inverse": inverse = true;
                        break;
                    case "numbers": numbers = true;
                        break;
                    case "latex": latex = true;
                        break;
                    case "neighbours": neighbours = true;
                        break;
                    default: throw new IllegalStateException("Unexpected value: " + transformation);
                }
            }

        } catch (final Throwable e) {
            logger.error("Error while parsing the request: " + e.getMessage());
            return null;
        }
        return "{\n" +
               "\"Transform\": " + (transformOperations != null ? transformOperations.toString() : "none") + ",\n" +
               "\"Shortcuts\": \"none\",\n" +
               "\"inverse\": " + inverse + ",\n" +
               "\"Numbers\": " + numbers + ",\n" +
               "\"Latex\": " + latex + ",\n" +
               "\"Neighbors\": " + neighbours + ",\n" +
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
    public String post(@PathVariable String text, @RequestBody TextTransformerRequestBody transforms) {
        logger.debug(text);
        TextTransformer startText = new TextClass(text);
        startText = new ExpNum(startText, transforms.isNumbers());
        startText = new ShortcutMod(startText, ShortcutMod.Type.fromName(transforms.getShortcuts()));
        startText = new ToLaTeX(startText, transforms.isLatex());
        startText = new NeighDel(startText, transforms.isNeighbors());
        startText = new Transform(startText, Transform.Type.fromName(transforms.getBasicTransform()));
        return startText.transform();
    }
}
