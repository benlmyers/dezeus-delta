package com.dezeus.delta.exception;

public class InvalidTermException extends Exception {

    public InvalidTermException() {
        super("This expression is not a valid term.");
    }
}
