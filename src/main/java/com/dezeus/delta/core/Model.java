package com.dezeus.delta.core;

import java.util.Set;

import com.dezeus.delta.core.model.ConstantInterpretation;
import com.dezeus.delta.core.model.FunctionInterpretation;
import com.dezeus.delta.core.model.RelationInterpretation;

public class Model {

    private Language language;
    private Universe universe;
    private Set<FunctionInterpretation> functionInterpretations;
    private Set<RelationInterpretation> relationInterpretations;
    private Set<ConstantInterpretation> constantInterpretations;

    public Model(
            Language language,
            Universe universe,
            Set<FunctionInterpretation> functionInterpretations,
            Set<RelationInterpretation> relationInterpretations,
            Set<ConstantInterpretation> constantInterpretations) {
        this.language = language;
        this.universe = universe;
        this.functionInterpretations = functionInterpretations;
        this.relationInterpretations = relationInterpretations;
        this.constantInterpretations = constantInterpretations;
    }

    public Language getLanguage() {
        return language;
    }

    public Universe getUniverse() {
        return universe;
    }

    public Set<FunctionInterpretation> getFunctionInterpretations() {
        return functionInterpretations;
    }

    public Set<RelationInterpretation> getRelationInterpretations() {
        return relationInterpretations;
    }

    public Set<ConstantInterpretation> getConstantInterpretations() {
        return constantInterpretations;
    }
}
