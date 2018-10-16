package trees.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest {

    @Test
    void testSortAndInOrderTraversal(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(6);
        bst.insert(4);
        bst.insert(8);
        bst.insert(7);
        bst.insert(9);
        bst.insert(5);
        bst.insert(3);
        bst.insert(2);

        bst.insert(50);
        bst.insert(12);
        bst.insert(60);
        bst.insert(20);
        bst.insert(11);
        bst.insert(55);

        List<Integer> rs = bst.sorted();

        List<Integer> exp = new ArrayList<>();
        exp.add(2);
        exp.add(3);
        exp.add(4);
        exp.add(5);
        exp.add(6);
        exp.add(7);
        exp.add(8);
        exp.add(9);
        exp.add(10);
        exp.add(11);
        exp.add(12);
        exp.add(20);
        exp.add(50);
        exp.add(55);
        exp.add(60);

        assertEquals(exp,rs);

    }

    @Test
    void testPreOrderTraversal(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(6);
        bst.insert(4);
        bst.insert(8);
        bst.insert(7);
        bst.insert(9);
        bst.insert(5);
        bst.insert(3);
        bst.insert(2);

        bst.insert(50);
        bst.insert(12);
        bst.insert(60);
        bst.insert(20);
        bst.insert(11);
        bst.insert(55);

        List<Integer> rs = bst.traverse(new PreOrderTraversalStrategy<Integer>());

        List<Integer> exp = new ArrayList<>();
        exp.add(10);
        exp.add(6);
        exp.add(4);
        exp.add(3);
        exp.add(2);
        exp.add(5);
        exp.add(8);
        exp.add(7);
        exp.add(9);
        exp.add(50);
        exp.add(12);
        exp.add(11);
        exp.add(20);
        exp.add(60);
        exp.add(55);

        assertEquals(exp,rs);

    }


    @Test
    void testPostOrderTraversalStrategy(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(6);
        bst.insert(4);
        bst.insert(8);
        bst.insert(7);
        bst.insert(9);
        bst.insert(5);
        bst.insert(3);
        bst.insert(2);

        bst.insert(50);
        bst.insert(12);
        bst.insert(60);
        bst.insert(20);
        bst.insert(11);
        bst.insert(55);

        List<Integer> rs = bst.traverse(new PostOrderTraversalStrategy<>());

        List<Integer> exp = new ArrayList<>();
        exp.add(2);
        exp.add(3);
        exp.add(5);
        exp.add(4);
        exp.add(7);
        exp.add(9);
        exp.add(8);
        exp.add(6);
        exp.add(11);
        exp.add(20);
        exp.add(12);
        exp.add(55);
        exp.add(60);
        exp.add(50);
        exp.add(10);

        assertEquals(exp,rs);

    }

    @Test
    void testDepth(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(10);
        bst.insert(6);
        bst.insert(4);
        bst.insert(8);
        bst.insert(7);
        bst.insert(9);
        bst.insert(5);
        bst.insert(3);
        bst.insert(2);

        bst.insert(50);
        bst.insert(12);
        bst.insert(60);
        bst.insert(20);
        bst.insert(11);
        bst.insert(55);

        assertEquals(4,bst.height());
    }
}
