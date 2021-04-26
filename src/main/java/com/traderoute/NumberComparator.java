package com.traderoute;

import java.util.Comparator;

public class NumberComparator<T extends Number & Comparable> implements Comparator<T> {

    public int compare( T a, T b ) throws ClassCastException {
        return a.compareTo( b );
    }
}
