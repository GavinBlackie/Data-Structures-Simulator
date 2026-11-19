package blackie.gavin.dsasimulatorserver.domain.Linear;

import java.util.NoSuchElementException;

public abstract class LinkedList<T> implements LinearStructure{

    protected Node<T> head = null;
    protected int size = 0;

    public abstract void insert(Node<T> position, T value);
    public abstract void insert(int index, T value);
    public void insertFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }
    public abstract void insertLast(T value);
    public abstract void remove(Node<T> position);
    public void remove(int index) {
        // Remove the element at the found index (utilise the find method)
        this.remove(this.find(index));
    }
    public void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }
        head = head.next;
        size--;
    }
    public abstract void removeLast();
    public abstract Node<T> search(T target);
    public Node<T> search(Node<T> targetNode) {
        return targetNode == null ? null : search(targetNode.data);
    }
    public abstract Node<T> find(int index);
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
    // Clear method might need more specific resetting if required (eg. need to also reset tail of a doubly linked list)
    @Override
    public void clear() {
        // Removes head, let Java's garbage collection handle the rest
        head = null;
        size = 0;
    }

    // Two "helper" getters for customizing display text across LinkedList types
    protected abstract String getSeparator();
    protected abstract String getEmptyDisplayMessage();

    @Override
    public void displayDetails() {
        if (head == null) {
            System.out.println(getEmptyDisplayMessage());
            return;
        }

        StringBuilder printMsg = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            printMsg.append(current.data);
            if (current.next != null) {
                printMsg.append(getSeparator());
            }
            current = current.next;
        }
        System.out.println(printMsg);
    }
}
