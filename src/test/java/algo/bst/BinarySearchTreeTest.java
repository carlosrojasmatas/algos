package algo.bst;

import ds.bst.BinaryTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BinarySearchTreeTest {


    @Test
    @DisplayName("Test insertion of elements")
    void testInsert(){

        BinaryTree<Integer> tree = new BinaryTree<>(10);
        tree.insert(11);
        tree.insert(9);
        tree.insert(15);
        tree.insert(6);
        tree.insert(4);
        tree.insert(100);
        tree.insert(65);
        tree.insert(33);
        assertEquals(9,tree.size());
        assertEquals(5,tree.depth());


        tree = new BinaryTree<>(10);
        tree.insert(5);
        tree.insert(40);
        tree.insert(2);
        tree.insert(6);
        tree.insert(42);
        tree.insert(38);

        assertEquals(7,tree.size());
        assertEquals(2,tree.depth());

    }

    @Test
    @DisplayName("Test find")
    void find(){

        BinaryTree<Integer> tree = new BinaryTree<>(10);
        tree.insert(11);
        tree.insert(9);
        tree.insert(15);
        tree.insert(6);
        tree.insert(4);
        tree.insert(100);
        tree.insert(65);
        tree.insert(33);
        assertEquals(Optional.of(9),tree.find(9));
        assertEquals(Optional.of(6),tree.find(6));
        assertEquals(Optional.of(100),tree.find(100));
        assertEquals(Optional.of(65),tree.find(65));
        assertEquals(Optional.of(10),tree.find(10));
        assertEquals(Optional.empty(),tree.find(110));
        assertEquals(Optional.empty(),tree.find(22));
        assertEquals(Optional.empty(),tree.find(2));

    }


    @Test
    @DisplayName("Test if it's balanced")
    void balance(){
        BinaryTree<Integer> tree = new BinaryTree<>(10);
        tree.insert(5);
        tree.insert(40);
        tree.insert(2);
        tree.insert(6);
        tree.insert(42);
        tree.insert(38);

        System.out.println(tree.printTree());
        assertTrue(tree.isBalanced());

        tree.insert(100);
        assertTrue(tree.isBalanced());

        tree.insert(1);
        assertTrue(tree.isBalanced());

        tree.insert(1000);
        tree.insert(1001);
        assertFalse(tree.isBalanced());

        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        assertTrue(tree.isBalanced());
    }

}
