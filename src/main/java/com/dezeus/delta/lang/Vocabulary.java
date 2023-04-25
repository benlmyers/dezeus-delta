package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;

public class Vocabulary {

    private Set<Symbol> relationSymbols = new HashSet<>();
    private Set<Symbol> functionSymbols = new HashSet<>();
    private Set<Symbol> constantSymbols = new HashSet<>();

    public Vocabulary() {
    }

    public Vocabulary(Set<Symbol> relationSymbols, Set<Symbol> functionSymbols, Set<Symbol> constantSymbols) {
        this.relationSymbols = relationSymbols;
        this.functionSymbols = functionSymbols;
        this.constantSymbols = constantSymbols;
    }

    public void add(Vocabulary v) {
        relationSymbols.addAll(v.getRelationSymbols());
        functionSymbols.addAll(v.getFunctionSymbols());
        constantSymbols.addAll(v.getConstantSymbols());
    }

    public Set<Symbol> getAll() {
        Set<Symbol> symbols = relationSymbols;
        symbols.addAll(functionSymbols);
        symbols.addAll(constantSymbols);
        return symbols;
    }

    public Set<Symbol> getRelationSymbols() {
        return relationSymbols;
    }

    public Set<Symbol> getFunctionSymbols() {
        return functionSymbols;
    }

    public Set<Symbol> getConstantSymbols() {
        return constantSymbols;
    }
}
