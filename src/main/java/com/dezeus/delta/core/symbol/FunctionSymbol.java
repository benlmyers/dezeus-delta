package com.dezeus.delta.core.symbol;

public class FunctionSymbol extends Symbol {

    private int arity;

    public FunctionSymbol(String literal, int arity) {
        super(literal);
        this.arity = arity;
    }

    public int getArity() {
        return arity;
    }
}
