package blackie.gavin.dsasimulatorserver.domain.Linear;

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
    public abstract void remove(int index);
    public void removeFirst() {
        head = head.next;
        size--;
    }
    public abstract void removeLast();
    public abstract Node<T> search(T target);
    public Node<T> search(Node<T> targetNode) {
        return search(targetNode.data);
    }
    public abstract Node<T> find(int index);
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
    // Clear method might need more specific reseting if required (eg. need to also reset tail of a doubly linked list)
    @Override
    public void clear() {
        // Removes head, let Java's garbage collection handle the rest
        head = null;
        size = 0;
    }
}
