/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {

    }

    private Node<Item> getHead() { return head; }
    
    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = new Node<>(item);
            tail = head;
        } else {
            Node<Item> node = new Node<>(item);
            head.setLast(node);
            node.setNext(head);
            head = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (tail == null) {
            tail = new Node<>(item);
            head = tail;
        } else {
            Node<Item> node = new Node<>(item);
            tail.setNext(node);
            node.setLast(tail);
            tail = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("");
        }
        Item val = head.getValue();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setLast(null);
        }
        size--;
        return val;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("");
        }
        Item val = tail.getValue();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getLast();
            tail.setNext(null);
        }
        size--;
        return val;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(this.getHead());
    }
    
    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> curNode;

        public DequeIterator(Node<Item> head) {
            curNode = head;
        }

        public boolean hasNext() {
            return curNode != null;
        }

        public Item next() {
            if (curNode == null) {
                throw new java.util.NoSuchElementException();
            }
            Item val = curNode.getValue();
            curNode = curNode.getNext();
            return val;
        }
    }

    private class Node<Item> {
        private Item value;
        private Node<Item> last;
        private Node<Item> next;

        public Node(Item v) {
            setValue(v);
            setLast(null);
            setNext(null);
        }

        public Item getValue() {
            return value;
        }

        public void setValue(Item value) {
            this.value = value;
        }

        public Node<Item> getLast() {
            return last;
        }

        public void setLast(Node<Item> last) {
            this.last = last;
        }

        public Node<Item> getNext() {
            return next;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }
    }
    
    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        deque.addFirst("tom");
        deque.addLast("bob");

        Iterator<String> it = deque.iterator();
        while (it.hasNext()) {
            StdOut.print(it.next());
            StdOut.print("");
        }
        StdOut.print("\n");

        deque.addFirst("Mary");
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
    }
}


