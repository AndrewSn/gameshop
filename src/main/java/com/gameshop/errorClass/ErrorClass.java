package com.gameshop.errorClass;

public class ErrorClass {
    private String errorMessage;

    public ErrorClass(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
