package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MySetTest {
    private MySet<String> mySet;

    @BeforeEach
    void setUp() {
        mySet = new MySet<>();
    }

    @Test
    void testAdd() {
        assertTrue(mySet.add("A"));
        assertTrue(mySet.add("B"));
        assertFalse(mySet.add("A")); // "A" уже существует
        assertEquals(2, mySet.size());
    }

    @Test
    void testRemove() {
        mySet.add("A");
        mySet.add("B");
        assertTrue(mySet.remove("A"));
        assertFalse(mySet.remove("C")); // "C" не существует
        assertEquals(1, mySet.size());
    }

    @Test
    void testContains() {
        mySet.add("A");
        mySet.add("B");
        assertTrue(mySet.contains("A"));
        assertFalse(mySet.contains("C")); // "C" не существует
    }

    @Test
    void testIsEmpty() {
        assertTrue(mySet.isEmpty());
        mySet.add("A");
        assertFalse(mySet.isEmpty());
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
        Object[] expectedArray = {"A", "B"};
        assertArrayEquals(expectedArray, mySet.toArray());
    }

    @Test
    void testToArray2() {
        HashSet<String> HSet = new HashSet<>();

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
        assertEquals(3, mySet.size());
        assertTrue(mySet.contains("C"));
        assertTrue(mySet.contains("D"));
        assertTrue(mySet.contains("E"));
    }

    @Test
    void testRetainAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");

        HashSet<String> retainElements = new HashSet<>(Arrays.asList("A", "C"));
        assertTrue(mySet.retainAll(retainElements));
        assertEquals(2, mySet.size());
        assertTrue(mySet.contains("A"));
        assertTrue(mySet.contains("C"));
        assertFalse(mySet.contains("B"));
    }

    @Test
    void testIterator() {
        mySet.add("A");
        mySet.add("B");

        Iterator<String> iterator = mySet.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());

        iterator.remove(); // Удаляем "A"

        assertEquals(1, mySet.size());
        assertFalse(mySet.contains("A"));

        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());

        iterator.remove(); // Удаляем "B"

        assertTrue(mySet.isEmpty());
    }

    @Test
    void testRemoveAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");

        HashSet<String> elementsToRemove = new HashSet<>(Arrays.asList("A", "B"));
        assertTrue(mySet.removeAll(elementsToRemove));
        assertEquals(1, mySet.size());
        assertFalse(mySet.contains("A"));
        assertFalse(mySet.contains("B"));
        assertTrue(mySet.contains("C"));
    }

    @Test
    void testRemoveAll2() {
        mySet.add("A");
        mySet.add("B");

        HashSet<String> elementsToRemove = new HashSet<>(Arrays.asList("C", "D"));
        assertFalse(mySet.removeAll(elementsToRemove));
        assertEquals(2, mySet.size());
        assertTrue(mySet.contains("A"));
        assertTrue(mySet.contains("B"));
    }

    @Test
    void testContainsAll() {
        mySet.add("A");
        mySet.add("B");
        mySet.add("C");

        HashSet<String> elementsToCheck = new HashSet<>(Arrays.asList("A", "C"));
        assertTrue(mySet.containsAll(elementsToCheck));
    }

    @Test
    void testContainsAll2() {
        mySet.add("A");
        mySet.add("B");

        HashSet<String> elementsToCheck = new HashSet<>(Arrays.asList("A", "C"));
        assertFalse(mySet.containsAll(elementsToCheck));
    }
}