package com.dezeus.delta.lang;

import com.dezeus.delta.exception.InvalidTermException;

public class Term {

    private Expression e;

    public Term(Expression e) throws InvalidTermException {
        this.e = e;
        if (!(isConstant() || isVariable() || isFunction())) {
            throw new InvalidTermException();
        }
    }

    private boolean isConstant() {
        return e.getSymbols().size() == 1 && e.getSymbols().get(0).getType() == Symbol.Type.CONSTANT;
    }

    private boolean isVariable() {
        return e.getSymbols().size() == 1 && e.getSymbols().get(0).getType() == Symbol.Type.VARIABLE;
    }

    private boolean isFunction() {
        if (e.getSymbols().size() < 3) {
            return false;
        }
        if (e.getSymbols().get(0).getType() != Symbol.Type.FUNCTION) {
            return false;
        }
        if (e.getSymbols().get(1) != new Symbol("(", Symbol.Type.GROUPING)) {
            return false;
        }
        if (e.getSymbols().get(e.getSymbols().size() - 1) != new Symbol(")", Symbol.Type.GROUPING)) {
            return false;
        }
        int startIndex = 2;
        for (int i = startIndex; i < e.getSymbols().size() - 1; i++) {
            Expression sub = e.subExpression(startIndex, i);
            try {
                new Term(sub);
            } catch (InvalidTermException ex) {
                if (i == e.getSymbols().size() - 1)
                    return false;
            }
        }
        return true;
    }
}
