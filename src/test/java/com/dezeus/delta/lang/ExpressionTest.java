package com.dezeus.delta.lang;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dezeus.delta.exception.InvalidExpressionException;

public class ExpressionTest {

    private Language l = new Language();
    private Symbol v1 = new Symbol("v1", Symbol.Type.VARIABLE);
    private Symbol v2 = new Symbol("v2", Symbol.Type.VARIABLE);

    @Test
    public void testExpression() throws InvalidExpressionException {
        new Expression(l, v1);
        new Expression(l, v2);
        new Expression(l, v1, v2);
        new Expression(l, Symbol.IMPLIES);
    }
}
