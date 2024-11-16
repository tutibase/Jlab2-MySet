package org.example;

import java.util.*;

public class MySet<E> implements Set<E>, Iterable<E> {
    private Object[] elements;
    private int size;

    public MySet() {
        elements = new Object[10]; // начальный размер массива
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            private int lastReturnedIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturnedIndex = currentIndex; // сохраняем индекс последнего возвращенного элемента
                return (E) elements[currentIndex++];
            }

            @Override
            public void remove() {
                if (lastReturnedIndex == -1) {
                    throw new IllegalStateException("Cannot remove element before calling next()");
                }
                MySet.this.remove(elements[lastReturnedIndex]); // удаляем элемент из множества
                currentIndex = lastReturnedIndex; // корректируем индекс текущего элемента
                lastReturnedIndex = -1; // сбрасываем индекс последнего возвращенного элемента
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false; // элемент уже существует
        }
        ensureCapacity();
        elements[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                elements[i] = elements[size - 1]; // заменяем удаляемый элемент последним
                elements[size - 1] = null; // освобождаем место
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2); // удваиваем размер массива
        }
    }
}