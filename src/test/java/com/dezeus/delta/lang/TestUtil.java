package com.dezeus.delta.lang;

public class TestUtil {

    final Symbol v1 = new Variable("v1");
    final Symbol v2 = new Variable("v2");
    final Symbol a = new Symbol("a", Symbol.Type.CONSTANT);
    final Symbol b = new Symbol("b", Symbol.Type.CONSTANT);
    final Symbol c = new Symbol("c", Symbol.Type.CONSTANT);
    final Symbol F = new Symbol("F", Symbol.Type.FUNCTION);
    final Symbol R = new Symbol("R", Symbol.Type.PREDICATE);
    final Language l = new Language();
    final Language l1 = new Language(new Vocabulary(a, b));
    final Language l2 = new Language(new Vocabulary(a, b, c, F, R));

    public TestUtil() {
        // Do nothing
    }
}
