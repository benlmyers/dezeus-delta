package com.dezeus.delta.exception;

import com.dezeus.delta.lang.Formula;

public class NoSubFormulaException extends Exception {

    public NoSubFormulaException(Formula f) {
        super("No subformula was found within " + f.toString() + ".");
    }
}
