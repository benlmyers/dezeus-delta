package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;

public class Symbol {

    public static final Symbol LEFT_PAREN = new Symbol("(");
    public static final Symbol RIGHT_PAREN = new Symbol(")");
    public static final Symbol DELIMITER = new Symbol(",");
    public static final Symbol NOT = new Symbol("¬", 1);
    public static final Symbol IMPLIES = new Symbol("→", 2);
    public static final Symbol FOR_ALL = new Symbol("∀");
    public static final Symbol EQUALS = new Symbol("=");

    public static final Set<Symbol> ALL_LOGIC() {
        Set<Symbol> result = new HashSet<>();
        result.add(LEFT_PAREN);
        result.add(RIGHT_PAREN);
        result.add(DELIMITER);
        result.add(NOT);
        result.add(IMPLIES);
        result.add(FOR_ALL);
        result.add(EQUALS);
        return result;
    }

    private String literal;
    private Type type;
    private int arity;

    private Symbol(String literal) {
        this(literal, 0);
    }

    private Symbol(String literal, int arity) {
        this(literal, Type.LOGICAL, arity);
    }

    public Symbol(String literal, Type type, int arity) {
        this.literal = literal;
        this.type = type;
    }

    public Symbol(String literal, Type type) {
        this(literal, type, 0);
    }

    public String getLiteral() {
        return literal;
    }

    public Type getType() {
        return type;
    }

    public int getArity() {
        return arity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Symbol)) {
            return false;
        }
        Symbol s = (Symbol) o;
        return s.getLiteral().equals(getLiteral()) && s.getType() == getType() && s.getArity() == getArity();
    }

    @Override
    public int hashCode() {
        return getLiteral().hashCode();
    }

    public enum Type {
        LOGICAL,
        CONSTANT,
        FUNCTION,
        PREDICATE,
        VARIABLE
    }
}
