package com.antibas.math.function;

import com.antibas.math.Complex;
import com.antibas.math.Math2;
import com.antibas.math.Number2;

public class Domain {
    public double getDx() {
        return dx;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    public boolean isClosedStart() {
        return closedStart;
    }

    public boolean isClosedEnd() {
        return closedEnd;
    }

    public Domain getNext() {
        return next;
    }
    private double dx;
	private double start;
	private double end;
    private boolean closedStart;
    private boolean closedEnd;

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setClosedStart(boolean closedStart) {
        this.closedStart = closedStart;
    }

    public void setClosedEnd(boolean closedEnd) {
        this.closedEnd = closedEnd;
    }
	private Domain next;
	
	public Domain(double start, double end, double dx, boolean closedStart, boolean closedEnd) {
		if(start >= end) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;
		this.dx = dx;
		this.closedStart = closedStart;
		this.closedEnd = closedEnd;
		this.next = null;
	}
	
	public Domain(double start, double end, boolean closedStart, boolean closedEnd) {
		this(start, end, Number2.DX_DOUBLE, closedStart, closedEnd);
	}
	
	public Domain(double start, double end, double dx) {
		this(start, end, dx, (start != Double.NEGATIVE_INFINITY), (end != Double.POSITIVE_INFINITY));
	}
	
	public Domain(double start, double end) {
		this(start, end, Number2.DX_DOUBLE, (start != Double.NEGATIVE_INFINITY), (end != Double.POSITIVE_INFINITY));
	}
	
	public Domain(double end, boolean closedEnd) {
		this(Double.NEGATIVE_INFINITY, end, false, closedEnd);
	}
	
	public Domain(double end) {
		this(Double.NEGATIVE_INFINITY, end, false, (end != Double.POSITIVE_INFINITY));
	}
	
	public Domain(Domain domain) {
		this(domain.start, domain.end, domain.dx, domain.closedStart, domain.closedEnd);
		this.next = domain.next;
	}
	
	public Domain(Domain... domains) {
		this(domains[0].start, domains[0].end, domains[0].dx, domains[0].closedStart, domains[0].closedEnd);
		Domain p = this;
		for(int i = 1; i < domains.length; i++) {
			if(!Domain.checkDomains(domains[i-1], domains[i])) { //if(domains[i-1].end >= domains[i].start && (domains[i-1].closedEnd || domains[i].closedStart)) {
				throw new IllegalArgumentException();
			}
			p.next = domains[i];
			p = p.next;
		}
	}
	
	public Domain() {
		this(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Number2.DX_DOUBLE, false, false);
	}

    public void setStart(double start) {
		if(start >= end) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		if(!check()) {
			throw new IllegalArgumentException();
		}
	}

    public void setEnd(double end) {
		if(start >= end || !Domain.checkDomains(this, next)){//(end >= next.start && (closedEnd || next.closedStart))) {
			throw new IllegalArgumentException();
		}
		this.end = end;
	}

    public boolean isInDomain(double x) {
		if(x < end && x > start) return true;
		if(x == end) return closedEnd;
		if(x == start) return closedStart;
		if(next != null) return next.isInDomain(x);
		return false;
	}
	
	public boolean isInDomain(int x) {
		return this.isInDomain((double)x);
	}
	
	public boolean isInDomain(Complex x) {
		return this.isInDomain(x.getReal());
	}
	
	public void pushDomain(Domain d) {
		Domain p = this, pprev = null;
		while(p != null) {
			pprev = p;
			p = p.next;
		}
		
		assert(pprev != null);
		
		if(!Domain.checkDomains(pprev, d)) {
			throw new IllegalArgumentException();
		}
		
		pprev.next = d;
	}
	
	public Domain popDomain() {
		Domain p = this, pprev = null, pprevprev = null;
		while(p != null) {
			pprevprev = pprev;
			pprev = p;
			p = p.next;
		}
		
		assert(pprev != null);
		
		Domain tmp = null;
		if(pprevprev != null) {
			tmp = pprevprev.next;
			pprevprev.next = null;
		}
		return tmp;
	}
	
	public double[] getRange() {
		double a = start, b = end;
		if(!this.closedStart) {
			a += dx;
		}
		
		if(!this.closedEnd) {
			b -= dx;
		}
		return Math2.range(a, b, dx);
	}

	@Override
	public String toString() {
		String str = (closedStart?"[":"(") + start + 
				", " + 
				end + (closedEnd?"]":")");
		if(next == null) {
			return str;
		}
		return str + " U " + next.toString();
		/*return (closedStart?"[":"(") + start + 
				"," + 
				end + (closedEnd?"]":")") + ", dx: " + dx;*/
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (closedEnd ? 1231 : 1237);
		result = prime * result + (closedStart ? 1231 : 1237);
        result = prime * result + Double.hashCode(dx);
        result = prime * result + Double.hashCode(end);
		result = prime * result + ((next == null) ? 0 : next.hashCode());
        result = prime * result + Double.hashCode(start);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Domain) {
			Domain o = (Domain)(obj);
			return this.start == o.start && this.closedStart == o.closedStart && 
				   this.end == o.end && this.closedEnd == o.closedEnd &&
				   this.dx == o.dx && this.next.equals(o.next);
		}
		return false;
	}
	
	private boolean check() {
		Domain p = this, pprev = null;
		while(p != null) {
			
			
			
			//System.out.println(pprev);
			//System.out.println(p);
			//System.out.println();
			if(!Domain.checkDomains(pprev, p)) { //if(domains[i-1].end >= domains[i].start && (domains[i-1].closedEnd || domains[i].closedStart)) {
				return false;
			}
			pprev = p;
			p = p.next;
		}
		return true;
	}

	private static boolean checkDomains(Domain d1, Domain d2) {
		if(d1 == null) {
			return true;
		}
		
		if(d2 == null) {
			return true;
		}
		return !(d1.end >= d2.start && (d1.closedEnd || d2.closedStart));
	}
	
}
