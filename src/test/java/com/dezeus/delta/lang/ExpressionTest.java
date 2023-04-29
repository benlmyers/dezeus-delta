package com.dezeus.delta.lang;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionTest {

    private Symbol v1 = new Symbol("v1", Symbol.Type.VARIABLE);
    private Symbol v2 = new Symbol("v2", Symbol.Type.VARIABLE);
    private Symbol a = new Symbol("a", Symbol.Type.CONSTANT);
    private Symbol b = new Symbol("b", Symbol.Type.CONSTANT);
    private Language l = new Language();
    private Language l1 = new Language(new Vocabulary(a, b));

    @Test
    public void testExpression() throws InvalidExpressionException {
        new Expression(l, v1);
        new Expression(l, v2);
        new Expression(l, v1, Symbol.IMPLIES, v2);
        new Expression(l1, a);
        new Expression(l1, b);
    }

    @Test(expected = InvalidExpressionException.class)
    public void textInvalidExpression() throws Exception {
        new Expression(l, v1, a);
    }
}
