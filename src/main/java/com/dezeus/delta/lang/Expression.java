package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.DynAnyPackage.Invalid;

import com.dezeus.delta.exception.InvalidSymbolException;

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

    public List<Symbol> getSymbols() {
        return s;
    }

    public Language getLanguage() {
        return language;
    }
}
