package com.dezeus.delta.exception;

import com.dezeus.delta.lang.Symbol;

public class InvalidSymbolException extends Exception {

    public InvalidSymbolException(Symbol invalidSymbol) {
        super("The symbol " + invalidSymbol.getLiteral() + " is not in the current language");
    }
}
