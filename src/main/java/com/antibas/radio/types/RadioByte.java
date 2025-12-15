package com.antibas.radio.types;

import com.antibas.util.list.IndexConvertor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

public class RadioByte implements Iterable<Boolean>, IndexConvertor, RadioType{
	private final Deque<Boolean> bits;
	@Getter @Setter
	private int size;
	@Getter @Setter
	private Endian endian;
	
	public RadioByte(int size, Collection<Boolean> bits, Endian endian) {
		this.size = size;
		this.bits = new LinkedList<>(bits);
		this.endian = endian;
	}
	
	public RadioByte(int size, Endian endian) {
		this.size = size;
		this.bits = new LinkedList<>();
		this.endian = endian;
	}
	
	public RadioByte(int size, int value, Endian endian) {
		this(size, endian);
		int tmp = value;
		for(int i = 0; i < size; i++) {
			this.appendFront(tmp != 0 && ((tmp /= 2) % 2 == 0));
		}
	}
	
	public RadioByte(Endian endian) {
		this(8, endian);
	}
	
	public RadioByte(int size, Collection<Boolean> bits) {
		this(size, bits, Endian.BIG_ENDIAN);
	}
	
	public RadioByte(int size) {
		this(size, Endian.BIG_ENDIAN);
	}
	
	public RadioByte(int size, int value) {
		this(size, value, Endian.BIG_ENDIAN);
	}
	
	
	public RadioByte(boolean... b) {
		this(b.length);
		for(boolean bb: b) {
			this.appendFront(bb);
		}
	}
	
	public RadioByte() {
		this(8);
	}
	
	public RadioByte(RadioByte b) {
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
	
	public void invert() {
		this.forEach(x -> !x);
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

	public static RadioByte and(RadioByte byte1, RadioByte byte2) {
		if(byte1.size != byte2.size) {
			throw new UnsupportedOperationException();
		}
		
		RadioByte ret = new RadioByte(byte1.size);
		for(int i = 0; i < ret.size; i++) {
			ret.appendFront(byte1.bitAt(i) & byte2.bitAt(i));
		}
		return ret;
	}
	
	public static RadioByte or(RadioByte byte1, RadioByte byte2) {
		if(byte1.size != byte2.size) {
			throw new UnsupportedOperationException();
		}
		
		RadioByte ret = new RadioByte(byte1.size);
		for(int i = 0; i < ret.size; i++) {
			ret.appendFront(byte1.bitAt(i) | byte2.bitAt(i));
		}
		return ret;
	}
	
	public static RadioByte xor(RadioByte byte1, RadioByte byte2) {
		if(byte1.size != byte2.size) {
			throw new UnsupportedOperationException();
		}
		
		RadioByte ret = new RadioByte(byte1.size);
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
