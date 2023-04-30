package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isPredicate() {
        return getExpression().isPredicate();
    }

    public boolean isLogicalTransform() {
        return getExpression().isLogicalTransform();
    }

    public boolean isEquality() {
        return getExpression().isEquality();
    }

    public boolean isNegation() {
        return getExpression().isNegation();
    }

    public boolean isImplication() {
        return getExpression().isImplication();
    }

    public boolean isUniversalInstantiation() {
        return getExpression().isUniversalInstantiation();
    }

    public boolean isAtomic() {
        return isPredicate() || isEquality();
    }

    public List<Symbol> getSymbols() {
        return getExpression().getSymbols();
    }

    public int size() {
        return getSymbols().size();
    }

    public List<Formula> getSubFormulas() throws InvalidFormulaException {
        List<Formula> result = new ArrayList<>();
        // Readability: Every formula must be a predicate, equality, or a logical
        // transform
        if (isPredicate()) {
            result.add(this);
            return result;
        }
        if (isEquality()) {
            int splitIndex = getExpression().getSymbols().indexOf(Symbol.EQUALS);
            Expression left = getExpression().subExpression(1, splitIndex);
            Expression right = getExpression().subExpression(splitIndex + 1, size() - 1);
            Formula leftFormula = new Formula(left);
            Formula rightFormula = new Formula(right);
            result.add(leftFormula);
            result.add(rightFormula);
            return result;
        }
        if (isNegation()) {
            Expression sub = getExpression().subExpression(2, size() - 1);
            Formula f = new Formula(sub);
            result.add(f);
            return result;
        }
        if (isImplication()) {
            int splitIndex = getExpression().getSymbols().indexOf(Symbol.IMPLIES);
            Expression left = getExpression().subExpression(1, splitIndex);
            Expression right = getExpression().subExpression(splitIndex + 1, size() - 1);
            Formula leftFormula = new Formula(left);
            Formula rightFormula = new Formula(right);
            result.add(leftFormula);
            result.add(rightFormula);
            return result;
        }
        if (isUniversalInstantiation()) {
            Expression sub = getExpression().subExpression(3, size() - 1);
            Formula f = new Formula(sub);
            result.add(f);
            return result;
        }
        return new ArrayList<>();
    }
}
