package com.dezeus.delta.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A utility class for generating all possible tuples of a given size from a set
 * of elements.
 */
public final class ListUtil {

    /**
     * Generates all possible tuples of a given size from a set of elements.
     * 
     * @param size     the size of each tuple to generate
     * @param elements the set of elements to generate tuples from
     * @return a set containing all possible tuples of the given size from the given
     *         set of elements
     */
    public static <T> Set<List<T>> allTuples(int size, Set<T> elements) {
        Set<List<T>> tuples = new HashSet<>();
        if (size == 0) {
            tuples.add(new ArrayList<>());
        } else {
            for (T element : elements) {
                Set<List<T>> tuplesWithoutElement = allTuples(size - 1, elements);
                for (List<T> tuple : tuplesWithoutElement) {
                    List<T> newTuple = new ArrayList<>(tuple);
                    newTuple.add(element);
                    tuples.add(newTuple);
                }
            }
        }
        return tuples;
    }
}
