package app;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int counter = 0;

    private class Node {

        Node next;
        Item value;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.value = item;
        first.next = oldfirst;
        counter++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int iteratorCounter = counter;
        private Node current = first;

        @Override
        public boolean hasNext() {
            return counter > 0;
        }

        @Override
        public Item next() {
            counter--;
            Item value = current.value;
            current = current.next;
            return value;
        }
    }

}