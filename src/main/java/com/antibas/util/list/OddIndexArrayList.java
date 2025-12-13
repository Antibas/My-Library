package com.antibas.util.list;

import java.util.Collection;

public class OddIndexArrayList<T> extends ArrayListIndexConvertor<T> {
    public OddIndexArrayList(){
        super();
    }

    public OddIndexArrayList(int initialCapacity){
        super(initialCapacity);
    }

    public OddIndexArrayList(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public int indexConvert(int index) {
        return 2*index + 1;
    }
}
