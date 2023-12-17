package pl.put.poznan.transformer.server.rest;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a response body for the REST API.
 */
public class ResponseBody {

    private String message, error;

    /**
     * Constructs a {@code ResponseBody} object with the specified data and error message.
     *
     * @param data         the data
     * @param errorMessage the errorMessage
     */
    private ResponseBody(@Nullable String data, @Nullable String errorMessage) {
        this.message = data;
        this.error = errorMessage;
    }

    /**
     * Returns a {@code ResponseBody} object with the specified message.
     *
     * @param message the message to be returned
     * @return a {@code ResponseBody} object with the specified message
     */
    @Contract("_ -> new")
    public static @NotNull ResponseBody ok(@NotNull String message) {
        return new ResponseBody(message, null);
    }

    /**
     * Returns a {@code ResponseBody} object with the specified error.
     *
     * @param error the error to be returned
     * @return a {@code ResponseBody} object with the specified error
     */
    @Contract("_ -> new")
    public static @NotNull ResponseBody error(@NotNull String error) {
        return new ResponseBody(null, error);
    }

    @Override
    public String toString() {
        final String message = this.message != null ? "\"" + this.message + "\"" : "null";
        final String error = this.error != null ? "\"" + this.error + "\"" : "null";
        return "{\"message\":" + message + ",\"error\":" + error + "}";
    }
}
