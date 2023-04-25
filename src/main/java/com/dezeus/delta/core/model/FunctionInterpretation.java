package com.dezeus.delta.core.model;

import com.dezeus.delta.core.Universe;
import com.dezeus.delta.core.element.ElementwiseOperation;
import com.dezeus.delta.core.symbol.Symbol;

public class FunctionInterpretation extends Interpretation {

    private ElementwiseOperation function;

    public FunctionInterpretation(Symbol symbol, Universe universe, ElementwiseOperation function) {
        super(symbol, universe);
        this.function = function;
    }

    public ElementwiseOperation getFunction() {
        return function;
    }
}
