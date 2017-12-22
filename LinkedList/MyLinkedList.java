package LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author weijian1 Weijian Li.
 * LinkedList (Singly) Method Implementation with generic and iterator.
 * public method addFirst(E e), addLast(E e), insertAfter(..) insertBefore(..) remove(..) iterator()
 * @param <AnyType> data type to insert into list
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    /**
     * head of this linkedlist.
     */
    private ListNode<AnyType> head;
    /**
     * constructor to constructor a new linkedlist
     */
    public MyLinkedList() {
        head = null;
    }

    /**
     * add a node to head.
     * @param item content of this node
     */
    public void addFirst(AnyType item) {
        head = new ListNode<>(item, head);
    }

    /**
     * add a node to last.
     * @param item content of this node
     */
    public void addLast(AnyType item) {
        ListNode<AnyType> cur = head;
        if (cur == null) {
            addFirst(item);
//            head = new ListNode<>(item, null);
            return;
        }
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode<>(item, null);
    }

    /**
     * insert a new node after target node.
     * @param key content of target node
     * @param item content of new node
     */
    public void insertAfter(AnyType key, AnyType item) {
        ListNode<AnyType> curr = head;
        while (curr != null && !curr.value.equals(key)) {
            curr = curr.next;
        }
        // for here, curr == null || curr.value.equals(key)
        if (curr != null) {
            ListNode<AnyType> next = curr.next;
            curr.next = new ListNode<>(item, next);
        }
    }

    /**
     * insert a new node before target node.
     * @param key content of target node
     * @param item content of new node
     */
    public void insertBefore(AnyType key, AnyType item) {
        ListNode<AnyType> prev = null;
        ListNode<AnyType> curr = head;
        while (curr != null && !curr.value.equals(key)) {
            prev = curr;
            curr = curr.next;
        }
        // for here, curr == null || curr.value.equals(key)
        if (curr != null) {
            ListNode<AnyType> newNode = new ListNode<>(item, curr);
            if (prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
        }
    }

    /**
     * remove target node.
     * @param key content of target node
     */
    public void remove(AnyType key) {
        ListNode<AnyType> dummy = new ListNode<>(key, head);
        ListNode<AnyType> curr = head;
        ListNode<AnyType> prev = dummy;
        while (curr != null && !curr.value.equals(key)) {
            prev = curr;
            curr = curr.next;
        }
        // for here, curr == null || curr.value.equals(key)
        if (curr != null) {
            prev.next = curr.next;
            curr.next = null;
        }
        head = dummy.next;
    }

    /**
     * Iterator implementation that returns iterator object.
     * @return an Iterator object
     */
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    /**
     * private nested class implements Iterator interface.
     */
    private class LinkedListIterator implements Iterator<AnyType> {
        /**
         * head of this iterator.
         */
        ListNode<AnyType> nextNode;

        /**
         * constructor to set nextNode to its head
         */
        private LinkedListIterator() {
            nextNode = head;
        }
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }
        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ListNode<AnyType> result = nextNode;
            nextNode = nextNode.next;
            return result.value;
        }
    }

    /**
     * private static class defines node of linked list.
     * @param <AnyType>
     */
    private static class ListNode<AnyType> {
        /**
         * value that this node holds.
         */
        private AnyType value;
        /**
         * next node.
         */
        private ListNode<AnyType> next;
        /**
         * constructor with value and its next node as its parameters.
         */
        private ListNode(AnyType value, ListNode<AnyType> next) {
            this.value = value;
            this.next = next;
        }
    }
}
