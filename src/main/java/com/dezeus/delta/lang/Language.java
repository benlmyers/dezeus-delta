package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;

public class Language {

    private Set<Vocabulary> dependencies;
    private Vocabulary vocabulary;
    private Set<Symbol> variables;

    public Set<Symbol> getSymbols() {
        Set<Symbol> symbols = new HashSet<>();
        symbols.addAll(vocabulary.getAll());
        symbols.addAll(variables);
        return symbols;
    }

    public Vocabulary getFullVocabulary() {
        Vocabulary v = new Vocabulary();
        v.add(getVocabulary());
        for (Vocabulary dependency : getDependencies()) {
            v.add(dependency);
        }
        return v;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public Set<Vocabulary> getDependencies() {
        return dependencies;
    }

    public Set<Symbol> getVariables() {
        return variables;
    }
}
