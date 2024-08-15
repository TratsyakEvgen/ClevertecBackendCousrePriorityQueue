package ru.clevertec.course;

public interface CustomQueue<E> {
    void add(E element);
    E peek();
    E poll();
    int getSize();
    String toString();
}
