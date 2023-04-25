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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Symbol)) {
            return false;
        }
        Symbol s = (Symbol) o;
        return s.getLiteral().equals(getLiteral()) && s.getType() == getType();
    }

    enum Type {
        CONSTANT,
        FUNCTION,
        RELATION,
        VARIABLE,
        GROUPING
    }
}
