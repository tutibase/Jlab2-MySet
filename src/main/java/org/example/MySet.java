package org.example;

import java.util.*;

public class MySet<E> implements Set<E>, Iterable<E> {
    private Object[] elements;
    private int size;
    private final int INIT_SIZE = 10;

    public MySet() {
        elements = new Object[INIT_SIZE]; // начальный размер массива
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
        Object[] array = new Object[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Если переданный массив меньше по размеру, создаем новый массив того же типа
            Object[] array = new Object[size];
            System.arraycopy(elements, 0, array, 0, size);
            return (T[]) array;
        }

        for (int i = 0; i < size; i++){
            a[i] = (T) elements[i];
        }
        return a;
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
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                remove(elements[i]);
                modified = true;
                i--; // Уменьшаем индекс, так как размер множества изменился
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        size = 0;
        elements = new Object[INIT_SIZE];
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2); // удваиваем размер массива
        }
    }
}