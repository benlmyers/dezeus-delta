package com.dezeus.delta.model;

import com.dezeus.delta.logic.AryFunction;
import com.dezeus.delta.logic.AryRelation;
import com.dezeus.delta.set.DezeusSet;

public class Model {

    DezeusSet<Object> universe;
    DezeusSet<AryRelation<Object>> relations;
    DezeusSet<AryFunction<Object, Object>> functions;
    DezeusSet<Object> constants;
}
