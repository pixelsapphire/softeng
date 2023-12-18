package com.pixelsapphire.keqingui.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TransformRequest {

    private final String text;
    public Map<@NotNull String, @Nullable Object> transformOperations = new HashMap<>();

    public TransformRequest(@NotNull String text) {
        this.text = text;
    }

    public TransformRequest with(@NotNull String key, @Nullable Object value) {
        transformOperations.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        final var request = new StringBuilder("{\"text\":\"" + text + "\",");
        for (final var entry : transformOperations.entrySet())
            request.append("\"").append(entry.getKey()).append("\":").append(jsonLiteral(entry.getValue())).append(",");
        request.deleteCharAt(request.length() - 1);
        request.append("}");
        return request.toString();
    }

    private String jsonLiteral(@Nullable Object value) {
        if (value instanceof final String str) return "\"" + str + "\"";
        else return Objects.toString(value);
    }
}
