package com.dezeus.delta.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SymbolTest {

    @Test
    public void testSymbolEquality() {
        Symbol a1 = new Symbol("a", Symbol.Type.VARIABLE);
        Symbol a2 = new Symbol("a", Symbol.Type.VARIABLE);
        Symbol b1 = new Symbol("b", Symbol.Type.VARIABLE);
        Symbol b2 = new Symbol("b", Symbol.Type.CONSTANT);
        assertTrue(a1.equals(a2));
        assertFalse(a1.equals(b1));
        assertFalse(b1.equals(b2));
        assertFalse(a1.equals(b2));
    }
}
