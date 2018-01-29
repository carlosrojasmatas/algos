package ds;

import ds.impl.CircularQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    @DisplayName("Push and Pop")
    void testPush() {
        CircularQueue<Integer> q = new CircularQueue<>(5);
        q.add(1);
        assertEquals(q.size() , 1);
        Integer v = q.pop();
        assertEquals(v , new Integer(1));
        assertEquals(q.size() , 0);
        q.add(2);
        q.add(3);
        v = q.pop();
        assertEquals(v , new Integer(2));
        v = q.pop();
        assertEquals(v, new Integer(3));
        assertTrue(q.size() == 0);
    }

    @Test
    @DisplayName("Circular test")
    void testCircular() {
        CircularQueue<Integer> q = new CircularQueue<>(5);
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        assertTrue(q.size() == 5);
        assertEquals(q.toString(),"[1,2,3,4,5]");
        q.pop();
        q.add(6);
        q.pop();
        q.add(7);
        q.pop();
        q.add(8);

        assertTrue(q.toString().equals("[6,7,8,4,5]"));

        assertTrue(q.pop() == 4);
        assertTrue(q.pop() == 5);
        assertTrue(q.pop() == 6);
        assertTrue(q.pop() == 7);
        assertTrue(q.pop() == 8);

        q.clear();

        assertTrue(q.isEmpty());
    }

    @Test
    @DisplayName("Must throw an exception while popping elements in an emtpy queue")
    void emptyTest(){
        CircularQueue<Integer> q = new CircularQueue<>(5);
        assertThrows(IllegalStateException.class,q::pop);
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.pop();
        q.pop();
        q.pop();
        q.pop();
        assertThrows(IllegalStateException.class,q::pop);

        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.clear();
        assertThrows(IllegalStateException.class,q::pop);
    }

    @Test
    @DisplayName("Must throw an exception while adding elements in a full queue")
    void fullTest(){
        CircularQueue<Integer> q = new CircularQueue<>(5);
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        assertThrows(IllegalStateException.class,()->q.add(6));

        q.pop();
        q.pop();
        q.add(7);
        q.add(8);
        assertThrows(IllegalStateException.class,()->q.add(9));

    }


}
