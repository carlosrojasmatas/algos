package ds.impl;

import ds.api.LinkedList;
import ds.api.Node;

public class CircularDoublyLinkedList<T> implements LinkedList<T> {


    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public T get(int idx) {
        return getNode(idx).getValue();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insertFist(T obj) {
        Node<T> newNode = new Node<>(obj);
        if (size == 0) {
            first = newNode;
            last = first;
            first.setNext(last);
            first.setPrev(last);
            last.setNext(first);
            last.setPrev(first);
        } else {
            Node<T> curr = first;
            first = newNode;
            first.setNext(curr);
            first.setPrev(last);
            curr.setPrev(first);
        }
        ++size;
    }

    @Override
    public void insertLast(T obj) {

        Node<T> newNode = new Node<>(obj);

        if (isEmpty()) {
            first = newNode;
            last = newNode;
            first.setNext(last);
            first.setPrev(last);
            last.setNext(first);
            last.setPrev(first);

        } else {
            Node<T> curr = last;
            last = newNode;
            last.setPrev(curr);
            last.setNext(first);
            curr.setNext(last);
        }
        ++size;

    }

    @Override
    public void insertAt(int idx, T obj) {
        if (idx > size) throw new IllegalStateException("Position does not exist");
        if (idx == 0) insertFist(obj);
        else if (idx == size) insertLast(obj);
        else {
            Node<T> newNode = new Node<>(obj);
            Node<T> curr = getNode(idx);
            Node<T> prev = curr.getPrev();
            prev.setNext(newNode);
            newNode.setPrev(prev);
            newNode.setNext(curr);
            curr.setPrev(newNode);
            ++size;
        }

    }


    @Override
    public T deleteFirst() {

        if (size == 0) {
            throw new IllegalStateException("List is empty");
        } else {
            T value = first.getValue();
            Node<T> newFirst = first.getNext();
            newFirst.setPrev(last);
            first = newFirst;
            --size;
            return value;
        }

    }

    @Override
    public T deleteLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        } else {
            T value = last.getValue();
            Node<T> newLast = last.getPrev();
            newLast.setNext(first);
            last = newLast;
            --size;
            return value;
        }
    }

    @Override
    public void insertAfter(T obj, T value) {

        Node<T> newNode = new Node<>(obj);
        int id = indexOf(obj);
        if (id == -1) throw new IllegalArgumentException(String.format("Value %s not found", obj));
        insertAt(id + 1, value);

    }

    private T deleteKey(int key) {
        if (key == 0) return deleteFirst();
        if (key == size - 1) return deleteLast();
        Node<T> toDelete = getNode(key);
        Node<T> prev = toDelete.getPrev();
        Node<T> next = toDelete.getNext();

        prev.setNext(next);
        next.setPrev(prev);
        toDelete.setPrev(null);// ensure quick gc
        toDelete.setNext(null);
        --size;
        return toDelete.getValue();
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return first.getValue();
    }

    @Override
    public T getLast() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return last.getValue();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T remove(T obj) {
        int id = indexOf(obj);
        return deleteKey(id);
    }

    @Override
    public int indexOf(T obj) {
        if (obj.equals(first.getValue())) return 0;
        if (obj.equals(last.getValue())) return size - 1;
        int idx = 0;
        Node<T> curr = first;
        while (!curr.getValue().equals(obj) && !(idx >= size)) {
            ++idx;
            curr = curr.getNext();
        }
        return (idx >= size ? -1 : idx);
    }

    private Node<T> getNode(int idx) {

        if (idx > size) throw new IllegalStateException("Index out of bounds");
        if (size == 0) throw new IllegalStateException("List is empty");

        Node<T> curr = first;
        for (int i = 0; i != idx; i++) {
            curr = curr.getNext();
        }

        return curr;
    }


//    @Override
//    public String toString() {
//        Node<T> curr = first;
//        StringBuffer buff = new StringBuffer();
//        while (curr != null) {
//            buff.append(curr.toString());
//            curr = curr.getNext();
//        }
//        return buff.toString();
//    }
}
