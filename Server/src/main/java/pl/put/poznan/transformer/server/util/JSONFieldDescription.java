package pl.put.poznan.transformer.server.util;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a JSON field description for the REST API.
 */
public class JSONFieldDescription {

    private final String name, type, description;
    private final boolean required;

    /**
     * Constructs a {@code TransformDescription} object with the specified name, type, description and requirement.
     *
     * @param name        the name of the field
     * @param type        the type of the field
     * @param description the description of the field
     * @param required    indicates whether the field is required ({@code true}) or optional ({@code false})
     */
    public JSONFieldDescription(@NotNull String name, @NotNull String type, @NotNull String description, boolean required) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.required = required;
    }

    /**
     * Returns the name of the field.
     *
     * @return the name of the field
     */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Escapes the given string for JSON, replacing {@code "} with {@code \"} and {@code \} with {@code \\}.
     *
     * @param s the string to be escaped
     * @return the escaped string
     */
    private @NotNull String escape(@NotNull String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public String toString() {
        return "{\"name\":\"" + escape(name) + "\",\"type\":\"" + escape(type) +
               "\",\"description\":\"" + escape(description) + "\",\"required\":" + required + "}";
    }
}
