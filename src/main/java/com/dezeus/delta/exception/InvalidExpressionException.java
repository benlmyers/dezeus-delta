package com.dezeus.delta.exception;

public class InvalidExpressionException extends Exception {

    public InvalidExpressionException(String message) {
        super("The expression is invalid. (" + message + ")");
    }
}
