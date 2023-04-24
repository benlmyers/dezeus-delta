package com.dezeus.delta.core.model;

import com.dezeus.delta.core.Relation;
import com.dezeus.delta.core.Universe;
import com.dezeus.delta.core.symbol.Symbol;

public class RelationInterpretation extends Interpretation {

    private Relation relation;

    public RelationInterpretation(Symbol symbol, Universe universe, Relation relation) {
        super(symbol, universe);
        this.relation = relation;
    }

    public Relation getRelation() {
        return relation;
    }
}
