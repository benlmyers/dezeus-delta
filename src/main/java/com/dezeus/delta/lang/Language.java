package com.dezeus.delta.lang;

import java.util.HashSet;
import java.util.Set;

import com.dezeus.delta.logic.FirstOrderLanguage;

public class Language {

    private Set<Vocabulary> dependencies;
    private Vocabulary vocabulary;
    private Set<Symbol> variables;

    public Language(Vocabulary vocabulary, Set<Vocabulary> dependencies, Set<Symbol> variables) {
        this.vocabulary = vocabulary;
        this.dependencies = dependencies;
        this.variables = variables;
    }

    public Language(Vocabulary vocabulary, Set<Symbol> variables) {
        this(vocabulary, new HashSet<>(), variables);
    }

    public Set<Symbol> getSymbols() {
        Set<Symbol> symbols = new HashSet<>();
        symbols.addAll(FirstOrderLanguage.L0());
        symbols.addAll(getFullVocabulary().getAll());
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
