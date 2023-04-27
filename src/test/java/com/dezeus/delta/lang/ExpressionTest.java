package com.dezeus.delta.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionTest {

    private Language l = new Language();
    private Symbol v1 = new Symbol("v1", Symbol.Type.VARIABLE);
    private Symbol v2 = new Symbol("v2", Symbol.Type.VARIABLE);
    private Symbol a = new Symbol("a", Symbol.Type.CONSTANT);

    @Test
    public void testExpression() throws InvalidExpressionException {
        Expression e1 = new Expression(l, v1);
        Expression e2 = new Expression(l, v2);
        Expression e3 = new Expression(l, v1, v2);
        new Expression(l, Symbol.IMPLIES);
        Expression e5 = e1.add(e2);
        assertEquals(e3, e5);
    }

    @Test(expected = InvalidExpressionException.class)
    public void textInvalidExpression() throws Exception {
        new Expression(l, v1, a);
    }
}
