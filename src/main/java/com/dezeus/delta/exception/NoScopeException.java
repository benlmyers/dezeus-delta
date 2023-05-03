package com.dezeus.delta.exception;

import com.dezeus.delta.lang.Formula;
import com.dezeus.delta.lang.Variable;

public class NoScopeException extends Exception {

    public NoScopeException(Variable v, Formula f) {
        super("No scope for variable \"" + v.getLiteral() + "\" found within " + f.toString() + ".");
    }
}
