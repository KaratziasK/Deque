package org.hua.dequeapp;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDeque3 {
    
    @Test
    public void testExceptions(){
        int capacity = -1;
        
        //Λάθος capacity
        try {
            DeQueue<Integer> deque = new CircularArrayQueue<>(capacity);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().equals("Wrong Capacity!"));
        }    
        
        //Σωστό capacity
        DeQueue<Integer> deque = new CircularArrayQueue<>();
        
        assertTrue(deque.isEmpty());
        
        //Pops με άδεια deque
        try {
            deque.popFirst();
        }catch(NoSuchElementException e){
            assertTrue(e.getMessage().equals("Deque is empty"));
        }
        try {
            deque.popLast();
        }catch(NoSuchElementException e){
            assertTrue(e.getMessage().equals("Deque is empty"));
        }
        
        //First και Last με άδεια deque
        try {
            deque.first();
        }catch(NoSuchElementException e){
            assertTrue(e.getMessage().equals("Deque is empty"));
        }
        try {
            deque.last();
        }catch(NoSuchElementException e){
            assertTrue(e.getMessage().equals("Deque is empty"));
        }
        
        
        //Γεμίζουμε την deque για τους iterators
        for (int i=0; i<10; i++){
            deque.pushFirst(i);
        }
        //Αλλαγή στην deque κατά τη διάρκεια iteration
        try{
            Iterator<Integer> it = deque.iterator();
            it.hasNext();
            assertTrue(it.next() == 9);
            assertTrue(it.next() == 8);
            
            //Γίνεται αλλαγή στην deque 
            deque.pushFirst(10);
            //Trow exception
            it.next();
        }catch(ConcurrentModificationException e){
            assertTrue(e.getMessage().equals("Deque modified during iteration"));
        }
        
        try{
            Iterator<Integer> it = deque.descendingIterator();
            it.hasNext();
            assertTrue(it.next() == 0);
            assertTrue(it.next() == 1);
            
            //Γίνεται αλλαγή στην deque 
            deque.pushLast(10);
            //Throw exception
            it.next();
        }catch(ConcurrentModificationException e){
            assertTrue(e.getMessage().equals("Deque modified during iteration"));
        }
        
        deque.clear();
        assertTrue(deque.isEmpty());     
    }
}
