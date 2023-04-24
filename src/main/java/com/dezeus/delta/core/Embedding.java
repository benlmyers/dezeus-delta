package com.dezeus.delta.core;

public class Embedding {

    private Model domain;
    private Model codomain;
    private Function function;

    public Embedding(Model domain, Model codomain, Function function) {
        this.domain = domain;
        this.codomain = codomain;
        this.function = function;
    }

    public Model getDomain() {
        return domain;
    }

    public Model getCodomain() {
        return codomain;
    }

    public Function getFunction() {
        return function;
    }
}
