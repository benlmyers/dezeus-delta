package com.dezeus.delta.core.symbol;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a formal language consisting of function symbols, relation
 * symbols, and constant symbols.
 */
public class Language {

    /**
     * The set of function symbols in the language.
     */
    private Set<FunctionSymbol> functionSymbols;

    /**
     * The set of relation symbols in the language.
     */
    private Set<RelationSymbol> relationSymbols;

    /**
     * The set of constant symbols in the language.
     */
    private Set<ConstantSymbol> constantSymbols;

    /**
     * Constructs a new Language object with the specified function symbols,
     * relation symbols, and constant symbols.
     *
     * @param functionSymbols the set of function symbols
     * @param relationSymbols the set of relation symbols
     * @param constantSymbols the set of constant symbols
     */
    public Language(
            Set<FunctionSymbol> functionSymbols,
            Set<RelationSymbol> relationSymbols,
            Set<ConstantSymbol> constantSymbols) {
        this.functionSymbols = functionSymbols;
        this.relationSymbols = relationSymbols;
        this.constantSymbols = constantSymbols;
    }

    /**
     * Returns the set of function symbols in the language.
     *
     * @return the set of function symbols
     */
    public Set<FunctionSymbol> getFunctionSymbols() {
        return functionSymbols;
    }

    /**
     * Returns the set of relation symbols in the language.
     *
     * @return the set of relation symbols
     */
    public Set<RelationSymbol> getRelationSymbols() {
        return relationSymbols;
    }

    /**
     * Returns the set of constant symbols in the language.
     *
     * @return the set of constant symbols
     */
    public Set<ConstantSymbol> getConstantSymbols() {
        return constantSymbols;
    }

    /**
     * Determines whether the given sequence of symbols represents a valid term in
     * the language.
     *
     * @param sequence the sequence of symbols to check
     * @return true if the sequence is a valid term, false otherwise
     */
    public boolean isTerm(List<Symbol> sequence) {
        // implementation not provided
        return true;
    }

    /**
     * Returns the set of all symbols in the language.
     *
     * @return the set of all symbols
     */
    public Set<Symbol> getSymbols() {
        Set<Symbol> symbols = new HashSet<>();
        symbols.addAll(functionSymbols);
        symbols.addAll(relationSymbols);
        symbols.addAll(constantSymbols);
        return symbols;
    }
}
