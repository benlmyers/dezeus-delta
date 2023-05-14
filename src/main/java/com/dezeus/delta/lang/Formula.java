package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.List;

import com.dezeus.delta.exception.InvalidFormulaException;
import com.dezeus.delta.exception.NoScopeException;
import com.dezeus.delta.exception.NoSubFormulaException;

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

    /**
     * Gets the scope of a variable within the formula.
     * The scope is a range of where such a variable occurs.
     * 
     * @param v The variable to search a scope for
     * @return The range, inclusive, of the indexes of symbols upon which the
     *         variable occurs
     * @throws NoScopeException when an issue occurs with finding a universal
     *                          instantiation subformula of the variable, or when a
     *                          subformula is found but is not a universal
     *                          instantiation
     */
    public int[] getScope(Variable v) throws NoScopeException {
        int startIndex = getSymbols().indexOf(v);
        if (startIndex < 1) {
            throw new NoScopeException(v, this);
        }
        try {
            Formula formula = findSubformula(startIndex - 1);
            if (!formula.isUniversalInstantiation()) {
                throw new NoScopeException(v, this);
            }
            int endIndex = startIndex + formula.size() - 1;
            int[] result = { startIndex, endIndex };
            return result;
        } catch (Exception _0) {
            throw new NoScopeException(v, this);
        }
    }

    private Formula findSubformula(int startingIndex) throws InvalidFormulaException, NoSubFormulaException {
        int parenCount = 0;
        for (int i = startingIndex; i < size(); i++) {
            if (getSymbols().get(i) == Symbol.LEFT_PAREN) {
                parenCount++;
            }
            if (getSymbols().get(i) == Symbol.RIGHT_PAREN) {
                parenCount--;
                if (parenCount == 0) {
                    Expression sub = getExpression().subExpression(startingIndex, i + 1);
                    return new Formula(sub);
                }
            }
        }
        throw new NoSubFormulaException(this);
    }
}
