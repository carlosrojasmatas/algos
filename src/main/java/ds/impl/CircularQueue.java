package ds.impl;

import ds.api.Queue;

import java.util.Arrays;

public class CircularQueue<T> implements Queue<T> {
    private final int capacity;
    private T[] holder;
    private int rear = -1;
    private int front = -1;
    private int currSize = 0;

    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity) {
        holder = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public void add(T obj) {
        if((rear == capacity - 1 && front == 0) || front == rear + 1) throw new IllegalStateException("Queue is full");

        if(rear == capacity -1  && front != 0) rear = -1;
        if(front == -1) front = 0;
        holder[++rear]= obj;
        currSize++;

    }

    @Override
    public T pop() {

        if(currSize == 0) throw new IllegalStateException("Queue is empty");
        if(front == capacity) front = 0;
        T obj = holder[front];
        currSize--;
        if(currSize == 0 )this.clear();
        else front++;
        return obj;
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public boolean isEmpty() {
        return currSize == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.currSize = 0;
        this.front = -1;
        this.rear = -1;
        this.holder = (T[]) new Object[capacity];
    }

    @Override
    public String toString() {
        return "[" + Arrays.stream(holder).map(Object::toString).reduce("", (a, b) -> {
            if (a.isEmpty()) return b;
            else return a + "," + b;
        }) + "]";
    }
}
