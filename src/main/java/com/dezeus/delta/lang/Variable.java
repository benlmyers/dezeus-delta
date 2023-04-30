package com.dezeus.delta.lang;

public class Variable extends Symbol {

    public Variable(String literal) {
        super(literal, Symbol.Type.VARIABLE);
    }
}
