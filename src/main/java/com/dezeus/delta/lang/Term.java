package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.List;

import com.dezeus.delta.exception.InvalidTermException;

public class Term {

    private Expression e;

    public Term(Expression e) throws InvalidTermException {
        this.e = e;
        if (!e.isTerm()) {
            throw new InvalidTermException();
        }
    }

    public Expression getExpression() {
        return e;
    }

    public boolean isAtomic() {
        return getExpression().isAtomic();
    }

    public boolean isConstant() {
        return getExpression().isConstant();
    }

    public boolean isVariable() {
        return getExpression().isVariable();
    }

    public boolean isFunction() {
        return getExpression().isFunction();
    }

    public List<Symbol> getSymbols() {
        return getExpression().getSymbols();
    }

    public List<Term> getSubTerms() throws InvalidTermException {
        List<Term> result = new ArrayList<>();
        // Readability: Every term must be atomic or a variable
        if (isAtomic()) {
            Expression sub = getExpression().subExpression(0, 1);
            Term t = new Term(sub);
            result.add(t);
            return result;
        }
        if (isFunction()) {
            int start = 0;
            for (int i = 0; i < getSymbols().size(); i++) {
                if (getSymbols().get(i) == Symbol.DELIMITER) {
                    Expression subExpression = getExpression().subExpression(start, i);
                    result.add(new Term(subExpression));
                }
            }
            return result;
        }
        return new ArrayList<>();
    }
}
