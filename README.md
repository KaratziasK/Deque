# CircularArrayQueue Project
**NOTE:** This project was part of a university course on Data Structures at Harokopio University of Athens.

## Overview
This project is a Java implementation of a double-ended queue (Deque) using the circular array technique. The class CircularArrayQueue offers flexible operations for adding, removing, and traversing elements in the queue.

## Authors
- **Kyriakos Karatzias**
- **Christos Kalamatianos**
- **Xenofontas Marketakis**

This project was developed as part of our coursework in the Data Structures class.

## Class Description
The CircularArrayQueue class includes the following fields:

- `E[] array`: An array storing the elements of the Deque. The use of Generics allows for any data type.
- `int front`: Index pointing to the first element.
- `int rear`: Index pointing to the next position after the last element.
- `int size`: The number of elements in the Deque.
- `int modCount`: The number of modifications made to the Deque.
## Constructors
The class provides two constructors:

1. `CircularArrayQueue()`: Creates an object with a default array size of 8 elements.
2. `CircularArrayQueue(int capacity)`: Allows the programmer to specify the array's initial size.
## Deque Operations
The implementation provides essential operations, including:

- Adding and removing elements from both ends of the Deque.
- Retrieving the first and last elements.
- Checking if the Deque is empty and returning its size.
- Traversing the elements using Iterators.
- Dynamic resizing of the array as needed.

## Usage Examples
The code example `DequeApp` demonstrates three usage scenarios that show the basic operations of adding, removing, and traversing elements with iterators. These examples include diagrams on the last pages, with arrows indicating the continuity of the array.

## Problem Solving and Exceptions
The implementation includes adequate checks for potential issues such as an empty Deque, attempts to remove or traverse elements from an empty Deque, and creating a CircularArrayQueue object with incorrect capacity. Additionally, it detects concurrent modifications of the Deque during iteration.

## JUnit Test Files
The project includes four JUnit test files to verify the correct functionality of the implementation.

- **`DequeTest1`** : Tests the correct operation of adding and deleting elements in the following cases: `pushFirst - popFirst`, `pushFirst - popLast`, `pushLast - popFirst`, `pushLast - popLast`. It also checks methods like `deque.last()`, `deque.first()`, `deque.size()`, `deque.isEmpty()`. The private functions `myPushFirst` and `myPushLast` add `count` elements to the deque in their own way, each checking `pushFirst` or `pushLast` respectively. Finally, using the deque returned by these functions, `popFirst` and `popLast` are tested.

- **`DequeTest2`** : Tests the correct operation of the two iterators. Elements are added to the deque, and checks are first performed for the iterator from `first` to `last` and then for the iterator from `last` to `first`. After completing the checks for each iterator, it is verified that the deque is not empty because iterators do not alter the contents of the deque.

- **`DequeTest3`** : Verifies that the deque implementation throws the correct exceptions in extreme cases. It checks if an incorrect capacity is given to the `CircularArrayQueue` constructor and if `pop`, `first`, or `last` is attempted on an empty deque. Finally, the test checks if a `ConcurrentModificationException` is thrown when the deque is modified during iteration.

- **`DequeTest4`** : Checks the correct operation of doubling and halving the capacity of the circular array.

## Conclusions
The implementation provides an efficient and secure structure for queue management, allowing for element addition and removal in both directions. The dynamic resizing mechanisms enhance the implementation's effectiveness. As part of our coursework, we developed a quality Deque structure, offering flexibility and security in its use.
