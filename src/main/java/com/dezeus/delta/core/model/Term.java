package com.dezeus.delta.core.model;

import java.util.List;

import com.dezeus.delta.core.symbol.Language;
import com.dezeus.delta.core.symbol.Symbol;

public class Term {

    private Language language;
    private List<Symbol> literal;

    public Term(Language language, List<Symbol> literal) throws InvalidException {
        this.language = language;
        this.literal = literal;

        if (literal.size() == 1) {
            Symbol symbol = literal.get(0);
            if (!(language.getConstantSymbols().contains(symbol) || language.getVariableSymbols().contains(symbol))) {
                throw new InvalidException("Single symbol is not a constant or variable");
            }
        } else {
            Symbol functionSymbol = literal.get(0);
            if (!language.getFunctionSymbols().contains(functionSymbol)) {
                throw new InvalidException("First symbol is not a function symbol");
            }
            if (!literal.get(1).equals(Symbol.OPEN_PAREN)) {
                throw new InvalidException("Second symbol is not an open parenthesis");
            }
            if (!literal.get(literal.size() - 1).equals(Symbol.CLOSE_PAREN)) {
                throw new InvalidException("Last symbol is not a close parenthesis");
            }
            int termStart = 2;
            for (int i = 2; i < literal.size() - 1; i++) {
                List<Symbol> subsequence = literal.subList(termStart, i);
                try {
                    new Term(language, subsequence);
                    termStart = i + 1;
                } catch (InvalidException e) {
                    if (i == literal.size() - 1) {
                        throw new InvalidException("Symbol is not a valid subterm");
                    }
                }
            }
        }
    }

    public Language getLanguage() {
        return language;
    }

    public List<Symbol> getLiteral() {
        return literal;
    }

    public class InvalidException extends Exception {
        public InvalidException(String reason) {
            super("This term is invalid. (" + reason + ")");
        }
    }
}
