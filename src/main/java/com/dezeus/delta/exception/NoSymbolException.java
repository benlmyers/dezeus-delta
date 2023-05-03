package com.dezeus.delta.exception;

public class NoSymbolException extends Exception {

    public NoSymbolException(String literal) {
        super("No symbol \"" + literal + "\" could be found in the language.");
    }
}
