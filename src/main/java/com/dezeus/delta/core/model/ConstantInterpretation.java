package com.dezeus.delta.core.model;

import com.dezeus.delta.core.Universe;
import com.dezeus.delta.core.Element;
import com.dezeus.delta.core.symbol.Symbol;

public class ConstantInterpretation extends Interpretation {

    private Element constant;

    public ConstantInterpretation(Symbol symbol, Universe universe, Element constant) {
        super(symbol, universe);
        this.constant = constant;
    }

    public Element getConstant() {
        return constant;
    }
}
