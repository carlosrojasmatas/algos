package ds.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BinaryTree<T extends Comparable<T>> {

    private BinaryNode<T> root;

    public BinaryTree(T value) {
        this.root = new BinaryNode<>(value);
    }

    public void insert(T value) {
        root.insert(value);
    }

    public Optional<T> find(T value) {
        return root.find(value).map(BinaryNode::value);
    }

    public int depth() {
        return findDepth(root);
    }

    private int findDepth(BinaryNode<T> subtree) {
        if (subtree == null) return 0;
        int leftDepth = findDepth(subtree.getLeft());
        int rigthDepth = findDepth(subtree.getRight());
        if (subtree.isRoot()) return leftDepth > rigthDepth ? leftDepth : rigthDepth;
        if (leftDepth > rigthDepth) return leftDepth + 1;
        else return rigthDepth + 1;
    }

    public boolean isBalanced() {
        int leftDepth = findDepth(root.getLeft()) + 1;
        int rigthDepth = findDepth(root.getRight()) + 1;
        return Math.abs(leftDepth - rigthDepth) <= 1;
    }

    public int size() {
        return countNodes(root);
    }

    private int countNodes(BinaryNode<T> subtree) {
        if (subtree == null) return 0;
        return 1 + countNodes(subtree.getLeft()) + countNodes(subtree.getRight());
    }

    public String printTree() {
        List<List<T>> buff = new ArrayList<>();
        for (int i = 0; i <= depth(); i++) {
            buff.add(new ArrayList<>());
        }

        List<List<T>> printableTree = printLevel(root, buff, 0);
        int maxElWidth =
                buff.get(buff.size() - 1).stream().map(e -> e.toString().length()).reduce((a, b) -> a + b).get();
        int elementsCount = depth() * 2;
        int finalWidth = ((elementsCount - 1) * 2) + maxElWidth;
        List<String> r = printableTree.stream()
                .map(lev -> lev.stream().map(Object::toString).collect(Collectors.toList()))
                .map(l -> {
                    String s = String.join("  ", l);
                    int padSize = finalWidth - s.length();
                    int padStart = s.length() + padSize / 2;
                    s = String.format("%" + padStart + "s", s);
                    s = String.format("%-" + finalWidth + "s", s);
                    return s;
                })
                .collect(Collectors.toList());


        return String.join("\n", r);
    }

    private List<List<T>> printLevel(BinaryNode<T> subtree, List<List<T>> buffer, int currLevel) {

        if (subtree.isLeaf()) {
            List<T> sb = buffer.get(currLevel);
            if (sb == null) {
                sb = new ArrayList<>();
                buffer.set(currLevel, sb);
            }
            sb.add(subtree.value());
        } else {
            if (subtree.getLeft() != null) printLevel(subtree.getLeft(), buffer, currLevel + 1);
            if (subtree.getRight() != null) printLevel(subtree.getRight(), buffer, currLevel + 1);
            List<T> sb = buffer.get(currLevel);
            if (sb == null) {
                sb = new ArrayList<>();
                buffer.set(currLevel, sb);
            }
            sb.add(subtree.value());
        }
        return buffer;
    }


}
