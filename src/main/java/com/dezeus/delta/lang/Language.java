package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;

import com.dezeus.delta.exception.NoSymbolException;

public class Language {

    private Vocabulary vocabulary;
    private Set<Symbol> variableSymbols;

    public Language(Vocabulary vocabulary, Set<Symbol> variableSymbols) {
        this.vocabulary = vocabulary;
        this.variableSymbols = variableSymbols;
    }

    public Language(Vocabulary vocabulary) {
        this(vocabulary, new HashSet<>());
    }

    public Language() {
        this(new Vocabulary());
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public Set<Symbol> getVariableSymbols() {
        return variableSymbols;
    }

    public Set<Symbol> getAllSymbols() {
        Set<Symbol> result = new HashSet<>();
        result.addAll(Symbol.BASE_LOGIC());
        result.addAll(vocabulary.getAllSymbols());
        result.addAll(variableSymbols);
        return result;
    }

    public Symbol getSymbol(String literal) throws NoSymbolException {
        for (Symbol s : getAllSymbols()) {
            if (s.getLiteral().equals(literal)) {
                return s;
            }
        }
        throw new NoSymbolException(literal);
    }
}
