package org.hua.dequeapp;

import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeque2 {
    
    @Test
    public void testIterators() {
        DeQueue<Integer> deque = new CircularArrayQueue<>();
        
        assertTrue(deque.isEmpty());
        int count = 100000;
        
        // Push στοιχεία με pushFirst
        for (int i = 0; i < count; i++) {
            deque.pushFirst(i);
        }
        
        //Assert για Iterator από το first μέχρι last
        Iterator<Integer> it = deque.iterator();
        int current = count-1;
        while (it.hasNext()) {
            //Το first και το last παραμένουν σταθερά γιατί ο iterator δεν αλλάζει την deque
            assertTrue(deque.first() == count-1);
            assertTrue(deque.last() == 0);
            assertTrue(it.next() == current--);
        }
        assertTrue(!deque.isEmpty());
        
        //Assert για Iterator από το last μέχρι first
        it = deque.descendingIterator();
        current = 0;
        while (it.hasNext()) {
            assertTrue(deque.first() == count-1);
            assertTrue(deque.last() == 0);
            assertTrue(it.next() == current++);
        }
        assertTrue(!deque.isEmpty());
        
        deque.clear();
        assertTrue(deque.isEmpty());
        
        
        
        // Push στοιχεία με pushLast
        for (int i = 0; i < count; i++) {
            deque.pushLast(i);
        }
        
        //Assert για Iterator από το first μέχρι last
        it = deque.iterator();
        current = 0;
        while (it.hasNext()) {
            //Το first και το last παραμένουν σταθερά γιατί ο iterator δεν αλλάζει την deque
            assertTrue(deque.first() == 0);
            assertTrue(deque.last() == count-1);
            assertTrue(it.next() == current++);
        }
        assertTrue(!deque.isEmpty());
        
        //Assert για Iterator από το last μέχρι first
        it = deque.descendingIterator();
        current = count-1;
        while (it.hasNext()) {
            assertTrue(deque.first() == 0);
            assertTrue(deque.last() == count-1);
            assertTrue(it.next() == current--);
        }
        assertTrue(!deque.isEmpty());
        
        deque.clear();
        assertTrue(deque.isEmpty());
        
    }
    
}
