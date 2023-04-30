package com.dezeus.delta.exception;

public class InvalidFormulaException extends Exception {

    public InvalidFormulaException() {
        super("This expression is not a valid formula.");
    }
}
