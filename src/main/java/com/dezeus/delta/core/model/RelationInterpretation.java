package com.dezeus.delta.core.model;

import com.dezeus.delta.core.element.ElementwiseRelation;
import com.dezeus.delta.core.symbol.Symbol;

public class RelationInterpretation extends Interpretation {

    private ElementwiseRelation relation;

    public RelationInterpretation(Symbol symbol, Universe universe, ElementwiseRelation relation) {
        super(symbol, universe);
        this.relation = relation;
    }

    public ElementwiseRelation getRelation() {
        return relation;
    }
}
