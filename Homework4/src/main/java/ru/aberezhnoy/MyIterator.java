package ru.aberezhnoy;

import java.util.Iterator;

public interface MyIterator<T> extends Iterator<T> {
    void reset();

    boolean atEnd();

    boolean atBegin();

    T deleteCurrent();

    void insertAfter(T obj);

    void insertBefore(T obj);

    T getCurrent();
}
