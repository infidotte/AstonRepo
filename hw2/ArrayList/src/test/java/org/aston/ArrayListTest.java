package org.aston;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private CustomArrayList<Integer> list;

    @BeforeEach
    public void init(){
        list = new ArrayList<>();
    }

    @Test
    void contains(){
        assertFalse(list.contains(80085));
        list.add(80085);
        assertTrue(list.contains(80085));
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void add() {
        list.add(80085);
        assertTrue(list.contains(80085));
        list.add(0, 0);
        assertTrue(list.contains(0));
        list.add(5, 5);
        assertTrue(list.contains(5));
    }

    @Test
    void addAll() {
        List<Integer> test = new java.util.ArrayList<>();
        for (int i = 0; i < 5; i++) {
            test.add(i);
        }
        list = new ArrayList<>(test);

        for (Integer t:
             test) {
            assertTrue(list.contains(t));
        }
    }

    @Test
    void get() {
        list.add(5);
        assertEquals(5, list.get(0));
    }

    @Test
    void clear() {
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals(5, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void removeByIndex() {
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals(0, list.get(0));
        Integer i = list.remove(0);
        assertEquals(1, list.get(0));
    }

    @Test
    void removeByObject() {
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        assertEquals(0, list.get(0));
        Integer nool = 0;
        boolean i = list.remove(nool);
        assertEquals(1, list.get(0));
    }

    @Test
    void sort() {
        ArrayList<Integer> test = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            test.add(i);
        }
        list.add(3);list.add(2);list.add(4);list.add(1);list.add(0);
        list.sort(Integer::compare);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), test.get(i));
        }
    }
}