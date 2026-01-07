package com.antibas.math;

import com.antibas.radio.types.Endian;
import com.antibas.radio.types.RadioType;
import com.antibas.util.list.IndexConvertor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;

public class Byte extends Number2 implements Iterable<Boolean>, IndexConvertor, RadioType {
	private final Deque<Boolean> bits;
	@Getter @Setter
	private int size;
	@Getter @Setter
	private Endian endian;

	public Byte(int size, Collection<Boolean> bits, Endian endian) {
		if(Math2.decimalPart(Math.sqrt(size)) != 0)
			throw new IllegalArgumentException();
		this.size = size;
		this.bits = bits == null? new LinkedList<>():new LinkedList<>(bits);
		this.endian = endian;
	}

	public Byte(int size, Endian endian) {
		this(size, null, endian);
	}

	public Byte(int size, int value, Endian endian) {
		this(size, endian);
		int tmp = value;
		for(int i = 0; i < size; i++) {
			this.appendFront(tmp != 0 && ((tmp /= 2) % 2 == 0));
		}
	}

	public Byte(Endian endian) {
		this(8, endian);
	}

	public Byte(int size, Collection<Boolean> bits) {
		this(size, bits, Endian.BIG_ENDIAN);
	}

	public Byte(int size) {
		this(size, Endian.BIG_ENDIAN);
	}

	public Byte(int size, int value) {
		this(size, value, Endian.BIG_ENDIAN);
	}


	public Byte(boolean... b) {
		this(b.length);
		for(boolean bb: b) {
			this.appendFront(bb);
		}
	}

	public Byte() {
		this(8);
	}

	@Override
	public int intValue() {
		return (int)this.doubleValue();
	}

	@Override
	public long longValue() {
		return (long)this.doubleValue();
	}

	@Override
	public float floatValue() {
		return (float)this.doubleValue();
	}

	@Override
	public double doubleValue() {
		List<Integer> numBits = this.bits.stream().map(x -> x?1:0).toList();
		if(this.endian == Endian.LITTLE_ENDIAN) numBits = numBits.reversed();
		int v = 0, i = size-1;
		for(int bit: numBits){
			v += bit*(i--);
		}
		return v;
	}

	public Byte(Byte b) {
		this(b.size, b.bits, b.endian);
	}
	
	public void setBit(int index, boolean bit) {
		((LinkedList<Boolean>)this.bits).set(indexConvert(index), bit);
	}
	
	public void setBit(int index, int bit) {
		this.setBit(index, bit == 1);
	}
	
	public void appendFront(boolean bit) {
		if(this.bits.size() == size) {
			this.bits.pollLast();
		}
		this.bits.offerFirst(bit);
		
	}
	
	public void appendFront(int bit) {
		this.appendFront(bit!=0);
	}
	
	public void appendBack(boolean bit) {
		if(this.bits.size() == size) {
			this.bits.pollFirst();
		}
		this.bits.offerLast(bit);
	}
	
	public void appendBack(int bit) {
		this.appendBack(bit!=0);
	}
	
	public void shiftFront(int times) {
		for(int i = 0; i < times; i++) {
			this.bits.pollLast();
			this.appendFront(0);
		}
	}
	
	public void shiftFront() {
		this.shiftFront(1);
	}
	
	public void shiftBack(int times) {
		for(int i = 0; i < times; i++) {
			this.bits.pollFirst();
			this.appendBack(0);
		}
	}
	
	public void shiftBack() {
		this.shiftBack(1);
	}
	
	public boolean bitAt(int index) {
		return ((LinkedList<Boolean>)this.bits).get(indexConvert(index));
	}
	
	public void forEach(Function<Boolean, Boolean> action) {
		for(int i = 0; i < size; i++) {
			this.setBit(i, action.apply(this.bitAt(i)));
		}
	}

	@Override
	public Complex complexValue() {
		return new Complex(this.doubleValue());
	}

	@Override
	public <T extends Number2> Number add(T o) {
		return null;
	}

	@Override
	public <T extends Number2> Number subtract(T o) {
		return null;
	}

	@Override
	public <T extends Number2> Number multiply(T o) {
		return null;
	}

	@Override
	public <T extends Number2> Number divide(T o) throws ArithmeticException {
		return null;
	}

	@Override
	public Number invert() {
//		this.forEach(x -> !x);
		return new Byte(size, this.bits.stream().map(x -> !x).toList());
	}

	@Override
	public Number abs() {
		return null;
	}

	@Override
	public Number pow(int power) {
		return null;
	}

	@Override
	public <T extends Number2> boolean greaterThan(T o) {
		return false;
	}

	@Override
	public <T extends Number2> boolean greaterThanOrEqual(T o) {
		return false;
	}

	@Override
	public <T extends Number2> boolean lessThan(T o) {
		return false;
	}

	@Override
	public <T extends Number2> boolean lessThanOrEqual(T o) {
		return false;
	}

	@Override
	public <T extends Number2> boolean notEquals(T o) {
		return false;
	}

	@Override
	public boolean isInfinite() {
		return false;
	}

	@Override
	public boolean isPositive() {
		return false;
	}

	@Override
	public int toInt() {
		int s = 0;
		for(int i = 0; i < size; i++) {
			if(this.bitAt(i)) {
				s += (int) Math.pow(2, i);
			}
		}
		return s;
	}
	
	@Override
	public byte toByte() {
		byte s = 0;
		for(int i = 0; i < size; i++) {
			if(this.bitAt(i)) {
				s += (byte) Math.pow(2, i);
			}
		}
		return s;
	}
	
	@Override
	public short toShort() {
		short s = 0;
		for(int i = 0; i < size; i++) {
			if(this.bitAt(i)) {
				s += (short) Math.pow(2, i);
			}
		}
		return s;
	}
	
	@Override
	public char toChar() {
		char s = 0;
		for(int i = 0; i < size; i++) {
			if(this.bitAt(i)) {
				s += (char) Math.pow(2, i);
			}
		}
		return s;
	}

	public static Byte and(Byte byte1, Byte byte2) {
		if(byte1.size != byte2.size) {
			throw new UnsupportedOperationException();
		}
		
		Byte ret = new Byte(byte1.size);
		for(int i = 0; i < ret.size; i++) {
			ret.appendFront(byte1.bitAt(i) & byte2.bitAt(i));
		}
		return ret;
	}
	
	public static Byte or(Byte byte1, Byte byte2) {
		if(byte1.size != byte2.size) {
			throw new UnsupportedOperationException();
		}
		
		Byte ret = new Byte(byte1.size);
		for(int i = 0; i < ret.size; i++) {
			ret.appendFront(byte1.bitAt(i) | byte2.bitAt(i));
		}
		return ret;
	}
	
	public static Byte xor(Byte byte1, Byte byte2) {
		if(byte1.size != byte2.size) {
			throw new UnsupportedOperationException();
		}
		
		Byte ret = new Byte(byte1.size);
		for(int i = 0; i < ret.size; i++) {
			ret.appendFront(byte1.bitAt(i) ^ byte2.bitAt(i));
		}
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for(boolean bit: bits) {
			if(bit) ret.append(1);
			else ret.append(0);
		}
		return ret.toString();
	}

	@Override
	public Iterator<Boolean> iterator() {
		return this.bits.iterator();
	}

	@Override
	public int indexConvert(int index) {
		return this.size-index-1;
	}
}
