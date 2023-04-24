package com.dezeus.delta.core;

import java.util.Set;

import com.dezeus.delta.core.model.ConstantInterpretation;
import com.dezeus.delta.core.model.FunctionInterpretation;
import com.dezeus.delta.core.model.RelationInterpretation;
import com.dezeus.delta.core.symbol.FunctionSymbol;
import com.dezeus.delta.core.symbol.Symbol;

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

    public FunctionInterpretation getFunctionInterpretation(FunctionSymbol symbol) throws NoInterpretationException {
        for (FunctionInterpretation interpretation : functionInterpretations) {
            if (interpretation.getSymbol().equals(symbol)) {
                return interpretation;
            }
        }
        throw new NoInterpretationException(symbol);
    }

    public RelationInterpretation getRelationInterpretation(Symbol symbol) throws NoInterpretationException {
        for (RelationInterpretation interpretation : relationInterpretations) {
            if (interpretation.getSymbol().equals(symbol)) {
                return interpretation;
            }
        }
        throw new NoInterpretationException(symbol);
    }

    public ConstantInterpretation getConstantInterpretation(Symbol symbol) throws NoInterpretationException {
        for (ConstantInterpretation interpretation : constantInterpretations) {
            if (interpretation.getSymbol().equals(symbol)) {
                return interpretation;
            }
        }
        throw new NoInterpretationException(symbol);
    }

    public class NoInterpretationException extends Exception {
        public NoInterpretationException(Symbol symbol) {
            super("No interpretation for symbol \"" + symbol.getLiteral() + "\" in model.");
        }
    }
}
