package com.dezeus.delta.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExpressionTest {

    private Symbol v1 = new Symbol("v1", Symbol.Type.VARIABLE);
    private Symbol v2 = new Symbol("v2", Symbol.Type.VARIABLE);
    private Symbol a1 = new Symbol("a1", Symbol.Type.CONSTANT);
    private Symbol a2 = new Symbol("a2", Symbol.Type.CONSTANT);

    @Test
    public void testIsConstant() {
        Expression e1 = new Expression(a1);
        Expression e2 = new Expression(a2);
        Expression e3 = new Expression(v1);
        assertTrue(e1.isConstant());
        assertTrue(e2.isConstant());
        assertFalse(e3.isConstant());
    }
}
