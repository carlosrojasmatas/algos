package trees.bst;

import scala.None;
import scala.Option;

import java.util.Optional;

public class Node<T extends Comparable<T>> {

    private final T value;
    private Optional<Node<T>> parent = Optional.empty();
    private Optional<Node<T>> right = Optional.empty();
    private Optional<Node<T>> left = Optional.empty();


    public Node(T value) {
        this.value = value;
    }

    public boolean isRoot() {
        return !parent.isPresent();
    }

    public boolean isLeaf() {
        return !right.isPresent() && !left.isPresent();
    }

    public boolean hasTwoChilds() {
        return (this.right.isPresent() && this.left.isPresent());
    }

    public boolean hasOnlyLeft() {
        return !this.right.isPresent() && this.left.isPresent();
    }

    public boolean hasOnlyRight() {
        return !this.left.isPresent() && this.right.isPresent();
    }


    public void insert(T child) {
        Node<T> n = new Node<>(child);
        if (this.value.compareTo(child) < 0) {

            if (!right.isPresent()) {
                n.setParent(this);
                right = Optional.of(n);
            } else right.get().insert(child);

        } else {

            if (!left.isPresent()) {
                n.setParent(this);
                left = Optional.of(n);
            } else left.get().insert(child);

        }
    }

    public Optional<Node<T>> search(T subj) {

        if (this.value.equals(subj)) return Optional.of(this);
        else {

            if (this.value.compareTo(subj) < 0) { //go to right
                if (!this.right.isPresent()) return Optional.empty();
                else return this.right.get().search(subj);
            } else { //go to left
                if (!this.left.isPresent()) return Optional.empty();
                else return this.left.get().search(subj);
            }
        }

    }

    public void removeChild(Node<T> child) {

        if (this.left.isPresent() && this.left.get().value.equals(child.value)) {
            this.left = Optional.empty();
        } else {
            this.right = Optional.empty();
        }

    }

    public void removeAllChilds() {
        this.left = Optional.empty();
        this.right = Optional.empty();
    }

    public void disconnectFromParent() {
        if (this.isRoot()) throw new IllegalStateException("Can not disconnect a root node");
        this.parent.get().removeChild(this);
        this.parent = Optional.empty();
    }


    public int depth() {
        if (isRoot()) return 0;
        else {
            return 1 + parent.get().depth();
        }
    }

    public T getValue() {
        return value;
    }

    public Optional<T> getOptionValue() {
        return Optional.of(this.value);
    }

    public Optional<Node<T>> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = Optional.ofNullable(parent);
    }

    public Optional<Node<T>> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        right.setParent(this);
        this.right = Optional.of(right);
    }

    public Optional<Node<T>> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        left.setParent(this);
        this.left = Optional.of(left);
    }

}
