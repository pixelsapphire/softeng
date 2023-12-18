package com.pixelsapphire.keqingui.data;

public class TransformResponse {

    private String message, error;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
