package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dezeus.delta.exception.InvalidExpressionException;
import com.dezeus.delta.lang.Symbol.Type;

public class Expression {

    private Language language;
    private List<Symbol> s;

    /**
     * Creates an expression from a list of symbols
     * 
     * @param language The language to use. All symbols used in the expression must
     *                 belong to the language.
     * @param s        The list of symbols
     * @throws InvalidExpressionException if the expression contains symbols not
     *                                    present in the language
     */
    public Expression(Language language, List<Symbol> s) throws InvalidExpressionException {
        this.language = language;
        this.s = s;
        for (Symbol _s : s) {
            if (!language.getAllSymbols().contains(_s) && _s.getType() != Symbol.Type.VARIABLE) {
                throw new InvalidExpressionException("Invalid symbol \"" + _s.getLiteral() + "\"");
            }
        }
    }

    /**
     * Creates an expression from a list of symbols
     * 
     * @param language The language to use. All symbols used in the expression must
     *                 belong to the language.
     * @param s        The list of symbols
     * @throws InvalidExpressionException if the expression contains symbols not
     *                                    present in the language
     */
    public Expression(Language language, Symbol... s) throws InvalidExpressionException {
        this(language, Arrays.asList(s));
    }

    /**
     * Gets a sub-expression
     * 
     * @param start The starting symbol index, inclusive
     * @param end   The ending symbol index, exclusive
     * @return
     */
    public Expression subExpression(int start, int end) {
        try {
            return new Expression(language, s.subList(start, end));
        } catch (InvalidExpressionException e) {
            // Unreachable; a subexpression is always an expression.
            return null;
        }
    }

    /**
     * Whether the expression is a term
     * 
     * @return true if the expression is a term, false otherwise
     */
    protected boolean isTerm() {
        return isConstant() || isVariable() || isFunction();
    }

    /**
     * Whether the expression is a formula
     * 
     * @return true if the expression is a formula, false otherwise
     */
    protected boolean isFormula() {
        return isPredicate() || isEquality() || isLogicalTransform();
    }

    /**
     * Whether the expression is a logical transform (negation, implication, or
     * instantiation)
     * 
     * @return true if the expression is a logical transform, false otherwise
     */
    protected boolean isLogicalTransform() {
        return isNegation() || isImplication() || isUniversalInstantiation();
    }

    /**
     * Whether the expression is a constant
     * 
     * @return true if the expression is a constant, false otherwise
     */
    protected boolean isConstant() {
        return getSymbols().size() == 1 && getSymbols().get(0).getType() == Symbol.Type.CONSTANT;
    }

    /**
     * Whether the expression is a variable
     * 
     * @return true if the expression is a variable, false otherwise
     */
    protected boolean isVariable() {
        return getSymbols().size() == 1 && getSymbols().get(0).getType() == Symbol.Type.VARIABLE;
    }

    protected boolean isEquality() {
        if (size() < 5) {
            return false;
        }
        if (!isBracketed()) {
            return false;
        }
        if (!getSymbols().contains(Symbol.EQUALS)) {
            return false;
        }
        int splitIndex = getSymbols().indexOf(Symbol.EQUALS);
        Expression left = subExpression(1, splitIndex);
        Expression right = subExpression(splitIndex + 1, size() - 1);
        return left.isTerm() && right.isTerm();
    }

    protected boolean isNegation() {
        if (size() < 4) {
            return false;
        }
        if (!isBracketed()) {
            return false;
        }
        if (!getSymbols().contains(Symbol.NOT)) {
            return false;
        }
        Expression sub = subExpression(2, size() - 1);
        return sub.isFormula();
    }

    protected boolean isImplication() {
        if (size() < 5) {
            return false;
        }
        if (!isBracketed()) {
            return false;
        }
        if (!getSymbols().contains(Symbol.IMPLIES)) {
            return false;
        }
        int splitIndex = getSymbols().indexOf(Symbol.IMPLIES);
        Expression left = subExpression(1, splitIndex);
        Expression right = subExpression(splitIndex + 1, size() - 1);
        return left.isFormula() && right.isFormula();
    }

    protected boolean isUniversalInstantiation() {
        if (size() < 3) {
            return false;
        }
        if (getSymbols().get(0) != Symbol.FOR_ALL) {
            return false;
        }
        if (getSymbols().get(1).getType() != Type.VARIABLE) {
            return false;
        }
        return subExpression(2, size()).isFormula();
    }

    protected boolean isFunction() {
        if (getSymbols().size() < 3) {
            return false;
        }
        if (getSymbols().get(0).getType() != Symbol.Type.FUNCTION) {
            return false;
        }
        if (getSymbols().get(1) != Symbol.LEFT_PAREN) {
            return false;
        }
        if (getSymbols().get(size() - 1) != Symbol.RIGHT_PAREN) {
            return false;
        }
        return subExpression(1, size() - 1).isListOfTerms();
    }

    protected boolean isPredicate() {
        if (getSymbols().size() < 3) {
            return false;
        }
        if (getSymbols().get(0).getType() != Symbol.Type.PREDICATE) {
            return false;
        }
        if (getSymbols().get(1) != Symbol.LEFT_PAREN) {
            return false;
        }
        if (getSymbols().get(size() - 1) != Symbol.RIGHT_PAREN) {
            return false;
        }
        return subExpression(1, size() - 1).isListOfTerms();
    }

    public List<Symbol> getSymbols() {
        return s;
    }

    public Language getLanguage() {
        return language;
    }

    public Expression add(Expression e) throws InvalidExpressionException {
        List<Symbol> newSymbols = new ArrayList<>();
        newSymbols.addAll(getSymbols());
        newSymbols.addAll(e.getSymbols());
        return new Expression(getLanguage(), newSymbols);
    }

    public int size() {
        return getSymbols().size();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Expression)) {
            return false;
        }
        Expression e = (Expression) o;
        return getSymbols().equals(e.getSymbols()) && getLanguage().equals(e.getLanguage());
    }

    @Override
    public int hashCode() {
        return getSymbols().hashCode();
    }

    @Override
    public String toString() {
        String result = "";
        for (Symbol symbol : getSymbols()) {
            result += symbol.getLiteral();
        }
        return result;
    }

    private boolean isBracketed() {
        if (getSymbols().get(0) != Symbol.LEFT_PAREN) {
            return false;
        }
        if (getSymbols().get(size() - 1) != Symbol.RIGHT_PAREN) {
            return false;
        }
        return true;
    }

    private boolean isListOfTerms() {
        int start = 0;
        for (int i = 0; i < getSymbols().size(); i++) {
            if (getSymbols().get(i) == Symbol.DELIMITER) {
                Expression subExpression = subExpression(start, i);
                if (!subExpression.isTerm()) {
                    return false;
                }
                start = i;
            }
        }
        return true;
    }
}
