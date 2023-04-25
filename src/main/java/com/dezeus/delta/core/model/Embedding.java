package com.dezeus.delta.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.dezeus.delta.core.Model;
import com.dezeus.delta.core.Model.NoInterpretationException;
import com.dezeus.delta.core.element.Element;
import com.dezeus.delta.core.element.ElementwiseOperation;
import com.dezeus.delta.core.element.ElementwiseRelation;
import com.dezeus.delta.core.symbol.ConstantSymbol;
import com.dezeus.delta.core.symbol.FunctionSymbol;
import com.dezeus.delta.core.symbol.RelationSymbol;
import com.dezeus.delta.util.ListUtil;

public class Embedding {

    private static boolean preservesConstantInterpretation(Model m, Model n, ElementwiseOperation h)
            throws NoInterpretationException {
        Set<ConstantSymbol> constants = m.getLanguage().getConstantSymbols();
        for (ConstantSymbol constant : constants) {
            Element cM = m.getConstantInterpretation(constant).getConstant();
            Element cN = n.getConstantInterpretation(constant).getConstant();
            List<Element> singletonArgument = new ArrayList<>();
            singletonArgument.add(cM);
            if (!cN.equals(h.apply(singletonArgument))) {
                return false;
            }
        }
        return true;
    }

    private static boolean preservesRelationInterpretation(Model m, Model n, ElementwiseOperation h)
            throws NoInterpretationException {
        Set<RelationSymbol> relations = m.getLanguage().getRelationSymbols();
        for (RelationSymbol relation : relations) {
            ElementwiseRelation rM = m.getRelationInterpretation(relation).getRelation();
            ElementwiseRelation rN = n.getRelationInterpretation(relation).getRelation();
            Set<List<Element>> combinations = ListUtil.allTuples(relation.getArity(), m.getUniverse().getElements());
            for (List<Element> combination : combinations) {
                boolean lhs = rM.contains(combination);
                List<Element> transformedCombination = transformList(combination, h);
                boolean rhs = rN.contains(transformedCombination);
                if (lhs != rhs) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean preservesFunctionInterpretation(Model m, Model n, ElementwiseOperation h)
            throws NoInterpretationException {
        Set<FunctionSymbol> functions = m.getLanguage().getFunctionSymbols();
        for (FunctionSymbol function : functions) {
            ElementwiseOperation fM = m.getFunctionInterpretation(function).getFunction();
            ElementwiseOperation fN = n.getFunctionInterpretation(function).getFunction();
            Set<List<Element>> combinations = ListUtil.allTuples(function.getArity(), m.getUniverse().getElements());
            for (List<Element> combination : combinations) {
                Element transformed = fM.apply(combination);
                List<Element> singletonArgument = new ArrayList<>();
                singletonArgument.add(transformed);
                Element lhs = h.apply(singletonArgument);
                List<Element> _transformed = new ArrayList<>(combination);
                Element rhs = fN.apply(_transformed);
                if (!lhs.equals(rhs)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Element> transformList(List<Element> original, ElementwiseOperation h) {
        List<Element> transformed = new ArrayList<>(original);
        for (int i = 0; i < original.size(); i++) {
            List<Element> singletonArgument = new ArrayList<>();
            singletonArgument.add(original.get(i));
            transformed.set(i, h.apply(singletonArgument));
        }
        return transformed;
    }

    private Model domain;
    private Model codomain;
    private ElementwiseOperation function;

    public Embedding(Model domain, Model codomain, ElementwiseOperation function) throws InvalidEmbeddingException {
        this.domain = domain;
        this.codomain = codomain;
        this.function = function;
        try {
            if (!preservesConstantInterpretation(domain, codomain, function)) {
                throw new InvalidEmbeddingException("Constant interpretation is not preserved");
            }
            if (!preservesRelationInterpretation(domain, codomain, function)) {
                throw new InvalidEmbeddingException("Relation interpretation is not preserved");
            }
            if (!preservesFunctionInterpretation(domain, codomain, function)) {
                throw new InvalidEmbeddingException("Function interpretation is not preserved");
            }
        } catch (NoInterpretationException e) {
            throw new InvalidEmbeddingException("No interpretation found");
        }
    }

    public Model getDomain() {
        return domain;
    }

    public Model getCodomain() {
        return codomain;
    }

    public ElementwiseOperation getFunction() {
        return function;
    }

    public class InvalidEmbeddingException extends Exception {
        public InvalidEmbeddingException(String reason) {
            super("This embedding is invalid. (" + reason + ")");
        }
    }
}
