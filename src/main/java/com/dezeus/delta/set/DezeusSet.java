package com.dezeus.delta.set;

import java.util.Collection;
import java.util.function.Function;

public class DezeusSet<T> {

    private Function<T, Boolean> isIncluded;

    public DezeusSet(Function<T, Boolean> isIncluded) {
        this.isIncluded = isIncluded;
    }

    public DezeusSet(Collection<T> base) {
        this.isIncluded = base::contains;
    }

    public boolean contains(T t) {
        return isIncluded.apply(t);
    }

    public DezeusSet<T> union(DezeusSet<T> other) {
        return new DezeusSet<>(t -> isIncluded.apply(t) || other.contains(t));
    }

    public DezeusSet<T> intersection(DezeusSet<T> other) {
        return new DezeusSet<>(t -> isIncluded.apply(t) && other.contains(t));
    }

    public DezeusSet<T> difference(DezeusSet<T> other) {
        return new DezeusSet<>(t -> isIncluded.apply(t) && !other.contains(t));
    }
}
