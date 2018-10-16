package trees.bst;

import java.util.List;

public interface TraversalStrategy<T extends Comparable<T>> {

    List<Node<T>> traverse(Node<T> node, List<Node<T>> holder);

}
