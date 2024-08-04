package org.hua.dequeapp;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CircularArrayQueue<E> implements DeQueue<E> {

    public final static int DEFAULT_CAPACITY = 8;
    private E[] array;
    private int front;
    private int rear;
    private int size;
    private transient int modCount=0;

    public CircularArrayQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    public CircularArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Wrong Capacity!");
        }
        this.size = 0;
        this.front = -1;
        this.rear = 0;
        this.array = (E[]) new Object[capacity];
    }

    
//=================================ΜΕΘΟΔΟΙ ΤΟΥ INTERFACE DEQUEUE==============================    
    
    /*Αν ο κυκλικός πίνακας είναι γεμάτος τότε διπλασιάζεται το μέγεθος του και ξανακαλείται η 
    pushFirst(elem) για να εκτελεστεί μία από τις δύο τελευταίες περιπτώσεις.
    Ως αποτέλεσμα, δεν επαναλαμβάνεται ίδιος κώδικας γιατί θα έπρεπε να βάλουμε if else μετά 
    το doubleCapacity().Με το return μετά την pushFirst(elem) δεν γίνεται προσθήκη του 
    ίδιου στοιχείου ξανά.*/
    
    @Override
    public void pushFirst(E elem) {
        if (isFull()) {
            doubleCapacity();
            pushFirst(elem);
            return;

        } else if (isEmpty()) {
            front = 0;
            rear = 1;
        } else if (front == 0) {
            //Το νέο πρώτο στοιχείο θα προστεθεί στο τέλος του πίνακα
            front = array.length - 1;
        } else {
            front--;
        }
        
        //Προσθήκη στοιχείου στην αρχή της deque
        array[front] = elem;
        size++;
        modCount++;
    }

    /*Αν ο πίνακας είναι γεματός τότε κάνουμε doubleCapacity() και η τιμή του rear είναι έγκυρη
    δηλαδή είναι μικρότερη του array.length οπότε το στοιχείο θα προστεθεί στην θέση rear*/
    @Override
    public void pushLast(E elem) {
        if (isFull()) {
            doubleCapacity();
            
        } else if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = rear % array.length;
        }
        
        //Προσθήκη στοιχείου στο τέλος της deque
        array[rear++] = elem;
        size++;
        modCount++;
    }
    
    //Διαγραφή του πρώτου στοιχείου της deque
    @Override
    public E popFirst() {
        E result;

        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
            
        } else if (size == 1) {
            result = array[front];
            //Reset των πεδίων αφού άδειασε η deque
            clear();
            
        } else {
            result = array[front];
            //Η τιμή του front γίνεται μικρότερη του array.length
            front = ++front % array.length; 
            
            //Έλεγχος για υποδιπλασίαση του πίνακα
            size--;
            if (size * 4 <= array.length) {
                halfCapacity();
            }
        }
        modCount++;
        return result;
    }
    
    //Διαγραφή του τελευταίου στοιχείου της deque
    @Override
    public E popLast() {
        E result;

        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
            
        } else if (size == 1) {
            result = array[rear - 1];
            //Reset των πεδίων αφού άδειασε η deque
            clear();

        } else {
            result = array[rear - 1];
            rear--;

            if (rear == 0) {
                //Το τελευταίο στοιχείο βρίσκεται στο array.length-1 
                rear = array.length;
            }
            
            //Έλεγχος για υποδιπλασίαση του πίνακα
            size--;
            if (size * 4 <= array.length) {
                halfCapacity();
            }
        }
        modCount++;
        return result;
    }
    
    //Επιστροφή του πρώτου στοιχείου της deque
    @Override
    public E first() {
        if (isEmpty()) { throw new NoSuchElementException("Deque is empty"); }
        return array[front];
    }
    
    //Επιστροφή του τελευταίου στοιχείου της deque
    @Override
    public E last() {
        if (isEmpty()) { throw new NoSuchElementException("Deque is empty"); }
        return array[rear - 1];
    }
    
    @Override
    public boolean isEmpty() { return size == 0; }
    
    @Override
    public int size() { return size; }

    @Override
    public void clear() {
        this.size = 0;
        this.front = -1;
        this.rear = 0;
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        modCount++;
    }
    
    //Επιστροφή των στοιχείων από το front μέχρι το rear−1 με βήμα+1
    @Override
    public Iterator<E> iterator() {
        return new IteratorFrontToRear();
    }
    
    //Επιστροφή των στοιχείων από το rear−1 μέχρι το front με βήμα−1.
    @Override
    public Iterator<E> descendingIterator() {
        return new IteratorRearToFront();
    }
