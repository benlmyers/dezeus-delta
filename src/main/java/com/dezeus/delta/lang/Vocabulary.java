package com.dezeus.delta.lang;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vocabulary {

    private Set<Vocabulary> dependencies;
    private Set<Symbol> symbols;

    public Vocabulary() {
        this(new HashSet<>());
    }

    public Vocabulary(Set<Symbol> symbols) {
        this(symbols, new HashSet<>());
    }

    public Vocabulary(Set<Symbol> symbols, Set<Vocabulary> dependencies) {
        this.symbols = symbols;
        this.dependencies = dependencies;
    }

    public Vocabulary(Symbol... symbols) {
        this(new HashSet<>(Arrays.asList(symbols)));
    }

    public Set<Symbol> getAllSymbols() {
        Set<Symbol> allSymbols = new HashSet<>(symbols);
        for (Vocabulary dependency : dependencies) {
            allSymbols.addAll(dependency.getAllSymbols());
        }
        return allSymbols;
    }

    public Set<Vocabulary> getDependencies() {
        return dependencies;
    }
}
