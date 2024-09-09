package ru.clevertec.course;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<E> implements Queue<E> {

    private final static int DEFAULT_INIT_CAPACITY = 8;
    private final Comparator<? super E> comparator;
    private E[] queue;
    private int size;

    @SuppressWarnings("unchecked")
    public PriorityQueue() {
        this((Comparator<? super E>) Comparator.naturalOrder());

    }

    @SuppressWarnings("unchecked")
    public PriorityQueue(Comparator<? super E> comparator) {
        this.comparator = comparator;
        this.queue = (E[]) new Object[DEFAULT_INIT_CAPACITY];
        this.size = 0;
    }


    @Override
    public void add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element should not be null");
        }
        put(element);
        siftUp();
    }

    @Override
    public E peek() {
        return queue[0];
    }

    @Override
    public E poll() {
        E result = queue[0];
        if (size > 0) {
            removeLast();
            siftDown();
        }
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(queue);
    }


    private void put(E element) {
        if (queue.length == size) {
            increaseArray();
        }
        queue[size] = element;
        size++;
    }

    private void increaseArray() {
        int newCapacity = size * 2;
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private void siftUp() {
        int childIndex = size - 1;
        int parentIndex = getParentIndex(childIndex);
        while (compareElements(childIndex, parentIndex) < 0) {
            swapElements(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }


    private void removeLast() {
        int lastElement = size - 1;
        queue[0] = queue[lastElement];
        queue[lastElement] = null;
        size--;
    }

    private void siftDown() {
        int parentIndex = 0;
        int left = getLeftChild(parentIndex);

        while (left < size) {
            int right = getRightChild(parentIndex);

            int minChildIndex = left;
            if (right < size && compareElements(right, left) < 0) {
                minChildIndex = right;
            }

            if (compareElements(parentIndex, minChildIndex) < 0) {
                break;
            }

            swapElements(minChildIndex, parentIndex);
            parentIndex = minChildIndex;
            left = getLeftChild(parentIndex);
        }
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChild(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getRightChild(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private int compareElements(int index, int comparableIndex) {
        E element = queue[index];
        E comparableElement = queue[comparableIndex];
        return comparator.compare(element, comparableElement);
    }


    private void swapElements(int index, int swapIndex) {
        E buffer = queue[index];
        queue[index] = queue[swapIndex];
        queue[swapIndex] = buffer;
    }
}
