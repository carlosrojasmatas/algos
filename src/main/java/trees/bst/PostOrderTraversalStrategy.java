package trees.bst;

import java.util.List;

public class PostOrderTraversalStrategy<T extends Comparable<T>> implements TraversalStrategy<T> {

    @Override
    public List<Node<T>> traverse(Node<T> node, List<Node<T>> holder) {
        if (node.isLeaf()) {
            holder.add(node);
            return holder;
        } else {
            if (node.getLeft().isPresent()) {
                traverse(node.getLeft().get(), holder);
            }

            if (node.getRight().isPresent()) {
                traverse(node.getRight().get(), holder);
            }

            holder.add(node);
        }
        return holder;
    }
}
