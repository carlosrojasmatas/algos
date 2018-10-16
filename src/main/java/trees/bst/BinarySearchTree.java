package trees.bst;

import sun.tools.jconsole.MaximizableInternalFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BinarySearchTree<T extends Comparable<T>> implements BST<T> {

    protected Optional<Node<T>> root = Optional.empty();


    @Override
    public void insert(T obj) {
        if (root.isPresent())
            root.get().insert(obj);
        else
            root = Optional.of(new Node<>(obj));
    }

    public List<T> traverse(TraversalStrategy<T> strategy) {
        if (!root.isPresent()) return Collections.emptyList();

        return strategy.traverse(root.get(), new ArrayList<>()).stream().map(Node::getValue).collect(Collectors.toList());
    }

    @Override
    public Optional<T> delete(T obj) {

        if (!root.isPresent()) return Optional.empty();

        Optional<Node<T>> subj = root.get().search(obj);

        if (!subj.isPresent())
            return Optional.empty();
        else {
            Node<T> n = subj.get();

            if (n.isLeaf()) { //no child, easiest case
                n.disconnectFromParent();
            } else if (n.hasOnlyLeft()) {
                Node<T> swap = n.getLeft().get();
                n.getParent().get().setLeft(swap);
                n.removeAllChilds();
                n.disconnectFromParent();
            } else if (n.hasOnlyRight()) {
                Node<T> swap = n.getRight().get();
                n.getParent().get().setRight(swap);
                n.removeAllChilds();
                n.disconnectFromParent();
            } else {
                Node<T> r = n.getRight().get();
                Node<T> l = n.getLeft().get();

                if (l.depth() > r.depth()) { //left tree is deeper than right tree so pick the bigger element from the left tree
                    Node<T> swap = max(l);
                    n.setParent(swap.getParent().get());
                    n.removeAllChilds();
                    n.getParent().get().removeChild(n);

                    swap.setRight(n.getRight().get());
                    swap.setLeft(n.getLeft().get());

                } else { // right deeper than left, taking the smaller element from the right tree
                    Node<T> swap = n.getRight().get();
                    swap.setLeft(n.getLeft().get());
                    n.removeAllChilds();
                }
            }

            return n.getOptionValue();
        }

    }

    public List<T> sorted() {

        if (root.isPresent()) {
            TraversalStrategy<T> strategy = new InOrderTraversalStrategy<>();
            return strategy.traverse(root.get(), new ArrayList<>()).stream().map(Node::getValue).collect(Collectors.toList());
        } else return Collections.emptyList();

    }

    private Node<T> max(Node<T> nod) {
        if (!nod.getRight().isPresent()) return nod;
        else return max(nod.getRight().get());
    }

    @Override
    public Optional<T> search(T obj) {

        if (!root.isPresent()) return Optional.empty();
        else {

            Optional<Node<T>> rs = root.get().search(obj);

            if (!rs.isPresent())
                return Optional.empty();
            else
                return rs.get().getOptionValue();

        }
    }

    public Optional<Node<T>> searchAsNode(T obj){
        if (!root.isPresent()) return Optional.empty();
        else {
            return root.get().search(obj);
        }
    }

    @Override
    public void clear() {
        /*TODO revisit the usage of optional here. The boxing object could cause GC problems and potential memory leaks
        see: https://blog.joda.org/2014/11/optional-in-java-se-8.html*/
        this.root = Optional.empty();

    }


    public Optional<T> find(T val) {
        if(!root.isPresent()) return Optional.empty();

        Optional<Node<T>> rs = root.get().search(val);
        if(rs.isPresent()) return root.get().getOptionValue();
        else return Optional.empty();

    }

    @Override
    public int height() {
        return depthOfTree(root);
    }

    protected int depthOfTree(Optional<Node<T>> tree) {
        if (!tree.isPresent()) return -1;
        else {
            return 1 + Math.max(depthOfTree(tree.get().getLeft()), depthOfTree(tree.get().getRight()));
        }
    }

}
