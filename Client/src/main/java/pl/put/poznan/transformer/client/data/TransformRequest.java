package pl.put.poznan.transformer.client.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.put.poznan.transformer.client.rest.RESTController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a request to transform a text. It is constructed using the {@link #with(String, Object)}
 * method. A JSON representation of an object of this class can be obtained using the {@link #toString()} method.
 */
public class  TransformRequest {

    private final String text;
    private final Map<@NotNull String, @Nullable Object> transformOperations = new HashMap<>();

    /**
     * Creates a new request to transform the specified text.
     *
     * @param text the text to transform
     */
    public TransformRequest(@NotNull String text) {
        this.text = text;
    }

    /**
     * Adds a transformation operation to the request.
     *
     * @param key   the name of the transformation operation
     * @param value the argument of the transformation operation
     * @return this request with the specified transformation operation applied
     */
    public TransformRequest with(@NotNull String key, @Nullable Object value) {
        transformOperations.put(key, value);
        return this;
    }

    /**
     * Returns a JSON representation of this request, ready to be sent to the server.
     *
     * @return a JSON representation of this request
     * @see RESTController#transform
     */
    @Override
    public String toString() {
        final var request = new StringBuilder("{\"text\":\"" + text + "\",");
        for (final var entry : transformOperations.entrySet())
            request.append("\"").append(entry.getKey()).append("\":").append(jsonLiteral(entry.getValue())).append(",");
        request.deleteCharAt(request.length() - 1);
        request.append("}");
        return request.toString();
    }

    /**
     * Returns a JSON literal representation of the specified object.
     *
     * @param value the object to represent as a JSON literal
     * @return a JSON literal representation of the specified object
     */
    private String jsonLiteral(@Nullable Object value) {
        if (value instanceof String) return "\"" + value + "\"";
        else return Objects.toString(value);
    }
}
