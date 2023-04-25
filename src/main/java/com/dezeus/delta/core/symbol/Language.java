package com.dezeus.delta.core.symbol;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a formal language consisting of function symbols, relation
 * symbols, and constant symbols.
 */
public class Language {

    private Optional<Language> base;

    /**
     * The set of function symbols in the language.
     */
    private Set<Symbol> functionSymbols;

    /**
     * The set of relation symbols in the language.
     */
    private Set<Symbol> relationSymbols;

    /**
     * The set of constant symbols in the language.
     */
    private Set<Symbol> constantSymbols;

    /**
     * The set of variable symbols in the language.
     */
    private Set<Symbol> variableSymbols;

    private Map<Symbol, Integer> arityMap;

    /**
     * Constructs a new Language object with the specified function symbols,
     * relation symbols, and constant symbols.
     *
     * @param functionSymbols the set of function symbols
     * @param relationSymbols the set of relation symbols
     * @param constantSymbols the set of constant symbols
     * @param variableSymbols the set of variable symbols
     */
    public Language(
            Set<Symbol> functionSymbols,
            Set<Symbol> relationSymbols,
            Set<Symbol> constantSymbols,
            Set<Symbol> variableSymbols,
            Map<Symbol, Integer> arityMap) {
        this.functionSymbols = functionSymbols;
        this.relationSymbols = relationSymbols;
        this.constantSymbols = constantSymbols;
        this.variableSymbols = variableSymbols;
        this.arityMap = arityMap;
    }

    /**
     * Constructs a new Language object with the specified function symbols,
     * relation symbols, and constant symbols.
     *
     * @param functionSymbols the set of function symbols
     * @param relationSymbols the set of relation symbols
     * @param constantSymbols the set of constant symbols
     * @param variableSymbols the set of variable symbols
     */
    public Language(
            Language base,
            Set<Symbol> functionSymbols,
            Set<Symbol> relationSymbols,
            Set<Symbol> constantSymbols,
            Set<Symbol> variableSymbols,
            Map<Symbol, Integer> arityMap) {
        this(functionSymbols, relationSymbols, constantSymbols, variableSymbols, arityMap);
        this.base = Optional.ofNullable(base);
    }

    /**
     * Returns the set of function symbols in the language.
     *
     * @return the set of function symbols
     */
    public Set<Symbol> getFunctionSymbols() {
        Set<Symbol> result = new HashSet<>();
        base.ifPresent(b -> result.addAll(b.getFunctionSymbols()));
        return functionSymbols;
    }

    /**
     * Returns the set of relation symbols in the language.
     *
     * @return the set of relation symbols
     */
    public Set<Symbol> getRelationSymbols() {
        Set<Symbol> result = new HashSet<>();
        base.ifPresent(b -> result.addAll(b.getRelationSymbols()));
        return relationSymbols;
    }

    /**
     * Returns the set of constant symbols in the language.
     *
     * @return the set of constant symbols
     */
    public Set<Symbol> getConstantSymbols() {
        Set<Symbol> result = new HashSet<>();
        base.ifPresent(b -> result.addAll(b.getConstantSymbols()));
        return constantSymbols;
    }

    /**
     * Returns the set of variable symbols in the language.
     *
     * @return the set of variable symbols
     */
    public Set<Symbol> getVariableSymbols() {
        Set<Symbol> result = new HashSet<>();
        base.ifPresent(b -> result.addAll(b.getVariableSymbols()));
        return variableSymbols;
    }

    /**
     * Returns the set of all symbols in the language.
     *
     * @return the set of all symbols
     */
    public Set<Symbol> getSymbols() {
        Set<Symbol> symbols = new HashSet<>();
        base.ifPresent(b -> symbols.addAll(b.getSymbols()));
        symbols.addAll(functionSymbols);
        symbols.addAll(relationSymbols);
        symbols.addAll(constantSymbols);
        return symbols;
    }

    /**
     * Returns the arity of the specified function symbol.
     *
     * @param symbol the function symbol
     * @return the arity of the function symbol
     */
    public Map<Symbol, Integer> getArityMap() {
        return arityMap;
    }
}
