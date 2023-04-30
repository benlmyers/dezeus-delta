package com.dezeus.delta.model;

import com.dezeus.delta.logic.AryFunction;
import com.dezeus.delta.logic.AryPredicate;
import com.dezeus.delta.set.DezeusSet;

public class Model {

    DezeusSet<Object> universe;
    DezeusSet<AryPredicate<Object>> Predicates;
    DezeusSet<AryFunction<Object, Object>> functions;
    DezeusSet<Object> constants;
}
