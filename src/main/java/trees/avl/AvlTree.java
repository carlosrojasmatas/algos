package trees.avl;

import trees.bst.BinarySearchTree;
import trees.bst.Node;

import java.util.Optional;

public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    private boolean autoBalance = true;

    AvlTree() {

    }

    AvlTree(boolean autoBalance) {
        this.autoBalance = autoBalance;
    }

    @Override
    public void insert(T obj) {
        super.insert(obj);
        if (autoBalance && !isBalanced()) rotate(root);
    }


    boolean isBalanced() {
        if(!root.isPresent()) return false;

        Node<T> r = root.get();
        if(r.isLeaf()) return true;

        return Math.abs(depthOfTree(r.getRight()) - depthOfTree(r.getLeft())) <= 1;
    }


    void rotateRight(Node<T> node) {

        Node<T> leftChild = node.getLeft().get();
        /*
         * change |  to    |
         *        /       /
         *        \      /
         * */
        if(leftChild.hasOnlyRight()){
            Node<T> rigthGrandChild = leftChild.getRight().get();
            node.setLeft(rigthGrandChild);
            rigthGrandChild.setLeft(leftChild);
            leftChild.removeAllChilds();
            leftChild = node.getLeft().get();
        }
        if (node.isRoot()) {
            leftChild.setParent(null);
            setRoot(leftChild);
        } else {
            leftChild.setParent(node.getParent().get());
            node.getParent().get().setLeft(leftChild);
        }
        leftChild.setRight(node);
        node.setParent(leftChild);
        node.removeAllChilds();
    }

    void rotateLeft(Node<T> node) {

        Node<T> rightChild = node.getRight().get();
        /*
        * change |  to    |
        *         \        \
        *        /          \
        * */
        if(rightChild.hasOnlyLeft()){
            Node<T> leftGrandChild = rightChild.getLeft().get();
            node.setRight(leftGrandChild);
            leftGrandChild.setRight(rightChild);
            rightChild.removeAllChilds();
            rightChild = node.getRight().get();
        }

        if (node.isRoot()) {
            rightChild.setParent(null);
            setRoot(rightChild);
        } else {
            rightChild.setParent(node.getParent().get());
            node.getParent().get().setRight(rightChild);
        }

        rightChild.setLeft(node);
        node.removeAllChilds();
        node.setParent(rightChild);


    }

    void rotate(Optional<Node<T>> root) {

        if(!root.isPresent()) throw new IllegalStateException("Something went wrong");

        if(depthOfTree(root) == 2){
            if(root.get().hasOnlyRight()) rotateLeft(root.get());
            else rotateRight(root.get());
        }else{

            int leftDepth = depthOfTree(root.get().getLeft());
            int rightDepth = depthOfTree(root.get().getRight());

            if (leftDepth > rightDepth) rotate(root.get().getLeft());
            else rotate(root.get().getRight());
        }


    }


    private void setRoot(Node<T> newRoot) {
        this.root = Optional.of(newRoot);
    }

    Optional<Node<T>> getRoot() {
        return root;
    }
}
