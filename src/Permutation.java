import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            randomizedQueue.enqueue(input);
        }

        Iterator<String> it = randomizedQueue.iterator();

        for (int i = 0; i < k; i++)
            if (it.hasNext()) System.out.println(it.next());
    }
}
