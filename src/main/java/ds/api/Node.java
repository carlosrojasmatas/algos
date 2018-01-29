package ds.api;

public class Node<T> {
    private Node<T> next;
    private Node<T> prev;
    private T value;

    public Node(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("{ %s }",value);
    }
}
