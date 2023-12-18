package pl.put.poznan.transformer.server.logic;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code TransformsRegister} class is a singleton class that holds a list of all available text transformation
 * operations. Use {@link #register(TextTransformerDecorator)} to register a new text transformation operation for
 * all subclasses of {@link TextTransformerDecorator} that should be listed in GUI lists and help messages.
 */
public class TransformsRegister {

    /**
     * Holds all available text transformation operations.
     */
    private static final List<TextTransformerDecorator> transforms = new ArrayList<>();

    /**
     * Registers a new text transformation operation.
     *
     * @param transform the text transformation operation to be registered
     */
    public static void register(@NotNull TextTransformerDecorator transform) {
        transforms.add(transform);
    }

    /**
     * Gets the list of all available text transformation operations.
     *
     * @return the list of all available text transformation operations
     */
    @Contract(pure = true)
    public static @NotNull @UnmodifiableView List<TextTransformerDecorator> getTransforms() {
        return Collections.unmodifiableList(transforms);
    }
}
