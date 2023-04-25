package com.dezeus.delta.core.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.dezeus.delta.core.element.Element;

public class Universe implements Collection<Element> {

    private Set<Element> elements;

    public Universe(Set<Element> objects) {
        this.elements = objects;
    }

    public Set<Element> getElements() {
        return elements;
    }

    @Override
    public boolean add(Element e) {
        return elements.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends Element> c) {
        return elements.addAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Iterator<Element> iterator() {
        return elements.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }
}