//=================================ΜΕΘΟΔΟΙ ΤΟΥ INTERFACE DEQUEUE==============================
//=================================ΒΟΗΘΗΤΙΚΕΣ PRIVATE ΜΕΘΟΔΟΙ=================================    
    private class IteratorFrontToRear implements Iterator<E> {
        private int cur;//Δείχνει σε ποιό στοιχείο βρισκόμαστε
        private int counter;//Πλήθος στοιχείων που έχουν επιστραφεί
        private final int expectedModCount=modCount;

        public IteratorFrontToRear() {
            this.cur = front;//Ξεκινάμε από το πρώτο στοιχείο
            this.counter = 0;
        }
        
        /*Υπάρχει επόμενο στοιχείο όσο το πλήθος των στοιχείων που έχουν επιστραφεί είναι μικρότερο 
        από το συνολικό πλήθος των στοιχείων που βρίσκονται στην deque*/
        @Override
        public boolean hasNext() {
            checkForComodification();
            return counter < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException("");
            }
            E res = array[cur];
            //Η τιμή του cur γίνεται μικρότερη του array.length 
            cur = ++cur % array.length;
            
            counter++;
            return res;
        }
        
        final void checkForComodification() {
            if (modCount!=expectedModCount) {
                throw new ConcurrentModificationException("Deque modified during iteration");
            }
        }

    }
    
    private class IteratorRearToFront implements Iterator<E> {
        private int cur;
        private int counter;
        private final int expectedModCount=modCount;

        public IteratorRearToFront() {
            this.cur = rear - 1;//Ξεκινάμε από το τελευταίο στοιχείο
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return counter < size;
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException("");
            }
            E res = array[cur--];

            if(cur == -1){
                //Το επόμενο στοιχείο είναι στην τελευταία θέση του πίνακα
                cur = array.length-1;
            }
            counter++;
            return res;
        }
        
        final void checkForComodification() {
            if (modCount!=expectedModCount) {
                throw new ConcurrentModificationException("Deque modified during iteration");
            }
        }
    }
    
    //Δύο περιπτώσεις που ο κυκλικός πίνακας είναι γεμάτος  
    private boolean isFull() {
        //True αν ισχύει μία περίπτωση
        return ((front == 0 && rear == array.length) || (front == rear));
    }
  
    private void doubleCapacity() {
        //Δημιουργία νέου πίνακα
        int newCapacity = 2 * array.length;
        E[] newArray = (E[]) new Object[newCapacity];
        
        /*Δύο περιπτώσεις αφού ο γεμάτος πίνακας έχει δύο περιπτώσεις
        1η: Τα στοιχεία μεταφέρονται στις ίδιες θέσεις και τα front και rear δεν αλλάζουν.
        2η: Τα στοιχεία από το 0 μέχρι το rear-1 μεταφέρονται στις ίδιες θέσεις, ενώ τα 
        στοιχεία από το front μέχρι το τέλος του αρχικού πίνακα μεταφέρονται στις τελευταίες
        θέσεις του νέου πίνακα.Το rear παραμένει ίδιο ενώ το front αλλάζει.
        */
        if (front == 0 && rear == array.length) {
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
           
        } else if (front == rear) {
            for (int i = 0; i < rear; i++) {
                newArray[i] = array[i];
            }
            for (int i = 0; i < (array.length - front); i++) {
                newArray[newArray.length - i - 1] = array[array.length - i - 1];
            }
            front = newArray.length - (array.length - front);
        }
        array = newArray;
    }

    private void halfCapacity() {
        if (array.length <= DEFAULT_CAPACITY) {
            return;
        }
        //Δημιουργία νέου πίνακα
        int newCapacity = array.length / 2;
        E[] newArray = (E[]) new Object[newCapacity];
        
        /*Δύο περιπτώσεις
        1η: Τα στοιχεία είναι συνεχόμενα στον παλιό πίνακα οπότε μεταφέρονται στην αρχή του νέου πίνακα
        και αλλάζουν το front και το rear.
        2η: Κάποια στοιχεία βρίσκονται στην αρχή του παλιού πίνακα και τα υπόλοιπα στο τέλος του.
        Αυτά που βρίσκονται στην αρχή μεταφέρονται στις ίδιες θέσεις,ενώ τα υπόλοιπα μεταφέρονται 
        στις τελευταίες θέσεις του νέου πίνακα.Το rear παραμένει ίδιο ενώ το front αλλάζει.*/
        
        if (front < rear) {
            int k = 0;
            for (int i = front; i < rear; i++) {
                newArray[k++] = array[i];
            }
            front = 0;
            rear = size;
        } else {
            for (int i = 0; i < rear; i++) {
                newArray[i] = array[i];
            }
            for (int i = 0; i < array.length - front; i++) {
                newArray[newArray.length - i - 1] = array[array.length - i - 1];
            }
            front = newArray.length - (array.length - front);
        }
        array = newArray;
    }
//=================================ΒΟΗΘΗΤΙΚΕΣ PRIVATE ΜΕΘΟΔΟΙ=================================
    //Επιστροφή της χωρητικότητας που χρησιμοποιείται στο unit Test
    protected int capacity(){
        return array.length;
    }
}


