package com.antibas.util.pair;

import java.io.Serial;
import java.io.Serializable;

public class Pair<V1, V2> implements Serializable{

    public V1 getFirst() {
        return first;
    }

    public void setFirst(V1 first) {
        this.first = first;
    }

    public V2 getSecond() {
        return second;
    }

    public void setSecond(V2 second) {
        this.second = second;
    }
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -6282508785273402085L;
	protected V1 first;
	protected V2 second;
	
	public Pair(V1 first, V2 second) {
		this.first = first;
		this.second = second;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair) {
			return first.equals(((Pair<V1, V2>) obj).first) &&
				   second.equals(((Pair<V1, V2>) obj).second);
		}
		return false;
	}

	@Override
	public String toString() {
		return "<" + first + ", " + second + ">";
	}

}
