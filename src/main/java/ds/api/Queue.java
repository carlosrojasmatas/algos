package ds.api;

public interface Queue<T> {

    public void add(T obj);
    public T pop();
    public int size();
    public boolean isEmpty();
    public void clear();

}

