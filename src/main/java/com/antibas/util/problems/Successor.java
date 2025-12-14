package com.antibas.util.problems;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Successor<S, A> {
	private S state;
	private A action;
	private double cost;
	
	
	public Successor(S state, A action, double cost) {
		this.state = state;
		this.action = action;
		this.cost = cost;
	}
	
	public Successor(S state, A action, Heuristic<S,A> heuristic) {
		this.state = state;
		this.action = action;
		this.cost = heuristic.apply(this);
	}
	
	public Successor(S state, A action) {
		this(state, action, 0d);
	}

    @Override
	public String toString() {
		return "Successor [state=" + state + ", action=" + action + ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Successor other = (Successor) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
}
