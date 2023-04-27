package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;

public class Symbol {

    public static final Symbol LEFT_PAREN = new Symbol("(");
    public static final Symbol RIGHT_PAREN = new Symbol(")");
    public static final Symbol NOT = new Symbol("¬");
    public static final Symbol IMPLIES = new Symbol("→");
    public static final Symbol FOR_ALL = new Symbol("∀");
    public static final Symbol EQUALS = new Symbol("=");

    public static final Set<Symbol> ALL_LOGIC() {
        Set<Symbol> result = new HashSet<>();
        result.add(LEFT_PAREN);
        result.add(RIGHT_PAREN);
        result.add(NOT);
        result.add(IMPLIES);
        result.add(FOR_ALL);
        result.add(EQUALS);
        return result;
    }

    private String literal;
    private Type type;

    private Symbol(String literal) {
        this(literal, Type.LOGICAL);
    }

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

    public enum Type {
        LOGICAL,
        CONSTANT,
        FUNCTION,
        PREDICATE,
        VARIABLE
    }
}
