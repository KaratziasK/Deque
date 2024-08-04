package org.hua.dequeapp;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeApp {

    public static final int ELEMENTS = 10;
    
    public static void main(String[] args) {
        try{  
          DeQueue<Integer> deque = new CircularArrayQueue();

          //Παράδειγμα με προσθήκη από την αρχή της deque
          System.out.println("=========================");
          System.out.println("Example 1 with push first");
          for (int i=0; i<ELEMENTS; i++){
              deque.pushFirst(i);
          }
          try {
              //Εκτελούνται οι iterators και τα pops
              myIterators(deque);
              myPops(deque,"Example 1");
              
          }catch(NoSuchElementException | ConcurrentModificationException e){
              System.out.println("\n" + e.getMessage());
          }catch(IllegalArgumentException e){
              System.out.println("\n" + e.getMessage() + " The pops didn't executed");
          }


          //Παράδειγμα με προσθήκη από το τέλος της deque
          System.out.println("\n\n========================");
          System.out.println("Example 2 with push last");
          for (int i=0; i<ELEMENTS; i++){
              deque.pushLast(i);
          }
          try {
              myIterators(deque);
              myPops(deque,"Example 2");
              
          }catch(NoSuchElementException | ConcurrentModificationException e){
              System.out.println("\n" + e.getMessage());
          }catch(IllegalArgumentException e){
              System.out.println("\n" + e.getMessage() + " The pops didn't executed");
          }


          //Παράδειγμα με προσθήκη από την αρχή και το τέλος της deque
          System.out.println("\n\n=======================================");
          System.out.println("Example 3 with push first and push last");
          for (int i=0; i<ELEMENTS; i++){
              if (i % 2 == 0){
                  deque.pushFirst(i);
              }else {
                  deque.pushLast(i);
              }
          }
          try {
              myIterators(deque);
              myPops(deque,"Example 3");
              
          }catch(NoSuchElementException | ConcurrentModificationException e){
              System.out.println("\n" + e.getMessage());
          }catch(IllegalArgumentException e){
              System.out.println("\n" + e.getMessage() + " The pops didn't executed");
          }
          System.out.println("\n");
        
        //Λάθος capacity στον constructor της deque  
        }catch(IllegalArgumentException e){
          System.out.println(e.getMessage());
        }  
    }
    
    //Δημιουργούνται οι δύο iterators και τυπώνουν τα αποτελέσματα
    private static void myIterators(DeQueue<Integer> deque){
        System.out.println("Elements from first: " + deque.first()
                           + " to last: " + deque.last());
        Iterator<Integer> it = deque.iterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        
        System.out.println("\n\nElements from last: " + deque.last()
                           + " to first: " + deque.first());
        it = deque.descendingIterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
    }
    
    //Εκτελούνται 3 περιπτώσεις για κάθε example δηλαδή popFirst, popLast και τα δύο μαζί
    private static void myPops(DeQueue<Integer> deque,String example){
        
        if ( (!example.equals("Example 1")) && (!example.equals("Example 2")) 
              && (!example.equals("Example 3")) ){
           throw new IllegalArgumentException("Argument isn't a valid example!");
        }
        
        System.out.print("\n\nPOP FIRST: ");
        for (int i=0; i<ELEMENTS; i++){
            System.out.print(deque.popFirst() + " ");
        }
        
        //Προσθήκη στοιχείων στην deque για να μπορεί να γίνει το pop
        deque = pushDequeForPops(deque,example);
        
        System.out.print("\n\nPOP LAST: ");
        for (int i=0; i<ELEMENTS; i++){
            System.out.print(deque.popLast() + " ");
        }
        
        deque = pushDequeForPops(deque,example);
        
        System.out.print("\n\nPOP FIRST AND POP LAST: ");
        for (int i=0; i<ELEMENTS; i++){
            if (i % 2 == 0){
                System.out.print(deque.popFirst() + " ");
            }else {
                System.out.print(deque.popLast() + " ");
            }
        }  
    }
    /*Στην deque προστήθονται στοιχεία με το αντίστοιχο τρόπο του κάθε example 
    για να τυπωθούν τα επιθυμητά αποτελέσματα*/
    private static DeQueue<Integer> pushDequeForPops(DeQueue<Integer> deque,String example){
        if (example.equals("Example 1")){
            for (int i=0; i<ELEMENTS; i++){
                deque.pushFirst(i);
            } 
        }else if (example.equals("Example 2")){
            for (int i=0; i<ELEMENTS; i++){
                 deque.pushLast(i);
            }
        }else if (example.equals("Example 3")){
            for (int i=0; i<ELEMENTS; i++){
                if (i % 2 == 0){
                    deque.pushFirst(i);
                }else {
                    deque.pushLast(i);
                }
            }
        }
        return deque;
    }    
}
