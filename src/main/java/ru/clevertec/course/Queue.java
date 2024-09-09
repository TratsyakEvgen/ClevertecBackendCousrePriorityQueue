package ru.clevertec.course;

public interface Queue<E> {
    void add(E element);
    E peek();
    E poll();
    int getSize();
    String toString();
}
