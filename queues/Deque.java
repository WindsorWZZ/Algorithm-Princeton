/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head = null;
    private Node<Item> tail = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {

    }

    public Node<Item> getHead() { return head; }

    public Node<Item> getTail() { return tail; }

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
        if (head == null) {
            head = new Node<>(item);
            tail = head;
        } else {
            Node<Item> node = new Node<>(item);
            head.last = node;
            node.next = head;
            head = node;
        }
        size++;

        printQueue();
    }

    // add the item to the back
    public void addLast(Item item) {
        if (tail == null) {
            tail = new Node<>(item);
            head = tail;
        } else {
            Node<Item> node = new Node<>(item);
            tail.next = node;
            node.last = tail;
            tail = node;
        }
        size++;

        printQueue();
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("");
        }
        Item val = head.value;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.last = null;
        }
        size--;

        printQueue();
        return val;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("");
        }
        Item val = head.value;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.last;
            tail.next = null;
        }
        size--;

        printQueue();
        return val;
    }

    public void printQueue() {
        Node<Item> p = head;
        while (p != null) {
            StdOut.print(p.value);
            StdOut.print(" ");
            p = p.next;
        }
        StdOut.printf("%d\n", size);
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator(this);
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

class DequeIterator<Item> implements Iterator<Item> {
    private Node<Item> curNode;

    public DequeIterator(Deque<Item> deque) {
        curNode = deque.getHead();
    }

    public boolean hasNext() {
        return curNode != null;
    }

    public Item next() {
        Item val = curNode.value;
        curNode = curNode.next;
        return val;
    }
}

class Node<Item> {
    public Item value;
    public Node<Item> last;
    public Node<Item> next;

    public Node(Item v) {
        value = v;
        last = null;
        next = null;
    }

}