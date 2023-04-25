package com.dezeus.delta.logic;

import java.util.List;

import com.dezeus.delta.set.HybridSet;

public class AryRelation<A> {

    private HybridSet<List<A>> relation;
    private int arity;

    public AryRelation(HybridSet<List<A>> relation, int arity) {
        this.relation = relation;
        this.arity = arity;
    }

    public HybridSet<List<A>> getRelation() {
        return relation;
    }

    public int getArity() {
        return arity;
    }
}
