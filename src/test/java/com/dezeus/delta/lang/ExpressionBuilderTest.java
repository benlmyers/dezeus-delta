package com.dezeus.delta.lang;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionBuilderTest {

    private TestUtil t = new TestUtil();

    @Test
    public void testBuildExpression() throws InvalidExpressionException {
        ExpressionBuilder b = new ExpressionBuilder(t.l);
        b.build("a");
        b.build("ab");
        b.build("a b c");
        b.build("a=b");
        b.build("a = b = c");
    }

    @Test(expected = InvalidExpressionException.class)
    public void testBuildInvalid() throws InvalidExpressionException {
        ExpressionBuilder b = new ExpressionBuilder(t.l);
        b.build("z");
    }

    @Test
    public void testWellFormed() throws InvalidExpressionException {
        ExpressionBuilder b = new ExpressionBuilder(t.l2);
        assertTrue(b.build("a").isConstant());
        assertTrue(b.build("(a=b)").isEquality());
        assertTrue(b.build("F(a)").isFunction());
        assertTrue(b.build("R(a,b)").isPredicate());
        assertTrue(b.build("R(a,b,c)").isFormula());
        assertTrue(b.build("R(a,b,c,F(a))").isPredicate());
        assertTrue(b.build("R(a,b,c,F(a),F(b))").isPredicate());
        assertTrue(b.build("(¬(a=b))").isNegation());
        assertTrue(b.build("(¬(¬(R(a,b))))").isNegation());
        assertTrue(b.build("(R(a)→R(b))").isImplication());
    }
}
