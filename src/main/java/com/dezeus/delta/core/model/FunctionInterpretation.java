package com.dezeus.delta.core.model;

import com.dezeus.delta.core.Universe;
import com.dezeus.delta.core.UniverseObject;
import com.dezeus.delta.core.symbol.Symbol;

interface Function {
    UniverseObject apply(UniverseObject... parameters);
}

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
