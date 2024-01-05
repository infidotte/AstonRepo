package org.aston;

import java.util.Collection;
import java.util.Comparator;

public interface CustomArrayList<E> extends Iterable<E> {
    boolean add(E element);
    boolean add(int index, E element);

    boolean addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    int size();

    E remove(int index);

    boolean remove(E element);

    void sort(Comparator<? super E> c);

    boolean contains(E element);
}
