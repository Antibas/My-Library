package com.antibas.math.combinations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Permutation<T> extends ArrayList<List<T>> {
    public Permutation(int initialCapacity) {
        super(initialCapacity);
    }

    public Permutation() {
    }

    public Permutation(Collection<? extends List<T>> c) {
        super(c);
    }

    public Permutation(Collection<T> c, int n) {
        super(permutations(c, n).stream().toList());
    }

    public static <T> Permutation<T> permutations(Collection<T> col, int n) {
        Permutation<T> result = new Permutation<>();
        if (n < 0 || n > col.size()) {
            return result;
        }
        return backtrack(col, n, new ArrayList<>(), new boolean[col.size()], result);
    }

    private static <T> Permutation<T> backtrack(
            Collection<T> col,
            int n,
            List<T> current,
            boolean[] used,
            Permutation<T> result) {

        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return result;
        }

        for (int i = 0; i < col.size(); i++) {
            if (used[i]) continue;

            used[i] = true;
            current.add(col.stream().toList().get(i));

            backtrack(col, n, current, used, result);

            current.removeLast();
            used[i] = false;
        }
        return result;
    }

}
