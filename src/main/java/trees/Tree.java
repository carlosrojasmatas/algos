package trees;

public interface Tree<T extends Comparable<T>> {

    public void insert(T value);
    public String traverse();
}
