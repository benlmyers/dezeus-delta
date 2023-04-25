package com.dezeus.delta.core;

public class Element {

    private Object obj;
    private Universe universe;

    public Element(Object obj) {
        this.obj = obj;
    }

    public Object getObject() {
        return obj;
    }

    public Universe getUniverse() {
        return universe;
    }
}