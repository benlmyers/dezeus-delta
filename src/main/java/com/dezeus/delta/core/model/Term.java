package com.dezeus.delta.core.model;

import java.util.List;

import com.dezeus.delta.core.symbol.Symbol;

public class Term {

    private static boolean isConstant(Model model, List<Symbol> symbols) {
        return symbols.size() == 1 && model.getLanguage().getConstantSymbols().contains(symbols.get(0));
    }

    private static boolean isVariable(Model model, List<Symbol> symbols) {
        return symbols.size() == 1 && model.getLanguage().getVariableSymbols().contains(symbols.get(0));
    }

    private Model model;
    private List<Symbol> symbols;

    public Term(Model model, List<Symbol> symbols) {
        this.model = model;
        this.symbols = symbols;
    }

    public class InvalidException extends Exception {
        public InvalidException(String reason) {
            super("This term is invalid. (" + reason + ")");
        }
    }
}
