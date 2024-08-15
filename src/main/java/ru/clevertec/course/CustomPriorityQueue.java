package ru.clevertec.course;

import java.util.Arrays;
import java.util.Comparator;

public class CustomPriorityQueue<E> implements CustomQueue<E> {

    private final static int DEFAULT_INIT_CAPACITY = 8;
    private final Comparator<? super E> comparator;
    private E[] queue;
    private int size;

    @SuppressWarnings("unchecked")
    public CustomPriorityQueue() {
        this((o1, o2) -> {
            Comparable<E> comparable = (Comparable<E>) o1;
            return comparable.compareTo(o2);
        });

    }

    @SuppressWarnings("unchecked")
    public CustomPriorityQueue(Comparator<? super E> comparator) {
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
        remove();
        siftDown();
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
        int newCapacity = size == 8 ? 15 : size * 2 + 1;
        queue = Arrays.copyOf(queue, newCapacity);
    }

    private void siftUp() {
        int childIndex = size - 1;
        int parentIndex = getParentIndex(childIndex);
        while (compareCondition(childIndex, parentIndex)) {
            swapElements(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }
    }

    private int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;
    }

    private void remove() {
        if (size > 0) {
            int lastElement = size - 1;
            queue[0] = queue[lastElement];
            queue[lastElement] = null;
            size--;
        }
    }

    private void siftDown() {
        if (size > 0) {
            int parentIndex = 0;
            int childIndex = getChildIndex(parentIndex);
            while (compareCondition(childIndex, parentIndex)) {
                swapElements(childIndex, parentIndex);
                parentIndex = childIndex;
                childIndex = getChildIndex(parentIndex);
            }
        }
    }

    private int getChildIndex(int parentIndex) {
        int left = parentIndex * 2 + 1;
        if (left >= size) {
            return parentIndex;
        }

        int right = left + 1;
        if (right >= size) {
            return left;
        }

        if (compareCondition(left, right)) {
            return left;
        }

        return right;
    }


    private boolean compareCondition(int comparableIndex, int index) {
        return comparator.compare(queue[comparableIndex], queue[index]) < 0;
    }

    private void swapElements(int index, int swapIndex) {
        E buffer = queue[index];
        queue[index] = queue[swapIndex];
        queue[swapIndex] = buffer;
    }
}
