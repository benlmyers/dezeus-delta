package com.dezeus.delta.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionTest {

    private Symbol v1 = new Variable("v1");
    private Symbol v2 = new Variable("v2");
    private Symbol a = new Symbol("a", Symbol.Type.CONSTANT);
    private Symbol b = new Symbol("b", Symbol.Type.CONSTANT);
    private Symbol c = new Symbol("c", Symbol.Type.CONSTANT);
    private Symbol F = new Symbol("F", Symbol.Type.FUNCTION);
    private Symbol R = new Symbol("R", Symbol.Type.PREDICATE);
    private Language l = new Language();
    private Language l1 = new Language(new Vocabulary(a, b));
    private Language l2 = new Language(new Vocabulary(a, b, c, F, R));

    private Expression e1, e2, e3, e4, e5;

    @Test
    public void testCreateExpression() throws InvalidExpressionException {
        e1 = new Expression(l, v1);
        e2 = new Expression(l, v1, Symbol.EQUALS, v2);
        e3 = new Expression(l, Symbol.LEFT_PAREN, Symbol.NOT, v1, Symbol.RIGHT_PAREN);
        e4 = new Expression(l1, a, b);
        e5 = new Expression(l1, Symbol.LEFT_PAREN, Symbol.NOT, Symbol.LEFT_PAREN, a, Symbol.EQUALS, b,
                Symbol.RIGHT_PAREN, Symbol.RIGHT_PAREN);
        assertTrue(e1.equals(e1));
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
        Expression e7 = e5.subExpression(2, 7);
        assertTrue(e7.equals(new Expression(l1, Symbol.LEFT_PAREN, a, Symbol.EQUALS, b, Symbol.RIGHT_PAREN)));
        Expression e8 = e4.subExpression(0, 1);
        assertTrue(e8.equals(new Expression(l1, a)));
    }

    @Test
    public void testIsTerm() throws InvalidExpressionException {
        testCreateExpression();
        assertTrue(e1.isTerm());
        assertFalse(e2.isTerm());
        assertFalse(e3.isTerm());
        assertFalse(e4.isTerm());
        assertFalse(e5.isTerm());
        Expression e6 = new Expression(l2, F, Symbol.LEFT_PAREN, v1, Symbol.RIGHT_PAREN);
        assertTrue(e6.isTerm());
        Expression e7 = new Expression(l2, F, Symbol.LEFT_PAREN, c, Symbol.RIGHT_PAREN);
        assertTrue(e7.isTerm());
    }

    @Test
    public void testIsFormula() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isFormula());
        assertFalse(e2.isFormula());
        assertFalse(e3.isFormula());
        assertFalse(e4.isFormula());
        assertTrue(e5.isFormula());
        Expression e6 = new Expression(l2, R, Symbol.LEFT_PAREN, v1, Symbol.RIGHT_PAREN);
        assertTrue(e6.isFormula());
        Expression e7 = new Expression(l2, R, Symbol.LEFT_PAREN, c, Symbol.RIGHT_PAREN);
        assertTrue(e7.isFormula());
        Expression e8 = new Expression(l2, Symbol.LEFT_PAREN, a, Symbol.EQUALS, c, Symbol.RIGHT_PAREN);
        assertTrue(e8.isFormula());
    }

    @Test
    public void testIsConstant() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isConstant());
        Expression e6 = new Expression(l1, a);
        assertTrue(e6.isConstant());
        Expression e7 = new Expression(l2, c);
        assertTrue(e7.isConstant());
    }

    @Test
    public void testIsVariable() throws InvalidExpressionException {
        testCreateExpression();
        assertTrue(e1.isVariable());
        assertFalse(e4.isVariable());
        Expression e6 = new Expression(l2, v2);
        assertTrue(e6.isVariable());
    }

    @Test
    public void testIsEquality() throws InvalidExpressionException {
        // TODO
    }
}
