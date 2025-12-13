package com.antibas.util.list;

import java.util.Collection;

public class EvenIndexArrayList<T> extends ArrayListIndexConvertor<T> {
    public EvenIndexArrayList(){
        super();
    }

    public EvenIndexArrayList(int initialCapacity){
        super(initialCapacity);
    }

    public EvenIndexArrayList(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public int indexConvert(int index) {
        return 2*index;
    }
}
