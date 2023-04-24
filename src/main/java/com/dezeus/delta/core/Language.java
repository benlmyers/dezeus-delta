package com.dezeus.delta.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dezeus.delta.core.symbol.ConstantSymbol;
import com.dezeus.delta.core.symbol.FunctionSymbol;
import com.dezeus.delta.core.symbol.RelationSymbol;
import com.dezeus.delta.core.symbol.Symbol;

public class Language {

    private Set<FunctionSymbol> functionSymbols;
    private Set<RelationSymbol> relationSymbols;
    private Set<ConstantSymbol> constantSymbols;

    public Language(
            Set<FunctionSymbol> functionSymbols,
            Set<RelationSymbol> relationSymbols,
            Set<ConstantSymbol> constantSymbols) {
        this.functionSymbols = functionSymbols;
        this.relationSymbols = relationSymbols;
        this.constantSymbols = constantSymbols;
    }

    public Set<FunctionSymbol> getFunctionSymbols() {
        return functionSymbols;
    }

    public Set<RelationSymbol> getRelationSymbols() {
        return relationSymbols;
    }

    public Set<ConstantSymbol> getConstantSymbols() {
        return constantSymbols;
    }

    public Set<Symbol> getSymbols() {
        Set<Symbol> symbols = new HashSet<>();
        symbols.addAll(functionSymbols);
        symbols.addAll(relationSymbols);
        symbols.addAll(constantSymbols);
        return symbols;
    }

    public boolean isTerm(List<Symbol> sequence) {
        return true;
    }
}
