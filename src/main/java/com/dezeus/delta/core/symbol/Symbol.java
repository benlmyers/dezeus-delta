package com.dezeus.delta.core.symbol;

public abstract class Symbol {

    private String literal;

    protected Symbol(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Symbol)) {
            return false;
        }
        Symbol s = (Symbol) o;
        return s.getLiteral().equals(getLiteral());
    }

    @Override
    public int hashCode() {
        return getLiteral().hashCode();
    }
}
