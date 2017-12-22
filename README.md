# Introduction
This repository is my Java implementation of basic data structure and basic sorting algorithm. Data structure include ArrayList, LinkedList, Queue, Stack, Heap, Binary Search Tree, HashTable, Trie. Sorting Algorithm include bubble sort, selection sort, insertion sort, merge sort and quick sort.

## ArrayList
Implemented method:
* add(AnyType item)
* add(int index, AnyType item)
* get(int index)
* remove(AnyType item)
* set(int index, AnyType item)
* size()
* iterator()
* isEmpty()

## LinkedList
Implemented method:
* addFirst(AnyType item)
* addLast(AnyType item)
* insertAfter(AnyType key, AnyType item)
* insertBefore(AnyType key, AnyType item)
* remove(AnyType key)
* iterator()

## Queue(array based)
Implemented method:
* enqueue(AnyType item)
* dequeue()
* peekFront()
* isEmpty()

## Stack(array based)
Implemented method:
* push(AnyType item)
* pop()
* peek()
* isEmpty()

## Heap(min heap, a.k.a Priority Queue)
Implemented method:
* offer(int value)
* poll()
* update(int index, int value)
* size()
* isEmpty()

## Binary Search Tree
Implemented method:
* find(int key)
* insert(int key, double value)
* delete(int key)
* traverse()

## Trie
Implemented method:
* search(String word)
* insert(String word)
* startswith(String prefix)
* delete(String word)

## HashTable
### HashMap I(using seperate chaining as a way to solve hash collision)
Implemented method:
* size()
* isEmpty()
* clear()
* put(K key, V value)
* get(K key)
* containsKey(K key)
* containsValue(V value)
* remove(K key)

### HashMap II(using open addressing(linear probing) as a way to solve hash collision)
Implemented method:
* insert(String value)
* size()
* display()
* contains(String key)
* numOfCollisions()
* hashValue(String value)
* showFrequency(String key)
* remove(String key)

### HashSet
Implemented method:
* add(E e)
* remove(E e)
* contains(E e)
* clear()
* size()
* isEmpty()

## Sorting Algorithm
* bubble sort runs in O(n^2)
* selection sort runs in O(n^2)
* insertion sort runs in O(n^2)
* merge sort runs in O(nlogn)
* quick sort runs in O(nlogn)

## Notes:
In case you find any bugs, please feel free to contact me at weijian1@andrew.cmu.edu

