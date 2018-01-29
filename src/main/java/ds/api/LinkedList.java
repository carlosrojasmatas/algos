package ds.api;

public interface LinkedList<T> {

    public boolean isEmpty();

    public void insertFist(T ojb);

    public void insertLast(T obj);

    public void insertAt(int idx,T ojb);

    public void insertAfter(T obj,T value);

    public T deleteFirst();

    public T deleteLast();

    public T remove(T obj);

    public T get(int idx);

    public int size();

    public T getFirst();

    public T getLast();

    public int indexOf(T obj);

}
