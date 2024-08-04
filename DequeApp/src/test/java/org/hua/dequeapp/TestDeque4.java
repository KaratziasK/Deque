package org.hua.dequeapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeque4 {
    
    @Test
    public void testCapacity(){
        CircularArrayQueue<Integer> deque = new CircularArrayQueue<>();
        assertTrue(deque.isEmpty());
        
        //Push στοιχεία με pushFirst
        for(int i=0; i<8; i++){
            deque.pushFirst(i);
        }
        
        //Ο κυκλικός πίνακας είναι γεμάτος
        assertTrue(deque.size() == 8);
        //Δεν έχει διπλασιαστεί η χωρητικότητα του
        assertTrue(deque.capacity() == 8);
        
        deque.pushFirst(8);
        
        //Διπλασιάστηκε η χωρητικότητα του
        assertTrue(deque.size() == 9);
        assertTrue(deque.capacity() == 16);
        
        
        // Pop στοιχεία με popFirst
        for(int i=0; i<4; i++){
            deque.popFirst();
        }
        
        //Δεν έχει υποδιπλασιαστεί η χωρητικότητα του
        assertTrue(deque.size() == 5);
        assertTrue(deque.capacity() == 16);
        
        deque.popFirst();
        
        //Υποδιπλασιάστηκε η χωρητικότητα του
        assertTrue(deque.size() == 4);
        assertTrue(deque.capacity() == 8);
        
        
        deque.clear();
        assertTrue(deque.isEmpty()); 
    }
    
}
