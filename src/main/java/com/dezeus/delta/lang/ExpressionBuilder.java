package com.dezeus.delta.lang;

import java.util.ArrayList;
import java.util.List;

import com.dezeus.delta.exception.InvalidExpressionException;
import com.dezeus.delta.exception.NoSymbolException;

public class ExpressionBuilder {

    private Language language;

    public ExpressionBuilder(Language language) {
        this.language = language;
    }

    public Expression build(String s) throws InvalidExpressionException {
        List<Symbol> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                continue;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(j) == ' ')
                    continue;
                String sub = s.substring(i, j);
                try {
                    if (language.getSymbol(sub) != null) {
                        list.add(language.getSymbol(sub));
                    }
                } catch (NoSymbolException _0) {
                    throw new InvalidExpressionException(s);
                }
            }
        }
        return new Expression(language, list);
    }
}