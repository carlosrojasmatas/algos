package trees.avl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class NodeTest {


    @Test
    void testHeight(){

        Node<Integer> root = new Node<>(50);

        assertEquals(0,root.getHeight());

        Node<Integer> twenty = new Node<>(20);
        Node<Integer> sixty = new Node<>(60);


        root.setLeft(twenty);
        root.setRight(sixty);

        assertEquals(1,twenty.getHeight());
        assertEquals(1,sixty.getHeight());
        assertEquals(0,root.getHeight());

        Node<Integer> nineteen = new Node<>(19);
        Node<Integer> twentyone = new Node<>(21);

        twenty.setLeft(nineteen);
        twenty.setRight(twentyone);

        Node<Integer> seventy = new Node<>(70);
        Node<Integer> fiftyfive = new Node<>(55);

        sixty.setLeft(fiftyfive);
        sixty.setRight(seventy);

        assertEquals(2,nineteen.getHeight());
        assertEquals(2,twentyone.getHeight());
        assertEquals(2,seventy.getHeight());
        assertEquals(2,fiftyfive.getHeight());
        assertEquals(1,twenty.getHeight());
        assertEquals(1,sixty.getHeight());
        assertEquals(0,root.getHeight());


    }
}
