import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] buffer;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        buffer = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == buffer.length) {
            expand();
        }
        buffer[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(0, size);
        Item res = buffer[index];
        buffer[index] = buffer[--size];
        if (size < buffer.length / 2) {
            shrink();
        }
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(0, size);
        return buffer[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator<Item>(buffer, size);
    }

    private void resize(int newSize) {
        Item[] newBuffer = (Item[]) new Object[newSize];
        for (int i = 0; i < size && i < newSize; ++i) {
            newBuffer[i] = buffer[i];
        }
        buffer = newBuffer;
    }

    private void expand() {
        resize(buffer.length * 2);
    }

    private void shrink() {
        resize(buffer.length / 2);
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.enqueue("f");

        for (int i = 0; i < 3; ++i) {
            StdOut.println(queue.dequeue());
        }

        Iterator<String> itor = queue.iterator();
        while (itor.hasNext()) {
            StdOut.println(itor.next());
        }

        for (int i = 0; i < queue.size(); ++i) {
            StdOut.println(queue.sample());
        }
    }
}

class RandomIterator<Item> implements Iterator<Item> {
    private Item[] buffer;
    private int size;

    RandomIterator(Item[] srcBuffer, int srcSize) {
        size = srcSize;
        buffer = (Item[]) new Object[srcBuffer.length];
        for (int i = 0; i < size; ++i) {
            buffer[i] = srcBuffer[i];
        }
    }

    public boolean hasNext() {
        return size > 0;
    }

    public Item next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(0, size);
        Item res = buffer[index];
        buffer[index] = buffer[--size];
        return res;
    }
}
