package com.dezeus.delta.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionTest {

    private TestUtil t = new TestUtil();

    private Expression e1, e2, e3, e4, e5;

    @Test
    public void testCreateExpression() throws InvalidExpressionException {
        e1 = new Expression(t.l, t.v1);
        e2 = new Expression(t.l, t.v1, Symbol.EQUALS, t.v2);
        e3 = new Expression(t.l, Symbol.LEFT_PAREN, Symbol.NOT, t.v1, Symbol.RIGHT_PAREN);
        e4 = new Expression(t.l1, t.a, t.b);
        e5 = new Expression(t.l1, Symbol.LEFT_PAREN, Symbol.NOT, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b,
                Symbol.RIGHT_PAREN, Symbol.RIGHT_PAREN);
        assertTrue(e1.equals(e1));
    }

    @Test(expected = InvalidExpressionException.class)
    public void textInvalidExpression() throws Exception {
        new Expression(t.l, t.v1, t.a);
        new Expression(t.l, t.a);
        new Expression(t.l1, t.c);
    }

    @Test
    public void testSubExpression() throws InvalidExpressionException {
        testCreateExpression();
        Expression e7 = e5.subExpression(2, 7);
        assertTrue(e7.equals(new Expression(t.l1, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b, Symbol.RIGHT_PAREN)));
        Expression e8 = e4.subExpression(0, 1);
        assertTrue(e8.equals(new Expression(t.l1, t.a)));
    }

    @Test
    public void testIsTerm() throws InvalidExpressionException {
        testCreateExpression();
        assertTrue(e1.isTerm());
        assertFalse(e2.isTerm());
        assertFalse(e3.isTerm());
        assertFalse(e4.isTerm());
        assertFalse(e5.isTerm());
        Expression e6 = new Expression(t.l2, t.F, Symbol.LEFT_PAREN, t.v1, Symbol.RIGHT_PAREN);
        assertTrue(e6.isTerm());
        Expression e7 = new Expression(t.l2, t.F, Symbol.LEFT_PAREN, t.c, Symbol.RIGHT_PAREN);
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
        Expression e6 = new Expression(t.l2, t.R, Symbol.LEFT_PAREN, t.v1, Symbol.RIGHT_PAREN);
        assertTrue(e6.isFormula());
        Expression e7 = new Expression(t.l2, t.R, Symbol.LEFT_PAREN, t.c, Symbol.RIGHT_PAREN);
        assertTrue(e7.isFormula());
        Expression e8 = new Expression(t.l2, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.c, Symbol.RIGHT_PAREN);
        assertTrue(e8.isFormula());
    }

    @Test
    public void testIsConstant() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isConstant());
        Expression e6 = new Expression(t.l1, t.a);
        assertTrue(e6.isConstant());
        Expression e7 = new Expression(t.l2, t.c);
        assertTrue(e7.isConstant());
    }

    @Test
    public void testIsVariable() throws InvalidExpressionException {
        testCreateExpression();
        assertTrue(e1.isVariable());
        assertFalse(e4.isVariable());
        Expression e6 = new Expression(t.l2, t.v2);
        assertTrue(e6.isVariable());
    }

    @Test
    public void testIsEquality() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isEquality());
        assertFalse(e2.isEquality());
        assertFalse(e3.isEquality());
        assertFalse(e4.isEquality());
        assertFalse(e5.isEquality());
        Expression e6 = new Expression(t.l2, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b, Symbol.RIGHT_PAREN);
        assertTrue(e6.isEquality());
        Expression e7 = new Expression(t.l2, Symbol.LEFT_PAREN, t.F, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN,
                Symbol.EQUALS, t.c, Symbol.RIGHT_PAREN);
        assertTrue(e7.isEquality());
    }

    @Test
    public void testIsNegation() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isNegation());
        assertFalse(e2.isNegation());
        assertFalse(e3.isNegation());
        assertFalse(e4.isNegation());
        assertTrue(e5.isNegation());
        Expression e6 = new Expression(t.l2, Symbol.LEFT_PAREN, Symbol.NOT, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b,
                Symbol.RIGHT_PAREN, Symbol.RIGHT_PAREN);
        assertTrue(e6.isNegation());
        Expression e7 = new Expression(t.l2, Symbol.LEFT_PAREN, Symbol.NOT, t.R, Symbol.LEFT_PAREN, t.a,
                Symbol.RIGHT_PAREN,
                Symbol.RIGHT_PAREN);
        assertTrue(e7.isNegation());
    }

    @Test
    public void testIsImplication() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isImplication());
        assertFalse(e2.isImplication());
        assertFalse(e3.isImplication());
        assertFalse(e4.isImplication());
        assertFalse(e5.isImplication());
        Expression e6 = new Expression(t.l2, Symbol.LEFT_PAREN, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b,
                Symbol.RIGHT_PAREN, Symbol.IMPLIES, Symbol.LEFT_PAREN, t.b, Symbol.EQUALS, t.c, Symbol.RIGHT_PAREN,
                Symbol.RIGHT_PAREN);

        assertTrue(e6.isImplication());
        Expression e7 = new Expression(t.l2, Symbol.LEFT_PAREN, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b,
                Symbol.RIGHT_PAREN, Symbol.IMPLIES, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b, Symbol.RIGHT_PAREN,
                Symbol.RIGHT_PAREN);
        assertTrue(e7.isImplication());
    }

    @Test
    public void testIsUniversalInstantiation() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isUniversalInstantiation());
        assertFalse(e2.isUniversalInstantiation());
        assertFalse(e3.isUniversalInstantiation());
        assertFalse(e4.isUniversalInstantiation());
        assertFalse(e5.isUniversalInstantiation());
        Expression e6 = new Expression(t.l1, Symbol.FOR_ALL, t.v1, Symbol.LEFT_PAREN, t.a, Symbol.EQUALS, t.b,
                Symbol.RIGHT_PAREN);
        System.out.println(e6);
        assertTrue(e6.isUniversalInstantiation());
        Expression e7 = new Expression(t.l2, Symbol.FOR_ALL, t.v1, Symbol.LEFT_PAREN, Symbol.NOT, t.R,
                Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN, Symbol.RIGHT_PAREN);
        assertTrue(e7.isUniversalInstantiation());
    }

    @Test
    public void testIsFunction() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isFunction());
        assertFalse(e2.isFunction());
        assertFalse(e3.isFunction());
        assertFalse(e4.isFunction());
        assertFalse(e5.isFunction());
        Expression e6 = new Expression(t.l2, t.F, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN);
        assertTrue(e6.isFunction());
        Expression e7 = new Expression(t.l2, Symbol.NOT, t.F, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN);
        assertFalse(e7.isFunction());
        Expression e8 = new Expression(t.l2, t.F, Symbol.LEFT_PAREN, t.F, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN,
                Symbol.RIGHT_PAREN);
        assertTrue(e8.isFunction());
    }

    @Test
    public void testIsPredicate() throws InvalidExpressionException {
        testCreateExpression();
        assertFalse(e1.isPredicate());
        assertFalse(e2.isPredicate());
        assertFalse(e3.isPredicate());
        assertFalse(e4.isPredicate());
        assertFalse(e5.isPredicate());
        Expression e6 = new Expression(t.l2, t.R, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN);
        assertTrue(e6.isPredicate());
        Expression e7 = new Expression(t.l2, Symbol.NOT, t.R, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN);
        assertFalse(e7.isPredicate());
        Expression e8 = new Expression(t.l2, t.R, Symbol.LEFT_PAREN, t.F, Symbol.LEFT_PAREN, t.a, Symbol.RIGHT_PAREN,
                Symbol.RIGHT_PAREN);
        assertTrue(e8.isPredicate());
    }
}
