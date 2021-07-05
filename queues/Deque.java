/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node<Item> {
        public Item value;
        public Node<Item> last;
        public Node<Item> next;

        public Node(Item v) {
            value = v;
            last = null;
            next = null;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    // construct an empty deque
    public Deque() {

    }

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
        return val;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
