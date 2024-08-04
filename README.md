# CircularArrayQueue Project
**NOTE:** This project was part of a university course on Operating Systems at Harokopio University of Athens. This project was a collaborative effort with my fellow students:
- Christos Kalamatianos
- Xenofontas Marketakis

## Overview
The CircularArrayQueue project is a Java-based implementation of a double-ended queue (deque) using a circular array. This project provides a robust and efficient data structure that supports constant-time insertions and deletions from both ends of the deque. The implementation includes features such as automatic resizing of the underlying array and support for iterators to traverse the deque.

## Features
Double-Ended Queue (Deque): Supports operations to add and remove elements from both the front and back of the queue.
Circular Array Implementation: Uses a circular array to store elements, optimizing space and allowing efficient use of the array.
Dynamic Resizing: Automatically adjusts the capacity of the array to handle more elements or reduce memory usage when elements are removed.
Iterators: Provides iterators to traverse the deque from front to back and back to front.
Exception Handling: Throws appropriate exceptions for invalid operations such as accessing elements from an empty deque or modifying the deque during iteration.
## Project Structure
The project consists of the following main components:

DeQueue Interface: Defines the operations supported by a double-ended queue.
CircularArrayQueue Class: Implements the DeQueue interface using a circular array.
Unit Tests: A set of JUnit tests that verify the functionality and correctness of the CircularArrayQueue implementation.
