package com.antibas.languages;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

public class Dictionary extends ArrayList<Word> {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2562499360971149457L;

	@Override
	public boolean addAll(Collection<? extends Word> c) {
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Word> c) {
		return super.addAll(index, c);
	}

	@Override
	public void addLast(Word element) {
		super.addLast(element);
	}

	@Override
	public boolean add(Word word) {
		return super.add(word);
	}

	@Override
	public void add(int index, Word element) {
		super.add(index, element);
	}

	@Override
	public void addFirst(Word element) {
		super.addFirst(element);
	}
}
