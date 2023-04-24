package com.dezeus.delta.core.symbol;

public class RelationSymbol extends Symbol {

    private int arity;

    public RelationSymbol(String literal, int arity) {
        super(literal);
        this.arity = arity;
    }

    public int getArity() {
        return arity;
    }
}
