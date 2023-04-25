package com.dezeus.delta.model;

import com.dezeus.delta.logic.AryFunction;
import com.dezeus.delta.logic.AryRelation;
import com.dezeus.delta.set.HybridSet;

public class Model {

    HybridSet<Object> universe;
    HybridSet<AryRelation<Object>> relations;
    HybridSet<AryFunction<Object, Object>> functions;
    HybridSet<Object> constants;
}
