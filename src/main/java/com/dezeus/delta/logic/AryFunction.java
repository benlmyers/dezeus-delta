package com.dezeus.delta.logic;

import java.util.List;
import java.util.function.Function;

public class AryFunction<A, B> implements Function<List<A>, B> {

    private Function<A, B> f;
    private int arity;

    @Override
    public B apply(List<A> t) {
        if (t.size() != arity) {
            throw new UnsupportedOperationException("Expected " + arity + " arguments, but got " + t.size());
        }
        return apply(t);
    }

    public AryFunction(Function<A, B> f, int arity) {
        this.f = f;
        this.arity = arity;
    }

    public Function<A, B> getFunction() {
        return f;
    }

    public int getArity() {
        return arity;
    }
}
