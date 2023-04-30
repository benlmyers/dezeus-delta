package com.dezeus.delta.lang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionTest {

    private Symbol v1 = new Symbol("v1", Symbol.Type.VARIABLE);
    private Symbol v2 = new Symbol("v2", Symbol.Type.VARIABLE);
    private Symbol a = new Symbol("a", Symbol.Type.CONSTANT);
    private Symbol b = new Symbol("b", Symbol.Type.CONSTANT);
    private Symbol c = new Symbol("c", Symbol.Type.CONSTANT);
    private Language l = new Language();
    private Language l1 = new Language(new Vocabulary(a, b));

    private Expression e1, e2, e3, e4, e5, e6;

    @Test
    public void testCreateExpression() throws InvalidExpressionException {
        e1 = new Expression(l, v1);
        e2 = new Expression(l, v1, Symbol.EQUALS, v2);
        e3 = new Expression(l, Symbol.NOT, Symbol.LEFT_PAREN);
        e4 = new Expression(l1, a, b);
        e5 = new Expression(l1, Symbol.NOT, Symbol.LEFT_PAREN, a, Symbol.EQUALS, b, Symbol.RIGHT_PAREN);
    }

    @Test(expected = InvalidExpressionException.class)
    public void textInvalidExpression() throws Exception {
        new Expression(l, v1, a);
        new Expression(l, a);
        new Expression(l1, c);
    }

    @Test
    public void testSubExpression() throws InvalidExpressionException {
        testCreateExpression();
        Expression e7 = e5.subExpression(1, 6);
        System.out.println(e7);
        assertTrue(e7.equals(new Expression(l1, Symbol.LEFT_PAREN, a, Symbol.EQUALS, b, Symbol.RIGHT_PAREN)));
    }
}
