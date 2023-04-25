package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.List;

import com.dezeus.delta.exception.InvalidSymbolException;
import com.dezeus.delta.exception.InvalidTermException;

public class Expression {

    private Language language;
    private List<Symbol> s = new ArrayList<>();

    public Expression(Language language, List<Symbol> s) throws InvalidSymbolException {
        this.language = language;
        this.s = s;
        for (Symbol _s : s) {
            if (!language.getSymbols().contains(_s)) {
                throw new InvalidSymbolException(_s);
            }
        }
    }

    public Expression(Symbol... s) {
        for (Symbol symbol : s) {
            this.s.add(symbol);
        }
    }

    public Expression subExpression(int start, int end) {
        try {
            return new Expression(language, s.subList(start, end));
        } catch (InvalidSymbolException e) {
            return new Expression();
        }
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
        if (getSymbols().get(1) != new Symbol("(", Symbol.Type.GROUPING)) {
            return false;
        }
        if (getSymbols().get(getSymbols().size() - 1) != new Symbol(")", Symbol.Type.GROUPING)) {
            return false;
        }
        int startIndex = 2;
        for (int i = startIndex; i < getSymbols().size() - 1; i++) {
            Expression sub = subExpression(startIndex, i);
            try {
                new Term(sub);
            } catch (InvalidTermException ex) {
                if (i == getSymbols().size() - 1)
                    return false;
            }
        }
        return true;
    }

    public List<Symbol> getSymbols() {
        return s;
    }

    public Language getLanguage() {
        return language;
    }
}
