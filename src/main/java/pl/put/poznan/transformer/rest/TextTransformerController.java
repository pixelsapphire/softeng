package pl.put.poznan.transformer.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
@RequestMapping("/{serviceName}")
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    /**
     * Handles GET requests for text transformation.
     *
     * @param serviceName the input text to be transformed
     * @param transforms  array of transformation types specified in the request
     * @return JSON representation of transformation details
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String serviceName, @RequestParam(value = "transforms", defaultValue = "upper,escape") String[] transforms) {

        if (serviceName.equals("transform")) logger.debug("Selected service: " + serviceName);
        else {
            logger.error("Invalid service name: " + serviceName);
            return ResponseBody.error("Invalid service name: " + serviceName).toString();
        }

        return ResponseBody.ok(help()).toString();
    }

    /**
     * Handles POST requests for text transformation.
     *
     * @param serviceName the service name (should be "transform")
     * @param transforms  object containing transformation preferences specified in the request body
     * @return transformed text based on the specified preferences
     */
    @RequestMapping(method = RequestMethod.POST, produces = "text/plain")
    public @NotNull String post(@NotNull @PathVariable String serviceName,
                                @Nullable @RequestBody TextTransformerRequestBody transforms) {

        if (serviceName.equals("transform")) logger.debug("Selected service: " + serviceName);
        else {
            logger.error("Invalid service name: " + serviceName);
            return ResponseBody.error("Invalid service name: " + serviceName).toString();
        }

        if (transforms == null) return ResponseBody.ok(help()).toString();
        else {
            logger.debug("Request body: " + transforms);
            return ResponseBody.ok(performTransformation(transforms)).toString();
        }
    }

    private @NotNull String performTransformation(@NotNull @RequestBody TextTransformerRequestBody transforms) {
        TextTransformer startText = new TextClass(transforms.getText());
        startText = new NumberExpansionTransform(startText, transforms.isNumbers());
        startText = new ShortcutTransform(startText, ShortcutTransform.Type.fromName(transforms.getShortcuts()));
        startText = new LaTeXEscapesTransform(startText, transforms.isLatex());
        startText = new DuplicatesRemovalTransform(startText, transforms.isNeighbors());
        startText = new CaseTransform(startText, CaseTransform.Type.fromName(transforms.getBasicTransform()));
        return startText.transform();
    }

    private @NotNull String help() {
        return "HELP";
    }
}
