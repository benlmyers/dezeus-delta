package com.dezeus.delta.lang;

import com.dezeus.delta.exception.InvalidFormulaException;

public class Formula {

    private Expression e;

    public Formula(Expression e) throws InvalidFormulaException {
        this.e = e;
        if (!e.isFormula()) {
            throw new InvalidFormulaException();
        }
    }

    public Expression getExpression() {
        return e;
    }

    public boolean isLogicalTransform() {
        return e.isLogicalTransform();
    }

    public boolean isEquality() {
        return e.isEquality();
    }

    public boolean isNegation() {
        return e.isNegation();
    }

    public boolean isImplication() {
        return e.isImplication();
    }

    public boolean isUniversalInstantiation() {
        return e.isUniversalInstantiation();
    }
}
