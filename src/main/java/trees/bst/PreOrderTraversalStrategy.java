package trees.bst;

import java.util.List;

public class PreOrderTraversalStrategy<T extends Comparable<T>> implements TraversalStrategy<T> {

    public List<Node<T>> traverse(Node<T> node, List<Node<T>> holder) {
        holder.add(node);
        if(node.getLeft().isPresent()) traverse(node.getLeft().get(),holder);
        if(node.getRight().isPresent()) traverse(node.getRight().get(),holder);

        return holder;
    }
}
