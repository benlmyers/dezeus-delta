package com.dezeus.delta.lang;

public class Symbol {

    private String literal;
    private Type type;

    public Symbol(String literal, Type type) {
        this.literal = literal;
        this.type = type;
    }

    public String getLiteral() {
        return literal;
    }

    public Type getType() {
        return type;
    }

    enum Type {
        CONSTANT,
        FUNCTION,
        RELATION,
        VARIABLE,
        GROUPING
    }
}
