package com.dezeus.delta.logic;

import java.util.HashSet;
import java.util.Set;

import com.dezeus.delta.lang.Symbol;

public class FirstOrderLanguage {

    public static final Symbol LEFT_PAREN = new Symbol("(", Symbol.Type.GROUPING);
    public static final Symbol RIGHT_PAREN = new Symbol(")", Symbol.Type.GROUPING);
    public static final Symbol EQUALITY = new Symbol("=", Symbol.Type.RELATION);
    public static final Symbol NOT = new Symbol("¬", Symbol.Type.FUNCTION);
    public static final Symbol IMPLIES = new Symbol("→", Symbol.Type.FUNCTION);

    public static final Set<Symbol> L0() {
        Set<Symbol> result = new HashSet<>();
        result.add(LEFT_PAREN);
        result.add(RIGHT_PAREN);
        result.add(EQUALITY);
        result.add(NOT);
        result.add(IMPLIES);
        return result;
    }
}
