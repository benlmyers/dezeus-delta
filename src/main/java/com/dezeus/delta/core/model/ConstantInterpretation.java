package com.dezeus.delta.core.model;

import com.dezeus.delta.core.Universe;
import com.dezeus.delta.core.UniverseObject;
import com.dezeus.delta.core.symbol.Symbol;

public class ConstantInterpretation extends Interpretation {

    private UniverseObject constant;

    public ConstantInterpretation(Symbol symbol, Universe universe, UniverseObject constant) {
        super(symbol, universe);
        this.constant = constant;
    }

    public Object getConstant() {
        return constant;
    }
}
