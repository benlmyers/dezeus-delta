package com.dezeus.delta.logic;

import java.util.List;

import com.dezeus.delta.set.DezeusSet;

public class AryRelation<A> {

    private DezeusSet<List<A>> relation;
    private int arity;

    public AryRelation(DezeusSet<List<A>> relation, int arity) {
        this.relation = relation;
        this.arity = arity;
    }

    public DezeusSet<List<A>> getRelation() {
        return relation;
    }

    public int getArity() {
        return arity;
    }
}
