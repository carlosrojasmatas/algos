package trees.avl;

import org.junit.jupiter.api.Test;
import trees.avl.AvlTree;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    void testInsert() {
        AvlTree<Integer> tree = new AvlTree<>(false);
        tree.insert(50);
        tree.insert(40);
        tree.insert(60);
        tree.insert(44);
        tree.insert(35);
        tree.insert(55);
        tree.insert(70);

        assertEquals("35 40 44 50 55 60 70", tree.traverse().trim());
        assertEquals(2, tree.depth());

        Node<Integer> root = tree.copyOfRoot();

        assertEquals(Integer.valueOf(50), root.getValue());
        assertEquals(Integer.valueOf(40), root.getLeft().getValue());
        assertEquals(Integer.valueOf(60), root.getRight().getValue());
        assertEquals(Integer.valueOf(44), root.getLeft().getRight().getValue());
        assertEquals(Integer.valueOf(35), root.getLeft().getLeft().getValue());
        assertEquals(Integer.valueOf(55), root.getRight().getLeft().getValue());
        assertEquals(Integer.valueOf(70), root.getRight().getRight().getValue());


        //case I withouth parents
        tree = new AvlTree<>();
        tree.insert(50);
        tree.insert(40);
        tree.insert(30);

        assertEquals("30 40 50", tree.traverse());
        assertEquals(1, tree.depth());

        root = tree.copyOfRoot();
        assertEquals(Integer.valueOf(40), root.getValue());
        assertEquals(Integer.valueOf(30), root.getLeft().getValue());
        assertEquals(Integer.valueOf(50), root.getRight().getValue());

        //case II
        tree = new AvlTree<>();
        tree.insert(50);
        tree.insert(60);
        tree.insert(70);

        root = tree.copyOfRoot();
        assertEquals(Integer.valueOf(60), root.getValue());
        assertEquals(Integer.valueOf(50), root.getLeft().getValue());
        assertEquals(Integer.valueOf(70), root.getRight().getValue());


        assertEquals("50 60 70", tree.traverse());
        assertEquals(1, tree.depth());

        //case III
        tree = new AvlTree<>();
        tree.insert(50);
        tree.insert(40);
        tree.insert(45);

        root = tree.copyOfRoot();
        assertEquals(Integer.valueOf(45), root.getValue());
        assertEquals(Integer.valueOf(40), root.getLeft().getValue());
        assertEquals(Integer.valueOf(50), root.getRight().getValue());

        assertEquals("40 45 50", tree.traverse());
        assertEquals(1, tree.depth());


        //case I deeper in herarchy
        tree = new AvlTree<>();

        tree.insert(50);
        tree.insert(40);
        tree.insert(60);
        tree.insert(35);
        tree.insert(45);
        tree.insert(20);

        assertEquals(2, tree.depth());
        assertEquals("20 35 40 45 50 60", tree.traverse());
        root = tree.copyOfRoot();

        assertEquals(Integer.valueOf(40), root.getValue());
        //left tree
        assertEquals(Integer.valueOf(35), root.getLeft().getValue());
        assertEquals(Integer.valueOf(20), root.getLeft().getLeft().getValue());

        //rigth tree
        assertEquals(Integer.valueOf(50), root.getRight().getValue());
        assertEquals(Integer.valueOf(45), root.getRight().getLeft().getValue());
        assertEquals(Integer.valueOf(60), root.getRight().getRight().getValue());

        //case II deeper in herarchy
        tree = new AvlTree<>();

        tree.insert(50);
        tree.insert(40);
        tree.insert(60);
        tree.insert(35);
        tree.insert(45);
        tree.insert(36);

        assertEquals(2, tree.depth());
        assertEquals("35 36 40 45 50 60", tree.traverse());
        root = tree.copyOfRoot();

        assertEquals(Integer.valueOf(40), root.getValue());
        //left tree
        assertEquals(Integer.valueOf(35), root.getLeft().getValue());
        assertEquals(Integer.valueOf(36), root.getLeft().getRight().getValue());

        //rigth tree
        assertEquals(Integer.valueOf(50), root.getRight().getValue());
        assertEquals(Integer.valueOf(45), root.getRight().getLeft().getValue());
        assertEquals(Integer.valueOf(60), root.getRight().getRight().getValue());

        //case III deeper in herarchy
        tree = new AvlTree<>();

        tree.insert(50);
        tree.insert(40);
        tree.insert(60);
        tree.insert(70);
        tree.insert(55);
        tree.insert(75);

        assertEquals(2, tree.depth());
        assertEquals("40 50 55 60 70 75", tree.traverse());
        root = tree.copyOfRoot();

        assertEquals(Integer.valueOf(60), root.getValue());
        //left tree
        assertEquals(Integer.valueOf(50), root.getLeft().getValue());
        assertEquals(Integer.valueOf(55), root.getLeft().getRight().getValue());
        assertEquals(Integer.valueOf(40), root.getLeft().getLeft().getValue());

        //rigth tree
        assertEquals(Integer.valueOf(70), root.getRight().getValue());
        assertEquals(Integer.valueOf(75), root.getRight().getRight().getValue());

    }


    @Test
    void testDeleteLeaf() {
        AvlTree<Integer> avl = new AvlTree<>(true);

        avl.insert(60);
        avl.insert(50);
        avl.insert(40);
        avl.insert(55);
        avl.insert(70);
        avl.insert(75);

        avl.delete(40);

        assertEquals("50 55 60 70 75", avl.traverse());
        assertEquals(2, avl.depth());

        Node<Integer> root = avl.copyOfRoot();
        assertEquals(Integer.valueOf(60), root.getValue());
        assertEquals(Integer.valueOf(50), root.getLeft().getValue());
        assertEquals(Integer.valueOf(70), root.getRight().getValue());
        assertEquals(Integer.valueOf(75), root.getRight().getRight().getValue());
        assertEquals(Integer.valueOf(55), root.getLeft().getRight().getValue());
        assertTrue(root.getLeft().getLeft() == null);

    }

    @Test
    void testDeleteLeftChild() {
        AvlTree<Integer> avl = new AvlTree<>(true);

        avl.insert(60);
        avl.insert(70);
        avl.insert(50);
        avl.insert(75);
        avl.insert(40);

        avl.delete(50);

        assertEquals("40 60 70 75", avl.traverse());
        assertEquals(2, avl.depth());

        Node<Integer> root = avl.copyOfRoot();
        assertEquals(Integer.valueOf(40), root.getLeft().getValue());

    }

    @Test
    void testDeleteRightChild() {
        AvlTree<Integer> avl = new AvlTree<>(true);

        avl.insert(60);
        avl.insert(70);
        avl.insert(50);
        avl.insert(75);
        avl.insert(40);
        avl.insert(55);

        avl.delete(75);

        assertEquals("40 50 55 60 70", avl.traverse());
        assertEquals(Integer.valueOf(70), avl.copyOfRoot().getRight().getValue());

    }

    @Test
    void testDeleteWithBothChilds() {
        AvlTree<Integer> avl = new AvlTree<>();

        avl.insert(60);
        avl.insert(50);
        avl.insert(70);
        avl.insert(40);
        avl.insert(75);
        avl.insert(55);

        avl.delete(50);

        assertEquals("40 55 60 70 75", avl.traverse());

    }

    @Test
    void testDeleteRoot() {
        AvlTree<Integer> avl = new AvlTree<>();

        avl.insert(60);
        avl.insert(50);
        avl.insert(70);
        avl.insert(40);
        avl.insert(75);
        avl.insert(55);

        avl.delete(60);

        assertEquals("40 50 55 70 75", avl.traverse());
        assertEquals(2, avl.depth());
        assertEquals(Integer.valueOf(55), avl.copyOfRoot().getValue());
        assertEquals(Integer.valueOf(50), avl.copyOfRoot().getLeft().getValue());
        assertNull(avl.copyOfRoot().getLeft().getRight());
    }


    @Test
    void testDeleteAndBalance() {
        AvlTree<Integer> avl = new AvlTree<>();

        avl.insert(60);
        avl.insert(50);
        avl.insert(70);
        avl.insert(40);
        avl.insert(75);
        avl.insert(55);

        avl.delete(50);
        avl.delete(55);
        avl.delete(40);

        assertEquals("60 70 75", avl.traverse());
        assertEquals(1, avl.depth());

        assertEquals(Integer.valueOf(70), avl.copyOfRoot().getValue());
        assertEquals(Integer.valueOf(60), avl.copyOfRoot().getLeft().getValue());
        assertEquals(Integer.valueOf(75), avl.copyOfRoot().getRight().getValue());


        avl = new AvlTree<>();

        avl.insert(60);
        avl.insert(50);
        avl.insert(70);
        avl.insert(40);
        avl.insert(75);
        avl.insert(55);

        avl.delete(75);
        avl.delete(70);


        assertEquals("40 50 55 60",avl.traverse());
        assertEquals(Integer.valueOf(50),avl.copyOfRoot().getValue());
        assertEquals(Integer.valueOf(60),avl.copyOfRoot().getRight().getValue());
        assertEquals(Integer.valueOf(55),avl.copyOfRoot().getRight().getLeft().getValue());
        assertEquals(Integer.valueOf(40),avl.copyOfRoot().getLeft().getValue());

    }

}
