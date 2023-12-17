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
     * Returns the type of the field.
     *
     * @return the type of the field
     */
    public @NotNull String getType() {
        return type;
    }

    /**
     * Returns the description of the field.
     *
     * @return the description of the field
     */
    public @NotNull String getDescription() {
        return description;
    }

    /**
     * Returns {@code true} or {@code false}, depending on whether the field is required or not
     *
     * @return {@code true} if the field is required, {@code false} otherwise
     */
    public boolean isRequired() {
        return required;
    }

    public String toString() {
        return "{\"name\":\"" + name.replace("\"", "\\\"") +
               "\",\"type\":\"" + type.replace("\"", "\\\"") +
               "\",\"description\":\"" + description.replace("\"", "\\\"") +
               "\",\"required\":" + required + "}";
    }
}
