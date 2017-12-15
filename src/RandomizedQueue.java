import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int numberItems = 0;
    private int arraySize = 1;
    private Item[] nodes = (Item[]) new Object[arraySize];

    public RandomizedQueue() {                 // construct an empty randomized queue

    }

    public boolean isEmpty() {                 // is the randomized queue empty?
        return numberItems == 0;
    }

    public int size() {                        // return the number of items on the randomized queue
        return numberItems;
    }

    public void enqueue(Item item) {           // add the item
        if (item == null) throw new IllegalArgumentException();
        nodes[numberItems++] = item;
        arrayShuffle();
        arrayResize();
    }

    public Item dequeue() {                    // remove and return a random item
        if (isEmpty()) throw new NoSuchElementException();
        Item temp = nodes[--numberItems];
        nodes[numberItems] = null;
        arrayShuffle();
        arrayResize();
        return temp;
    }

    public Item sample() {                     // return a random item (but do not remove it)
        if (isEmpty()) throw new NoSuchElementException();
        return iterator().next();
    }

    public Iterator<Item> iterator() {         // return an independent iterator over items in random order
        return new RandomizedIterator();
    }

    private void arrayShuffle() {
        Item[] temp = (Item[]) new Object[numberItems];
        for (int i = 0; i < numberItems;  i++) {
            temp[i] = nodes[i];
        }
        edu.princeton.cs.algs4.StdRandom.shuffle(temp);
        for (int i = 0; i < numberItems;  i++) {
            nodes[i] = temp[i];
        }
        return;
    }

    private void arrayResize() {
        boolean resize = false;
        if (numberItems >= arraySize / 2) {
            arraySize *= 2;
            resize = true;
        }
        if (numberItems <= arraySize / 4) {
            arraySize /= 2;
            resize = true;
        }

        if (resize) {

            Item[] temp = nodes;
            nodes = (Item[]) new Object[arraySize];
            for (int i = 0; i < numberItems; i++)
                nodes[i] = temp[i];
        }
    }

    private int[] shuffledKeys() {
        int[] keys = new int[numberItems];
        for (int i = 0; i < numberItems; i++) keys[i] = i;
        StdRandom.shuffle(keys);
        return keys;
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int current = 0;
        private final int[] shuffledKeys = shuffledKeys();

        public boolean hasNext() {
            return current < numberItems;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index = shuffledKeys[current++];
            return nodes[index];
        }
    }

    public static void main(String[] args) {   // unit testing (optional)
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        System.out.println((rq.size() == 0) ? "test passes" : "should be 0 items");
        rq.enqueue(2);
        System.out.println((rq.size() == 1) ? "test passes" : "should be 1 item");
        int thing = rq.dequeue();
        System.out.println((thing == 2) ? "test passes" : "should be 2");
        rq.dequeue();

    }
}