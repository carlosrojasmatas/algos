package trees.avl;

import trees.Tree;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    private boolean autoBalance = true;


    public AvlTree() {
    }

    public AvlTree(boolean autoBalance) {
        this.autoBalance = autoBalance;
    }

    @Override
    public void insert(T value) {
        root = insert(root, value);
    }

    @Override
    public String traverse() {
        if (root != null) {
            return inOrderTraverse(root, new StringBuffer());
        }

        return "";
    }

    public int depth() {

        if (root == null) throw new IllegalStateException("The tree must be initialized at least with one root node");
        return height(root);

    }

    public void delete(T value){
        root = delete(root,value);
    }

    Node<T> copyOfRoot() {
        return copy(root);
    }

    private Node<T> copy(Node<T> node) {

        if (node == null) return node;

        Node<T> newNode = new Node<>(node.getValue());
        newNode.setLeft(copy(node.getLeft()));
        newNode.setRight(copy(node.getRight()));
        return newNode;

    }

    private int height(Node node) {

        if (node == null) return -1;

        return Math.max(1 + height(node.getLeft()), 1 + height(node.getRight()));

    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) return node;

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(delete(node.getLeft(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRight(delete(node.getRight(), value));
        } else {

            if (node.isLeaf()) {
                return null;
            } else if (node.hasTwoChilds()) {
                Node<T> predecessor = predecessorOf(node);
                node.setValue(predecessor.getValue());
                node.setLeft(delete(node.getLeft(), predecessor.getValue()));

            } else if (node.getRight() == null) { //no rigth child
                node = node.getLeft();
            } else if (node.getLeft() == null) { //no left child
                node = node.getRight();
            }

        }

        if(node != null)
            node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        if (autoBalance) {
            node = deletionBalance(node);
        }

        return node;

    }

    private Node<T> deletionBalance(Node<T> node){
        int b = compareBalance(node);

        if( b > 1 ){
            if(compareBalance(node.getLeft()) > 1){
                node.setLeft(rotateRigth(node.getLeft()));
            }else if(compareBalance(node.getLeft()) < -1){
                node.setLeft(rotateLeft(node.getLeft()));
            }
            node = rotateRigth(node);
        }else if(b < -1){
            if(compareBalance(node.getRight()) > 1 ){
                node.setRight(rotateRigth(node.getRight()));
            }else if(compareBalance(node.getRight()) < -1)
                node.setRight(rotateLeft(node.getRight()));
            node = rotateLeft(node);
        }

        return node;
    }

    private Node<T> predecessorOf(Node<T> node) {
        Node<T> curr = node.getLeft();

        while (curr.getRight() != null) {
            curr = curr.getRight();
        }

        return curr;
    }


    private Node<T> insert(Node<T> node, T value) {

        if (node == null) return new Node(value);

        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(insert(node.getLeft(), value));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setRight(insert(node.getRight(), value));
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        if (autoBalance) {
            node = insertionBalance(node, value);
        }

        return node;
    }

    private Node<T> insertionBalance(Node<T> node, T data) {
        int b = compareBalance(node);

        //case I
        if (b > 1 && data.compareTo(node.getLeft().getValue()) < 0) {
            //left unbalanced
            node = rotateRigth(node);
            //case II
        } else if (b < -1 && data.compareTo(node.getRight().getValue()) > 0) {
            //rigthUnbalanced
            node = rotateLeft(node);
        }

        //case III
        else if (b > 1 && data.compareTo(node.getLeft().getValue()) > 0) {
            Node<T> left = rotateLeft(node.getLeft());
            node.setLeft(left);
            node = rotateRigth(node);
        }

        //case IV
        else if (b < -1 && data.compareTo(node.getRight().getValue()) < 0) {
            Node<T> right = rotateRigth(node.getRight());
            node.setRight(right);
            node = rotateLeft(node);
        }

        return node;
    }


    private int compareBalance(Node<T> node) {
        return height(node.getLeft()) - height(node.getRight());
    }

    private Node rotateLeft(Node<T> node) {
        Node<T> newRoot = node.getRight();
        Node<T> leftTmp = newRoot.getLeft();

        node.setRight(leftTmp);
        node.setParent(newRoot);
        newRoot.setLeft(node);

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        newRoot.setHeight(Math.max(height(newRoot.getRight()), height(newRoot.getRight())) + 1);

        return newRoot;
    }

    private Node rotateRigth(Node<T> node) {
        Node<T> newRoot = node.getLeft();
        Node<T> tempLeft = newRoot.getRight();

        newRoot.setRight(node);
        node.setLeft(tempLeft);

        //update the heights
        newRoot.setHeight(Math.max(height(newRoot.getLeft()), height(newRoot.getRight())) + 1);
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        return newRoot;

    }


    private String inOrderTraverse(Node<T> node, StringBuffer buffer) {

        if (node.getLeft() != null) inOrderTraverse(node.getLeft(), buffer);

        buffer.append(node.getValue() + " ");

        if (node.getRight() != null) inOrderTraverse(node.getRight(), buffer);

        return buffer.toString().trim();

    }
}
