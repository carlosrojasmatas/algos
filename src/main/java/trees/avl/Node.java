package trees.avl;

public class Node<T extends Comparable<T>> {

    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;

    private T value;
    private int height = 0;


    public Node(T value) {
        this.value = value;
    }


    public void setRight(Node<T> right) {
        this.right = right;
        if (right != null)
            right.setParent(this);
    }

    public void setLeft(Node<T> left) {
        this.left = left;
        if (left != null) {
            left.setParent(this);
        }
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
        this.height = this.parent.height + 1;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {

        this.height = height;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    public boolean hasTwoChilds(){

        return right != null && left != null;
    }

    @Override
    public String toString() {
        return "Node[value=" + value + ", height=" + height + "]";
    }
}
