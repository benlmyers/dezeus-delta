package com.dezeus.delta.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class HybridSet<T> {

    private Function<T, Boolean> base;
    private Set<T> add1 = new HashSet<>();
    private Set<T> rem2 = new HashSet<>();
    private boolean empty = true;

    public HybridSet(Function<T, Boolean> include) {
        this.base = include;
    }

    public HybridSet(Set<T> base) {
        add1.addAll(base);
    }

    public boolean containsAny(Collection<T> c) {
        for (T t : c) {
            if (contains(t)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(T e) {
        boolean contained = contains(e);
        add1.add(e);
        rem2.remove(e);
        empty = false;
        return contained;
    }

    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T t : c) {
            boolean r = add(t);
            if (r)
                changed = true;
        }
        rem2.removeAll(c);
        return changed;
    }

    public void clear() {
        add1.clear();
        rem2.clear();
        empty = true;
    }

    public boolean contains(T o) {
        return (base.apply(o) || add1.contains(o)) && !rem2.contains(o) && !empty;
    }

    public boolean containsAll(Collection<T> c) {
        for (T o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return empty && add1.isEmpty();
    }

    public Iterator<T> iterator(Set<T> universe) {
        return toList(universe).iterator();
    }

    public boolean remove(T o) {
        boolean contained = contains(o);
        add1.remove(o);
        rem2.add(o);
        return contained;
    }

    public boolean removeAll(Collection<T> c) {
        boolean changed = false;
        for (T o : c) {
            boolean r = remove(o);
            if (r)
                changed = true;
        }
        return changed;
    }

    public int size(Set<T> universe) {
        return toList(universe).size();
    }

    public List<T> toList(Set<T> universe) {
        List<T> result = new ArrayList<>(universe);
        result.addAll(add1);
        result.removeAll(rem2);
        if (empty)
            return new ArrayList<>();
        return result;
    }

}
