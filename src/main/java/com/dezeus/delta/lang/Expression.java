package com.dezeus.delta.lang;

import java.util.Arrays;
import java.util.List;

import com.dezeus.delta.exception.InvalidExpressionException;

public class Expression {

    private Language language;
    private List<Symbol> s;

    public Expression(Language language, List<Symbol> s) throws InvalidExpressionException {
        this.language = language;
        this.s = s;
        for (Symbol _s : s) {
            if (!language.getAllSymbols().contains(_s) && _s.getType() != Symbol.Type.VARIABLE) {
                throw new InvalidExpressionException("Invalid symbol \"" + _s.getLiteral() + "\"");
            }
        }
    }

    public Expression(Language language, Symbol... s) throws InvalidExpressionException {
        this(language, Arrays.asList(s));
    }

    public Expression subExpression(int start, int end) throws InvalidExpressionException {
        return new Expression(language, s.subList(start, end));
    }

    public boolean isTerm() {
        return isConstant() || isVariable() || isFunction();
    }

    public boolean isAtomic() {
        return isConstant() || isVariable();
    }

    public boolean isConstant() {
        return getSymbols().size() == 1 && getSymbols().get(0).getType() == Symbol.Type.CONSTANT;
    }

    public boolean isVariable() {
        return getSymbols().size() == 1 && getSymbols().get(0).getType() == Symbol.Type.VARIABLE;
    }

    public boolean isFunction() {
        if (getSymbols().size() < 3) {
            return false;
        }
        if (getSymbols().get(0).getType() != Symbol.Type.FUNCTION) {
            return false;
        }
        // TODO: Implement
        return true;
    }

    public List<Symbol> getSymbols() {
        return s;
    }

    public Language getLanguage() {
        return language;
    }
}
