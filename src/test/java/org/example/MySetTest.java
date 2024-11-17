package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MySetTest {
    private MySet<String> mySet;
    private HashSet<String> HSet;

    @BeforeEach
    void setUp() {

        mySet = new MySet<>();
        HSet = new HashSet<>();
    }

    @Test
    void testAdd() {
        assertEquals(mySet.add("A"), HSet.add("A"));
        assertEquals(mySet.add("B"), HSet.add("B"));
        assertEquals(mySet.add("A"), HSet.add("A")); // "A" уже существует
        assertEquals(HSet.size(), mySet.size());
    }

    @Test
    void testRemove() {
        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");

        assertEquals(mySet.remove("A"), HSet.remove("A"));
        assertEquals(mySet.remove("C"), HSet.remove("C")); // "C" не существует
        assertEquals(HSet.size(), mySet.size());
    }

    @Test
    void testContains() {
        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");

        assertEquals(mySet.contains("A"), HSet.contains("A"));
        assertEquals(mySet.contains("C"), HSet.contains("C")); // "C" не существует
    }

    @Test
    void testIsEmpty() {
        assertEquals(mySet.isEmpty(), HSet.isEmpty());
        mySet.add("A");
        HSet.add("A");
        assertEquals(mySet.isEmpty(), HSet.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, mySet.size());
        mySet.add("A");
        mySet.add("B");
        assertEquals(2, mySet.size());
        mySet.remove("A");
        assertEquals(1, mySet.size());
    }

    @Test
    void testToArray() {
        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");

        Object[] expectedArray = {"A", "B"};
        assertArrayEquals(expectedArray, mySet.toArray());
        assertArrayEquals(HSet.toArray(), mySet.toArray());
    }

    @Test
    void testToArray2() {

        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");


        assertArrayEquals(HSet.toArray(new String[0]), mySet.toArray(new String[0]));
        assertArrayEquals(HSet.toArray(new String[20]), mySet.toArray(new String[20]));
    }

    @Test
    void testAddAll() {
        HashSet<String> additionalElements = new HashSet<>(Arrays.asList("C", "D", "E"));
        mySet.addAll(additionalElements);
        HSet.addAll(additionalElements);

        assertEquals(HSet.size(), mySet.size());
        assertEquals(mySet.contains("C"), HSet.contains("C"));
        assertEquals(mySet.contains("D"), HSet.contains("C"));
        assertEquals(mySet.contains("E"), HSet.contains("C"));
    }

    @Test
    void testRetainAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");
        HSet.add("A");
        HSet.add("B");
        HSet.add("C");

        HashSet<String> retainElements = new HashSet<>(Arrays.asList("A", "C"));

        assertEquals(mySet.retainAll(retainElements), HSet.retainAll(retainElements));
        assertEquals(HSet.size(), mySet.size());
        assertEquals(mySet.contains("A"), HSet.contains("A"));
        assertEquals(mySet.contains("C"), HSet.contains("C"));
        assertEquals(mySet.contains("B"), HSet.contains("B"));
    }

    @Test
    void testIterator() {
        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");

        Iterator<String> iterator = mySet.iterator();
        Iterator<String> hiterator = HSet.iterator();

        assertEquals(iterator.hasNext(), hiterator.hasNext());
        assertEquals(hiterator.next(), iterator.next());

        iterator.remove(); // Удаляем "A"
        hiterator.remove(); // Удаляем "A"

        assertEquals(HSet.size(), mySet.size());
        assertEquals(mySet.contains("A"), HSet.contains("A"));

        assertEquals(iterator.hasNext(), hiterator.hasNext());
        assertEquals(hiterator.next(), iterator.next());

        iterator.remove(); // Удаляем "B"
        hiterator.remove(); // Удаляем "B"

        assertEquals(HSet.isEmpty(), mySet.isEmpty());
    }

    @Test
    void testRemoveAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");
        HSet.add("A");
        HSet.add("B");
        HSet.add("C");

        HashSet<String> elementsToRemove = new HashSet<>(Arrays.asList("A", "B"));

        assertEquals(mySet.removeAll(elementsToRemove), HSet.removeAll(elementsToRemove));
        assertEquals(mySet.size(), HSet.size());
        assertEquals(mySet.contains("A"), HSet.contains("A"));
        assertEquals(mySet.contains("B"), HSet.contains("B"));
        assertEquals(mySet.contains("C"), HSet.contains("C"));
    }

    @Test
    void testRemoveAll2() {
        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");

        HashSet<String> elementsToRemove = new HashSet<>(Arrays.asList("C", "D"));

        assertEquals(mySet.removeAll(elementsToRemove), HSet.removeAll(elementsToRemove));
        assertEquals(mySet.size(), HSet.size());
        assertEquals(mySet.contains("A"), HSet.contains("A"));
        assertEquals(mySet.contains("B"), HSet.contains("B"));
    }

    @Test
    void testContainsAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");
        HSet.add("A");
        HSet.add("B");
        HSet.add("C");

        HashSet<String> elementsToCheck = new HashSet<>(Arrays.asList("A", "C"));
        assertEquals(mySet.containsAll(elementsToCheck), HSet.containsAll(elementsToCheck));
    }

    @Test
    void testContainsAll2() {
        mySet.add("A");
        mySet.add("B");
        HSet.add("A");
        HSet.add("B");

        HashSet<String> elementsToCheck = new HashSet<>(Arrays.asList("A", "C"));
        assertEquals(mySet.containsAll(elementsToCheck), HSet.containsAll(elementsToCheck));
    }
}