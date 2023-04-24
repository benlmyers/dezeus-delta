package com.dezeus.delta.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Universe implements Collection<UniverseObject> {

    private Set<UniverseObject> objects;

    public Universe(Set<UniverseObject> objects) {
        this.objects = objects;
    }

    public Set<UniverseObject> getObjects() {
        return objects;
    }

    @Override
    public boolean add(UniverseObject e) {
        return objects.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends UniverseObject> c) {
        return objects.addAll(c);
    }

    @Override
    public void clear() {
        objects.clear();
    }

    @Override
    public boolean contains(Object o) {
        return objects.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return objects.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
        return objects.isEmpty();
    }

    @Override
    public Iterator<UniverseObject> iterator() {
        return objects.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return objects.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return objects.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return objects.retainAll(c);
    }

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public Object[] toArray() {
        return objects.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return objects.toArray(a);
    }
}