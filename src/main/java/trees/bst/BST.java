package trees.bst;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BST<T extends Comparable<T>> {

    List<T> traverse(TraversalStrategy<T> strategy);

    void insert(T obj);

    Optional<T> delete(T obj);

    Optional<T> search(T obj);

    int height();

    List<T> sorted();

    void clear();

    Optional<T> find(T val);

}
