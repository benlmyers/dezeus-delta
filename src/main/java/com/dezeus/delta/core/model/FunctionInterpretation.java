package com.dezeus.delta.core.model;

import com.dezeus.delta.core.Function;

import com.dezeus.delta.core.Universe;
import com.dezeus.delta.core.symbol.Symbol;

public class FunctionInterpretation extends Interpretation {

    private Function function;

    public FunctionInterpretation(Symbol symbol, Universe universe, Function function) {
        super(symbol, universe);
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
