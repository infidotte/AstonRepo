package org.aston;

import java.util.*;
import java.util.function.Consumer;

//*
// Написать свой кастомный ArrayList, обязательно реализовать следующие методы:
// - add(int index, E element) --
// - addAll(Collection<? extends E> c) ---
// - clear() ---
// - get(int index) ----
// - isEmpty() --------
// - remove(int index) ---
// - remove(Object o) ---
// - sort(Comparator<? super E> c) ---
//Первая половина реализует метод sort при помощи алгоритма quicksort вторая merge sort.
//
// *//
public class ArrayList<E> implements CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException();
        }
        size = elements.length;
    }

    public ArrayList(Collection<? extends E> collection) {
        Object[] a = collection.toArray();
        size = a.length;
        if (size != 0) {
            if (collection instanceof ArrayList<?>) {
                elements = a;
            } else {
                elements = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elements = new Object[DEFAULT_CAPACITY];
        }
    }

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean add(E element) {
        int index = size;
        return add(index, element);
    }

    public boolean add(int index, E element) {
        if (index > elements.length || index < 0)
            throw new IndexOutOfBoundsException();
        if (size == elements.length) {
            grow(size * 2 + 1);
        }
        System.arraycopy(elements, index, elements, index + 1, size + 1);
        elements[index] = element;
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        Object[] a = collection.toArray();
        int collSize = a.length;
        if (collSize == 0) return false;
        if (collSize > elements.length - size) {
            grow(size = collSize);
        }
        System.arraycopy(a, 0, elements, size, collSize);
        size += collSize;
        return true;
    }

    public E get(int index) {
        if (index < size || index > 0) return (E) elements[index];
        else throw new IndexOutOfBoundsException();
    }

    public void clear() {
        for (int to = size, i = size = 0; i < to; i++)
            elements[i] = null;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public E remove(int index) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        E old = (E) elements[index];
        rem(index);
        return old;
    }

    public boolean remove(Object o) {
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++) {
                    if (elements[i] == null) break found;
                }
            } else {
                for (; i < size; i++) {
                    if (o.equals(elements[i])) break found;
                }
            }
            return false;
        }
        rem(i);
        return true;
    }

    private void rem(int index) {
        int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        elements[size = newSize] = null;
    }

    private void grow(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public boolean contains(E element) {
        if (size == 0) return false;
        for (Object el :
                elements) {
            if (el != null && el.equals(element)) return true;
        }
        return false;
    }

    public void sort(Comparator<? super E> c) {
        quickSort(c, 0, size - 1);
    }

    public void quickSort(Comparator<? super E> c, int low, int high) {
        if (elements.length == 0 || low >= high) return;
        int middle = low + (high - low) / 2;
        E border = (E) elements[middle];
        int i = low, j = high;
        while (i <= j) {
            while (c.compare((E) elements[i], border) < 0) i++;
            while (c.compare((E) elements[j], border) > 0) j--;
            if (i <= j) {
                E swap = (E) elements[i];
                elements[i] = elements[j];
                elements[j] = swap;
                i++;
                j--;
            }
        }
        if (low < j) quickSort(c, low, j);
        if (high > i) quickSort(c, i, high);
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        CustomArrayList.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return CustomArrayList.super.spliterator();
    }
}
