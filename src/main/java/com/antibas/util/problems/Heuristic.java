package com.antibas.util.problems;

import java.util.function.Function;

public interface Heuristic<S, A> extends Function<Successor<S, A>, Double> {

}
