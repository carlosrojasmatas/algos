package ds;

import ds.api.LinkedList;
import ds.impl.CircularDoublyLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LinkedListTest {

    @Test
    @DisplayName("Inserting at the first position")
    void testInsertFirst() {
        LinkedList<String> ll = new CircularDoublyLinkedList<>();
        ll.insertFist("A");
        assertEquals(1, ll.size());
        String r = ll.get(0);
        assertEquals("A", r);
        r = ll.getFirst();
        assertEquals("A", r);
        r = ll.getLast();
        assertEquals("A", r);

        ll.insertFist("B");
        assertEquals(2, ll.size());
        r = ll.get(0);
        assertEquals("B", r);
        r = ll.getFirst();
        assertEquals("B", r);
        r = ll.getLast();
        assertEquals("A", r);

        ll.insertFist("C");
        assertEquals(3, ll.size());
        r = ll.get(0);
        assertEquals("C", r);
        r = ll.getFirst();
        assertEquals("C", r);
        r = ll.get(1);
        assertEquals("B", r);
        r = ll.getLast();
        assertEquals("A", r);

    }

    @Test
    @DisplayName("Inserting at the last position")
    void testInsertLast() {
        LinkedList<String> ll = new CircularDoublyLinkedList<>();
        ll.insertLast("A");
        assertEquals(1, ll.size());
        String r = ll.get(ll.size() - 1);
        assertEquals("A", r);
        r = ll.getFirst();
        assertEquals("A", r);
        r = ll.getLast();
        assertEquals("A", r);

        ll.insertLast("B");
        assertEquals(2, ll.size());
        r = ll.get(0);
        assertEquals("A", r);
        r = ll.get(ll.size() - 1);
        assertEquals("B", r);
        r = ll.getFirst();
        assertEquals("A", r);
        r = ll.getLast();
        assertEquals("B", r);

        ll.insertLast("C");
        assertEquals(3, ll.size());
        r = ll.get(0);
        assertEquals("A", r);
        r = ll.get(ll.size() - 1);
        assertEquals("C", r);
        r = ll.get(1);
        assertEquals("B", r);
        r = ll.getFirst();
        assertEquals("A", r);
        r = ll.getLast();
        assertEquals("C", r);


    }

    @Test
    @DisplayName("Inserting at the first and last position")
    void testInsertFirstAndLAst() {
        LinkedList<String> ll = new CircularDoublyLinkedList<>();
        ll.insertFist("A");
        assertEquals(1,ll.size());

        ll.insertLast("Z");
        assertEquals(2,ll.size());
        assertEquals("A",ll.getFirst());
        assertEquals("Z",ll.getLast());

        ll.insertFist("B");
        assertEquals(3,ll.size());
        assertEquals("B",ll.getFirst());
        assertEquals("A",ll.get(1));
        assertEquals("Z",ll.getLast());

        ll.insertLast("C");
        assertEquals(4,ll.size());
        assertEquals("B",ll.getFirst());
        assertEquals("A",ll.get(1));
        assertEquals("Z",ll.get(ll.size() - 2));
        assertEquals("C",ll.getLast());
    }

    @Test
    @DisplayName("Inserting at a given position")
    void testInsertAt() {
        LinkedList<String> ll = new CircularDoublyLinkedList<>();
        ll.insertAt(0,"A");
        assertEquals(1,ll.size());
        assertEquals("A",ll.getFirst());
        assertEquals("A",ll.get(0));

        ll.insertAt(0,"B");
        assertEquals(2,ll.size());
        assertEquals("B",ll.getFirst());
        assertEquals("B",ll.get(0));
        assertEquals("A",ll.get(1));

        ll.insertAt(1,"C");
        assertEquals(3,ll.size());
        assertEquals("B",ll.getFirst());
        assertEquals("B",ll.get(0));
        assertEquals("C",ll.get(1));
        assertEquals("A",ll.get(2));
        assertEquals("A",ll.getLast());

    }

    @Test
    @DisplayName("Inserting after a given element")
    void testInsertAfter() {
        LinkedList<String> ll = new CircularDoublyLinkedList<>();
        ll.insertFist("C");
        ll.insertFist("B");
        ll.insertFist("A");

        ll.insertAfter("A","AA");
        assertEquals(4,ll.size());
        assertEquals("AA",ll.get(1));

        ll.insertAfter("B","BB");
        assertEquals(5,ll.size());
        assertEquals("BB",ll.get(3));

        ll.insertAfter("C","CC");
        assertEquals(6,ll.size());
        assertEquals("CC",ll.getLast());
        assertEquals("C",ll.get(ll.size() - 2));

    }




    @Test
    @DisplayName("Test deleting methods to list")
    void testDeletion() {
        LinkedList<String> ll = new CircularDoublyLinkedList<>();
        ll.insertFist("E");
        ll.insertFist("D");
        ll.insertFist("C");
        ll.insertFist("B");
        ll.insertFist("A");

        String deleted = ll.deleteFirst();
        assertEquals(4,ll.size());
        assertEquals("A",deleted);

        deleted = ll.deleteLast();
        assertEquals(3,ll.size());
        assertEquals("E",deleted);

        deleted = ll.remove("C");
        assertEquals(2,ll.size());
        assertEquals("C",deleted);

        deleted = ll.remove("B");
        assertEquals(1,ll.size());
        assertEquals("B",deleted);
        assertEquals("D",ll.getFirst());
        assertEquals("D",ll.getLast());
    }

}
