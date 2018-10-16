package trees.avl;

import org.junit.jupiter.api.Test;
import trees.avl.AvlTree;
import trees.bst.Node;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    void testDepth(){
        AvlTree<Integer> avl = new AvlTree<>(false);
        avl.insert(10);
        avl.insert(6);
        avl.insert(4);
        avl.insert(8);
        avl.insert(7);
        avl.insert(9);
        avl.insert(5);
        avl.insert(3);
        avl.insert(2);

        avl.insert(50);
        avl.insert(12);
        avl.insert(60);
        avl.insert(20);
        avl.insert(11);
        avl.insert(55);

        assertEquals(4,avl.height());

        avl.clear();

        avl.insert(50);
        avl.insert(40);
        avl.insert(44);
        avl.insert(39);
        avl.insert(60);
        avl.insert(55);
        avl.insert(70);

        assertEquals(2,avl.height());
    }

    @Test
    void testBalanced() {
        AvlTree<Integer> avl = new AvlTree<>(false);
        avl.insert(10);
        avl.insert(6);
        avl.insert(4);
        avl.insert(8);
        avl.insert(7);
        avl.insert(9);
        avl.insert(5);
        avl.insert(3);
        avl.insert(2);

        avl.insert(50);
        avl.insert(12);
        avl.insert(60);
        avl.insert(20);
        avl.insert(11);
        avl.insert(55);

        assertTrue(avl.isBalanced());

        avl.clear();

        //single left child
        avl.insert(10);
        avl.insert(9);

        assertTrue(avl.isBalanced());

        avl.clear();

        //single right child
        avl.insert(10);
        avl.insert(11);

        //two single childs
        avl.insert(10);
        avl.insert(9);
        avl.insert(11);

        assertTrue(avl.isBalanced());

        //left longer by one
        avl.clear();
        avl.insert(10);
        avl.insert(9);
        avl.insert(8);
        avl.insert(11);

        assertTrue(avl.isBalanced());

        //right longer by one

        avl.clear();
        avl.insert(10);
        avl.insert(11);
        avl.insert(12);
        avl.insert(9);

        assertTrue(avl.isBalanced());


    }

    @Test
    void testUnbalanced() {
        AvlTree<Integer> avl = new AvlTree<>(false);
        /* only left childs
         *         a
         *        /
         *       b
         *      /
         *     c
         * */
        avl.insert(20);
        avl.insert(19);
        avl.insert(18);
        assertFalse(avl.isBalanced());

        /* only right childs
         *         a
         *          \
         *           b
         *            \
         *             c
         * */
        avl.clear();
        avl.insert(10);
        avl.insert(11);
        avl.insert(12);
        assertFalse(avl.isBalanced());

        /* root with one left child with one right child
         *         a
         *        /
         *       b
         *        \
         *         c
         * */
        avl.clear();
        avl.insert(11);
        avl.insert(9);
        avl.insert(10);
        assertFalse(avl.isBalanced());

        /* root with one right child with one left child
         *         a
         *          \
         *           b
         *          /
         *         c
         * */
        avl.clear();
        avl.insert(10);
        avl.insert(12);
        avl.insert(11);
        assertFalse(avl.isBalanced());

    }

    @Test
    void testRightRotation() {

        AvlTree<Integer> tree = new AvlTree<>(false);
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);

        tree.rotateRight(tree.getRoot().get());

        assertEquals(Integer.valueOf(9), tree.getRoot().get().getValue());
        assertEquals(Integer.valueOf(10), tree.getRoot().get().getRight().get().getValue());
        assertEquals(Integer.valueOf(8), tree.getRoot().get().getLeft().get().getValue());

        tree.clear();
        tree.insert(10);
        tree.insert(9);
        tree.insert(8);
        tree.insert(7);

        Node<Integer> nine = tree.searchAsNode(9).get();

        tree.rotateRight(nine);

        Node<Integer> eight = tree.getRoot().get().getLeft().get();
        assertEquals(Integer.valueOf(8), eight.getValue());
        assertEquals(Integer.valueOf(7), eight.getLeft().get().getValue());
        assertEquals(Integer.valueOf(9), eight.getRight().get().getValue());
        assertEquals(Integer.valueOf(8), nine.getParent().get().getValue());
        assertEquals(Integer.valueOf(10), eight.getParent().get().getValue());
        assertTrue(nine.isLeaf());

        tree.clear();

        tree.insert(10);
        tree.insert(7);
        tree.insert(9);

        tree.rotateRight(tree.getRoot().get());

        assertEquals(Integer.valueOf(9),tree.getRoot().get().getValue());
        assertEquals(Integer.valueOf(7),tree.getRoot().get().getLeft().get().getValue());
        assertEquals(Integer.valueOf(10),tree.getRoot().get().getRight().get().getValue());

    }

    @Test
    void testLeftRotation() {

        AvlTree<Integer> tree = new AvlTree<>(false);

        tree.insert(11);
        tree.insert(12);
        tree.insert(13);

        tree.rotateLeft(tree.getRoot().get());

        assertEquals(Integer.valueOf(12), tree.getRoot().get().getValue());
        assertEquals(Integer.valueOf(13), tree.getRoot().get().getRight().get().getValue());
        assertEquals(Integer.valueOf(11), tree.getRoot().get().getLeft().get().getValue());

        tree.clear();
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);

        Node<Integer> twelve = tree.searchAsNode(12).get();

        tree.rotateLeft(twelve);

        Node<Integer> thirteen = tree.getRoot().get().getRight().get();
        assertEquals(Integer.valueOf(13), thirteen.getValue());
        assertEquals(Integer.valueOf(12), thirteen.getLeft().get().getValue());
        assertEquals(Integer.valueOf(14), thirteen.getRight().get().getValue());
        assertEquals(Integer.valueOf(13), twelve.getParent().get().getValue());
        assertEquals(Integer.valueOf(11),thirteen.getParent().get().getValue());
        assertTrue(twelve.isLeaf());

        tree.clear();

        tree.insert(10);
        tree.insert(15);
        tree.insert(12);

        tree.rotateLeft(tree.getRoot().get());

        assertEquals(Integer.valueOf(12),tree.getRoot().get().getValue());
        assertEquals(Integer.valueOf(10),tree.getRoot().get().getLeft().get().getValue());
        assertEquals(Integer.valueOf(15),tree.getRoot().get().getRight().get().getValue());

    }

    @Test
    void testRotate(){
        AvlTree<Integer> tree = new AvlTree<>();

        tree.insert(12);
        tree.insert(11);
        tree.insert(10);
        tree.insert(9);

        assertTrue(tree.isBalanced());

        Node<Integer> root = tree.getRoot().get();
        assertEquals(Integer.valueOf(11),root.getValue());

        Node<Integer> lChild = root.getLeft().get();
        Node<Integer> rChild = root.getRight().get();
        assertEquals(Integer.valueOf(10),lChild.getValue());
        assertEquals(Integer.valueOf(9),lChild.getLeft().get().getValue());
        assertEquals(Integer.valueOf(12),rChild.getValue());

        tree.clear();

        tree.insert(9);
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);

        assertTrue(tree.isBalanced());

        root = tree.getRoot().get();
        assertEquals(Integer.valueOf(10),root.getValue());

        lChild = root.getLeft().get();
        rChild = root.getRight().get();
        assertEquals(Integer.valueOf(9),lChild.getValue());
        assertEquals(Integer.valueOf(11),rChild.getValue());
        assertEquals(Integer.valueOf(12),rChild.getRight().get().getValue());

        tree.clear();

        tree.insert(100);
        tree.insert(20);
        tree.insert(150);
        tree.insert(19);
        tree.insert(30);
        tree.insert(110);
        tree.insert(160);

        assertTrue(tree.isBalanced());

        tree.insert(18);
        tree.insert(17);

        assertTrue(tree.isBalanced());

        Node<Integer> eigthTeen = tree.searchAsNode(18).get();
        Node<Integer> twenty = tree.searchAsNode(20).get();
        assertEquals(Integer.valueOf(17),eigthTeen.getLeft().get().getValue());
        assertEquals(Integer.valueOf(19),eigthTeen.getRight().get().getValue());
        assertEquals(Integer.valueOf(20),eigthTeen.getParent().get().getValue());
        assertEquals(Integer.valueOf(18),twenty.getLeft().get().getValue());


        tree.clear();

        tree.insert(100);
        tree.insert(20);
        tree.insert(150);
        tree.insert(7);
        tree.insert(30);
        tree.insert(110);
        tree.insert(160);

        assertTrue(tree.isBalanced());

        tree.insert(8);
        tree.insert(9);

        assertTrue(tree.isBalanced());

        Node<Integer> eight = tree.searchAsNode(8).get();
        twenty = tree.searchAsNode(20).get();
        assertEquals(Integer.valueOf(7),eight.getLeft().get().getValue());
        assertEquals(Integer.valueOf(9),eight.getRight().get().getValue());
        assertEquals(Integer.valueOf(8),twenty.getRight().get().getValue());


    }

}
