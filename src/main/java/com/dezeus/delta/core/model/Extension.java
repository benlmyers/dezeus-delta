package com.dezeus.delta.core.model;

import java.util.HashSet;
import java.util.Set;

import com.dezeus.delta.core.element.Element;

public class Extension extends Model {

    Model base;
    Set<Element> extension;
    private Set<FunctionInterpretation> newFunctionInterpretations;
    private Set<RelationInterpretation> newRelationInterpretations;
    private Set<ConstantInterpretation> newConstantInterpretations;

    public Extension(
            Model base,
            Set<Element> extension,
            Set<FunctionInterpretation> newFunctionInterpretations,
            Set<RelationInterpretation> newRelationInterpretations,
            Set<ConstantInterpretation> newConstantInterpretations) {
        super(base.getLanguage(), base.getUniverse(), base.getFunctionInterpretations(),
                base.getRelationInterpretations(), base.getConstantInterpretations());
        this.extension = extension;
        this.newFunctionInterpretations = newFunctionInterpretations;
        this.newRelationInterpretations = newRelationInterpretations;
        this.newConstantInterpretations = newConstantInterpretations;
    }

    public Embedding getIsomorphism() throws Embedding.InvalidException {
        return new Embedding(base, this, args -> {
            return new Element(0);
        });
    }

    @Override
    public Universe getUniverse() {
        Universe total = new Universe(super.getUniverse().getElements());
        total.addAll(extension);
        return total;
    }

    @Override
    public Set<FunctionInterpretation> getFunctionInterpretations() {
        Set<FunctionInterpretation> total = new HashSet<>();
        total.addAll(super.getFunctionInterpretations());
        total.addAll(newFunctionInterpretations);
        return total;
    }

    @Override
    public Set<RelationInterpretation> getRelationInterpretations() {
        Set<RelationInterpretation> total = new HashSet<>();
        total.addAll(super.getRelationInterpretations());
        total.addAll(newRelationInterpretations);
        return total;
    }

    @Override
    public Set<ConstantInterpretation> getConstantInterpretations() {
        Set<ConstantInterpretation> total = new HashSet<>();
        total.addAll(super.getConstantInterpretations());
        total.addAll(newConstantInterpretations);
        return total;
    }
}
