package com.dezeus.delta.core;

public class UniverseObject {

    private Object obj;
    private Universe universe;

    public UniverseObject(Object obj) {
        this.obj = obj;
    }

    public Object getObject() {
        return obj;
    }

    public Universe getUniverse() {
        return universe;
    }
}