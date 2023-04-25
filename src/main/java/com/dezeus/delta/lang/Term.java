package com.dezeus.delta.lang;

import com.dezeus.delta.exception.InvalidTermException;

public class Term {

    private Expression e;

    public Term(Expression e) throws InvalidTermException {
        this.e = e;
        if (!e.isTerm()) {
            throw new InvalidTermException();
        }
    }
}
