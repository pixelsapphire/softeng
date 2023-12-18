package pl.put.poznan.transformer.client.data;

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
