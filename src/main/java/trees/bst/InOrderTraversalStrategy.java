package trees.bst;

import java.util.List;

public class InOrderTraversalStrategy<T extends Comparable<T>> implements TraversalStrategy<T>{

    public List<Node<T>> traverse(Node<T> n, List<Node<T>> holder) {
        if (n.getLeft().isPresent()) traverse(n.getLeft().get(), holder);

        holder.add(n);

        if (n.getRight().isPresent()) traverse(n.getRight().get(), holder);


        return holder;
    }
}
