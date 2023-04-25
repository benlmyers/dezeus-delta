package com.dezeus.delta.logic;

import java.util.HashSet;
import java.util.Set;

import com.dezeus.delta.core.symbol.Language;
import com.dezeus.delta.core.symbol.Symbol;

public class Logic {

    public static Symbol NOT = new Symbol("¬");
    public static Symbol IMPLIES = new Symbol("→");

    private static Set<Symbol> L0_functions() {
        Set<Symbol> result = new HashSet<>();
        result.add(NOT);
        result.add(IMPLIES);
        return result;
    }

    public static Language L0 = new Language(
            L0_functions(),
            new HashSet<>(),
            new HashSet<>(),
            new Set,
            null);
}
