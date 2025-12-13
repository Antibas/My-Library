package com.antibas.math.function.other;

import com.antibas.math.Number2;

public class Domain<T extends Number2> {
	private T dx;
	private T start;
	private T end;
	private boolean closedStart;
	private boolean closedEnd;
	private Domain<T> next;
	
	public Domain(T start, T end, T dx, boolean closedStart, boolean closedEnd) {
		if(start.greaterThanOrEqual(end)) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;
		this.closedStart = closedStart;
		this.closedEnd = closedEnd;
		this.next = null;
	}
	
	public Domain(T start, T end, T dx, boolean closedStart) {
		this(start, end, dx, closedStart, end.isFinite());
	}
	
	public Domain(T start, T end, T dx) {
		this(start, end, dx, start.isFinite(), end.isFinite());
	}
	
	public Domain(Domain<T> domain) {
		this(domain.start, domain.end, domain.dx, domain.closedStart, domain.closedEnd);
		this.next = domain.next;
	}
	
	public Domain(Domain<T>... domains) {
		this(domains[0].start, domains[0].end, domains[0].dx, domains[0].closedStart, domains[0].closedEnd);
		Domain<T> p = this;
		for(int i = 1; i < domains.length; i++) {
			if(domains[i-1].end.greaterThanOrEqual(domains[i].start)) {
				throw new IllegalArgumentException();
			}
			p.next = domains[i];
			p = p.next;
		}
	}
	
	/*public Domain2() {
		this(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Function.DX, false, false);
	}*/
	
	public T getStart() {
		return start;
	}

	public void setStart(T start) {
		if(start.greaterThanOrEqual(end)) {
			throw new IllegalArgumentException();
		}
		this.start = start;
	}

	public T getEnd() {
		return end;
	}

	public void setEnd(T end) {
		if(start.greaterThanOrEqual(end) || end.greaterThanOrEqual(next.start)) {
			throw new IllegalArgumentException();
		}
		this.end = end;
	}

	public boolean isClosedStart() {
		return closedStart;
	}

	public void setClosedStart(boolean closedStart) {
		this.closedStart = closedStart;
	}

	public boolean isClosedEnd() {
		return closedEnd;
	}

	public void setClosedEnd(boolean closedEnd) {
		this.closedEnd = closedEnd;
	}
	
	public T getDx() {
		return dx;
	}

	public void setDx(T dx) {
		this.dx = dx;
	}

	public Domain<T> getNext() {
		return next;
	}

	public boolean isInDomain(T x) {
		if(x.lessThan(end) && x.greaterThan(start)) return true;
		if(closedEnd && x.equals(end)) return true;
		if(closedStart && x.equals(start)) return true;
		if(next != null) return next.isInDomain(x);
		return false;
	}
	
	@Override
	public String toString() {
		String str = (closedStart?"[":"(") + start.toString() + 
				", " + 
				end.toString() + (closedEnd?"]":")")  + ", dx: " + dx.toString();
		if(next == null) {
			return str;
		}
		return str + " -> " + next.toString();
		/*return (closedStart?"[":"(") + start + 
				"," + 
				end + (closedEnd?"]":")") + ", dx: " + dx;*/
	}
	
	
}
