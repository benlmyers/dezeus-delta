package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import com.dezeus.delta.exception.InvalidSymbolException;

public class Vocabulary {

    private Set<Vocabulary> dependencies;
    private Set<Symbol> symbols;
    private Function<Symbol, Integer> arityMap;

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

    public Set<Symbol> getAllSymbols() {
        Set<Symbol> allSymbols = new HashSet<>(symbols);
        for (Vocabulary dependency : dependencies) {
            allSymbols.addAll(dependency.getAllSymbols());
        }
        return allSymbols;
    }

    public int getArity(Symbol symbol) throws InvalidSymbolException {
        if (symbols.contains(symbol)) {
            return arityMap.apply(symbol);
        } else {
            for (Vocabulary dependency : dependencies) {
                if (dependency.getAllSymbols().contains(symbol)) {
                    return dependency.getArity(symbol);
                }
            }
            throw new InvalidSymbolException(symbol);
        }
    }

    public Set<Vocabulary> getDependencies() {
        return dependencies;
    }
}
