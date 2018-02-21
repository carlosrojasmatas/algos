package ds.bst;

import java.util.Optional;

public class BinaryNode<T extends Comparable<T>> {

    private T value;

    private BinaryNode<T> left = null;
    private BinaryNode<T> parent = null;
    private BinaryNode<T> right = null;
    protected int depth = 0;

    public BinaryNode(T value) {
        this.value = value;
    }

    public Optional<BinaryNode<T>> find(T value) {
        if (this.value == value) return Optional.of(this);
        if (this.isLeaf()) return Optional.empty();
        if(left == null && right == null) return Optional.empty();
        if(left == null ) return right.find(value);
        if(right == null) return left.find(value);

        Optional<BinaryNode<T>> r = left.find(value);
        return r.isPresent()?r:right.find(value);

    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public BinaryNode<T> getRoot() {
        if (isRoot()) return this;
        else return parent.getRoot();
    }

    public int depth() {
        return depth;
    }


    public T value() {
        return value;
    }

    public BinaryNode<T> parent() {
        return parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public void insert(T value) {

        if (this.value.compareTo(value) > 0) {
            if(this.left == null) {
                BinaryNode<T> newNode =  new BinaryNode<>(value);
                newNode.parent = this;
                this.left = newNode;
            } else left.insert(value);
        } else if(this.value.compareTo(value) < 0){
            if(this.right == null) {
                BinaryNode<T> newNode =  new BinaryNode<>(value);
                newNode.parent = this;
                this.right = newNode;
            } else right.insert(value);
        }
    }

    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
