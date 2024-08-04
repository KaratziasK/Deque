package org.hua.dequeapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeque1 {
    
    @Test
    public void testPushesAndPops() {
        DeQueue<Integer> deque = new CircularArrayQueue<>();
        
        assertTrue(deque.isEmpty());
        int count = 100000;
        
        // Push στοιχεία με pushFirst
        deque = myPushFirst(deque,count);
        // Pop στοιχεία με popFirst
        int current = count-1;
        int curSize = deque.size();
        while(!deque.isEmpty()){
            assertTrue(deque.first() == current);
            assertTrue(deque.popFirst() == current);
            assertTrue(deque.size() == --curSize);
            current--;
        }
        assertTrue(deque.isEmpty());
        
        
        // Push στοιχεία με pushFirst
        deque = myPushFirst(deque,count);
        // Pop στοιχεία με popLast
        current = 0;
        curSize = deque.size();
        while(!deque.isEmpty()){
            assertTrue(deque.last() == current);
            assertTrue(deque.popLast() == current);
            assertTrue(deque.size() == --curSize);
            current++;
        }
        assertTrue(deque.isEmpty());
          
        
        // Push στοιχεία με pushLast
        deque = myPushLast(deque,count);
        // Pop στοιχεία με popFirst
        current = 0;
        curSize = deque.size();
        while(!deque.isEmpty()){
            assertTrue(deque.first() == current);
            assertTrue(deque.popFirst() == current);
            assertTrue(deque.size() == --curSize);
            current++;
        }
        assertTrue(deque.isEmpty());
        
        
        // Push στοιχεία με pushLast
        deque = myPushLast(deque,count);
        // Pop στοιχεία με popLast
        current = count-1;
        curSize = deque.size();
        while(!deque.isEmpty()){
            assertTrue(deque.last() == current);
            assertTrue(deque.popLast() == current);
            assertTrue(deque.size() == --curSize);
            current--;
        }
        assertTrue(deque.isEmpty());
    }
    
    // Push στοιχεία με pushFirst
    private DeQueue<Integer> myPushFirst(DeQueue<Integer> deque,int count){
        for (int i = 0; i < count; i++) {
            deque.pushFirst(i);
            assertTrue(deque.size() == i + 1);
            assertTrue(deque.first() == i);
            assertTrue(deque.last() == 0);
        }
        return deque;
    }
    // Push στοιχεία με pushLast
    private DeQueue<Integer> myPushLast(DeQueue<Integer> deque,int count){
        for (int i = 0; i < count; i++) {
            deque.pushLast(i);
            assertTrue(deque.size() == i + 1);
            assertTrue(deque.first() == 0);
            assertTrue(deque.last() == i);
        }
        return deque;
    }
    
}
