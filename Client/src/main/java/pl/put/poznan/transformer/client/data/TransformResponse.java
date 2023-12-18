package pl.put.poznan.transformer.client.data;

import pl.put.poznan.transformer.client.rest.RESTController;

/**
 * This class represents a transformed text. It is used by Jackson Databind to deserialize the response from the server.
 *
 * @see RESTController#transform
 */
@SuppressWarnings("unused")
public class TransformResponse {

    private String message, error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
