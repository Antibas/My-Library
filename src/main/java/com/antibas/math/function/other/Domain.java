package com.antibas.math.function.other;

import com.antibas.math.Number2;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.*;

@Getter
public class Domain<T extends Number2> implements List<Domain<T>> {
	private T dx;
	private T start;
	private T end;
	@Setter
	private boolean closedStart;
	@Setter
	private boolean closedEnd;
	private Domain<T> next;
	
	public Domain(T start, T end, T dx, boolean closedStart, boolean closedEnd) {
		if(start.greaterThanOrEqual(end)) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;
		this.dx = dx;
		this.closedStart = closedStart;
		this.closedEnd = closedEnd;
		this.next = null;
		this.validate();
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
	
	@SafeVarargs
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

//    public boolean isInDomain(T x) {
//		if(x.lessThan(end) && x.greaterThan(start)) return true;
//		if(closedEnd && x.equals(end)) return true;
//		if(closedStart && x.equals(start)) return true;
//		if(next != null) return next.isInDomain(x);
//		return false;
//	}
	
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

	public void setStart(T start) {
		this.start = start;
		this.validate();
	}

	public void setEnd(T end) {
		this.end = end;
		this.validate();
	}

	public void setDx(T dx) {
		this.dx = dx;
		this.validate();
	}

	private void validate(){
		assert start.greaterThan(end);
		assert dx.isPositive();
	}

	public List<Domain<T>> toList(){
		List<Domain<T>> list = new ArrayList<>();
		Iterator<Domain<T>> it = new DomainIterator();
		while (it.hasNext()){
			list.add(it.next());
		}
		return list;
	}

	@Override
	public int size() {
//		Domain<T> current = this;
//		int s = 0;
//		while (current != null){
//			s++;
//			current = current.next;
//		}
//		return s;
		return this.toList().size();
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	@Override
	public boolean contains(Object o) {
		if (o instanceof Number2 x) {
			if (x.lessThan(end) && x.greaterThan(start)) return true;
			if (closedEnd && x.equals(end)) return true;
			if (closedStart && x.equals(start)) return true;
			if (next != null) return next.contains(x);
		}
		return false;
	}

	@Override
	public Iterator<Domain<T>> iterator() {
		return new DomainIterator();
	}

	@Override
	public Object[] toArray() {
		return this.toList().toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return this.toList().toArray(a);
	}

	@Override
	public boolean add(Domain<T> t) {
		Domain<T> current = this;
		while (current.next != null){
			current = current.next;
		}
		current.next = t;
		this.validate();
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Domain<T> current = this;
		while (current.next != null){
			if(current.start.equals(o)){
				current.start = null;
				return true;
			}
			if(current.end.equals(o)){
				current.end = null;
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return new HashSet<>(this.toList()).containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Domain<T>> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Domain<T>> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		this.next = null;
	}

	@Override
	public Domain<T> get(int index) {
		return null;
	}

	@Override
	public Domain<T> set(int index, Domain<T> element) {
		return null;
	}

	@Override
	public void add(int index, Domain<T> element) {

	}

	@Override
	public Domain<T> remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<Domain<T>> listIterator() {
		return null;
	}

	@Override
	public ListIterator<Domain<T>> listIterator(int index) {
		return null;
	}

	@Override
	public List<Domain<T>> subList(int fromIndex, int toIndex) {
		return List.of();
	}

	private class DomainIterator implements Iterator<Domain<T>> {
		private Domain<T> current;
		public DomainIterator(){
			this.current = Domain.this;
		}
		@Override
		public boolean hasNext() {
			return current.next != null && current.next.end != null;
		}

		@Override
		public Domain<T> next() {
			current = current.next;
			return current;
		}
	}
}
