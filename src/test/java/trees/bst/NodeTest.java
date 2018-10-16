package trees.bst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scala.Int;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class NodeTest {

    @Test
    @DisplayName("Testing node.height()")
    void testDepth() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> n1_1 = new Node<>(2);
        Node<Integer> n1_2 = new Node<>(3);
        Node<Integer> n2_1 = new Node<>(4);
        Node<Integer> n2_2 = new Node<>(5);
        Node<Integer> n3_1 = new Node<>(6);
        Node<Integer> n3_2 = new Node<>(7);

        root.setLeft(n1_1);
        root.setRight(n1_2);

        n1_1.setLeft(n2_1);
        n1_1.setRight(n2_2);

        n2_2.setLeft(n3_1);
        n2_2.setRight(n3_2);

        assertEquals(root.depth(), 0);
        assertEquals(n1_1.depth(), 1);
        assertEquals(n1_2.depth(), 1);
        assertEquals(n2_1.depth(), 2);
        assertEquals(n2_2.depth(), 2);
        assertEquals(n3_1.depth(), 3);
        assertEquals(n3_2.depth(), 3);
    }

    @Test
    @DisplayName("Testing node.insert(ojb)")
    void testInsert() {
        Node<Integer> root = new Node<Integer>(10);
        root.insert(8);
        root.insert(7);
        root.insert(9);
        root.insert(5);
        root.insert(2);

        root.insert(50);
        root.insert(12);
        root.insert(60);
        root.insert(20);
        root.insert(11);
        root.insert(55);

        //check left tree
        Node<Integer> eight = root.getLeft().get();
        Node<Integer> nine = eight.getRight().get();
        Node<Integer> seven = eight.getLeft().get();
        Node<Integer> five = seven.getLeft().get();
        Node<Integer> two = five.getLeft().get();

        assertTrue(8 == eight.getValue());
        assertTrue(9 == nine.getValue());
        assertTrue(7 == seven.getValue());
        assertTrue(5 == five.getValue());
        assertTrue(2 == two.getValue());
        assertTrue(nine.isLeaf());
        assertFalse(seven.getRight().isPresent());
        assertFalse(five.getRight().isPresent());
        assertTrue(two.isLeaf());

        //check right tree
        Node<Integer> fifty = root.getRight().get();
        Node<Integer> twelve = fifty.getLeft().get();
        Node<Integer> sixty = fifty.getRight().get();
        Node<Integer> eleven = twelve.getLeft().get();
        Node<Integer> twenty = twelve.getRight().get();
        Node<Integer> fiftyFive = sixty.getLeft().get();

        assertTrue(fifty.getValue() == 50);
        assertTrue(twelve.getValue() == 12);
        assertTrue(sixty.getValue() == 60);
        assertTrue(eleven.getValue() == 11);
        assertTrue(twenty.getValue() == 20);
        assertTrue(fiftyFive.getValue() == 55);
        assertTrue(eleven.isLeaf());
        assertTrue(twenty.isLeaf());
        assertFalse(sixty.getRight().isPresent());

    }

    @Test
    @DisplayName("Test node.search(s)")
    void testSearch(){
        Node<Integer> root = new Node<Integer>(10);
        root.insert(8);
        root.insert(7);
        root.insert(9);
        root.insert(5);
        root.insert(2);

        root.insert(50);
        root.insert(12);
        root.insert(60);
        root.insert(20);
        root.insert(11);
        root.insert(55);

        Optional<Node<Integer>> rs = root.search(2);
        assertTrue(rs.isPresent() && rs.get().getValue() == 2);

        rs = root.search(11);
        assertTrue(rs.isPresent() && rs.get().getValue() == 11);

        rs = root.search(50);
        assertTrue(rs.isPresent() && rs.get().getValue() == 50);

        rs = root.search(22);
        assertTrue(!rs.isPresent());

        rs = root.search(61);
        assertTrue(!rs.isPresent());

    }


}
