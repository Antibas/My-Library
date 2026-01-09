package com.antibas.math.combinations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Product<T> extends ArrayList<List<T>>  {
    public Product(int initialCapacity) {
        super(initialCapacity);
    }

    public Product() {
    }

    public Product(Collection<? extends List<T>> c) {
        super(c);
    }

    public Product(Collection<T> c, int n) {
        super(product(c, n).stream().toList());
    }

    public static <T> Product<T> product(Collection<T> col, int n) {
        Product<T> result = new Product<>();
        if (n < 0) {
            return result;
        }
        backtrack(col, n, new ArrayList<>(), result);
        return result;
    }

    private static <T> void backtrack(
            Collection<T> col,
            int n,
            List<T> current,
            Product<T> result) {

        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (T element : col) {
            current.add(element);
            backtrack(col, n, current, result);
            current.removeLast();
        }
    }

}
