package com.dezeus.delta.logic;

import java.util.List;

import com.dezeus.delta.set.DezeusSet;

public class AryPredicate<A> {

    private DezeusSet<List<A>> Predicate;
    private int arity;

    public AryPredicate(DezeusSet<List<A>> Predicate, int arity) {
        this.Predicate = Predicate;
        this.arity = arity;
    }

    public DezeusSet<List<A>> getPredicate() {
        return Predicate;
    }

    public int getArity() {
        return arity;
    }
}
