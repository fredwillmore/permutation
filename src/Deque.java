import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int numberItems;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        public Node(Item item) {
            this.item = item;
            this.previous = null;
            this.next = null;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }

    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        numberItems = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return first == null;
    }

    public int size() {                        // return the number of items on the deque
        return numberItems;
    }

    public void addFirst(Item item) {          // add the item to the front
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node(item);
        node.setNext(first);
        if (first != null) {
            first.previous = node;
        } else {
            last = node;
        }
        first = node;
        numberItems++;
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node(item);
        node.setPrevious(last);
        if (last != null) {
            last.next = node;
        } else {
            first = node;
        }
        last = node;
        numberItems++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException();
        Node node = first;
        Node next = node.getNext();
        if (next != null) {
            next.setPrevious(null);
        }
        first = next;
        if (first == null) last = null;
        numberItems--;
        return node.item;
    }

    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException();
        Node node = last;
        Node previous = node.getPrevious();
        if (previous != null) {
            previous.setNext(null);
        }
        last = previous;
        if (last == null) first = null;
        numberItems--;
        return node.item;
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new DequeForwardIterator();
    }

    private class DequeForwardIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class DequeReverseIterator implements Iterator<Item> {
        private Node current = last;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.previous;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        System.out.println((deque.removeLast() == 0) ? "test passes" : "should be 0");
        System.out.println(deque.isEmpty() ? "test passes" : "should be true");



    }   // unit testing (optional)

}