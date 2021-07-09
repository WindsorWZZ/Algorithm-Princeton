import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int size = 0;
        if (args.length > 0) {
            size = Integer.parseInt(args[0]);
        }
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        while (StdIn.isEmpty()) {
            String str = StdIn.readString();
            randQueue.enqueue(str);
        }
        for (int i = 0; i < size; ++i) {
            StdOut.println();
        }
    }
}
