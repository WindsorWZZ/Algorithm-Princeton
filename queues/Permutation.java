import edu.princeton.cs.algs4.StdArrayIO;
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
        while (StdIn.hasNextLine()) {
            String str = "";
            try {
                str = StdIn.readString();
            } catch (Exception e) {

            }
            if (str.equals("")) {
                break;
            }
            if (randQueue.size() == size) {
                randQueue.dequeue();
            }
            randQueue.enqueue(str);
        }
        Iterator<String> itor = randQueue.iterator();
        while (itor.hasNext()) {
            StdOut.println(itor.next());
        }
    }
}
