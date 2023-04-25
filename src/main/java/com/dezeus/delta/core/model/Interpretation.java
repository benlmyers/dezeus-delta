package com.dezeus.delta.core.model;

import com.dezeus.delta.core.symbol.Symbol;

public abstract class Interpretation {

    private Symbol symbol;
    private Universe universe;

    protected Interpretation(Symbol symbol, Universe universe) {
        this.symbol = symbol;
        this.universe = universe;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Universe getUniverse() {
        return universe;
    }
}
