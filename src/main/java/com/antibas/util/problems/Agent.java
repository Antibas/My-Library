package com.antibas.util.problems;

public interface Agent<S, A> {
	A getAction(S state);
	void registerInitialState(S state);
	int getIndex();
}
