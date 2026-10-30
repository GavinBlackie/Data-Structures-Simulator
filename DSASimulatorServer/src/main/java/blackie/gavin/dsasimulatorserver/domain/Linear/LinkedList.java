package blackie.gavin.dsasimulatorserver.domain.Linear;

public abstract class LinkedList<T> implements LinearStructure{

    protected Node<T> head = null;
    protected int size = 0;

    public abstract void insert(Node<T> position, T value);
    public abstract void insert(int index, T value);
    public abstract void insertFirst(T value);
    public abstract void insertLast(T value);
    public abstract void remove(Node<T> position);
    public abstract void remove(int index);
    public abstract void removeFirst();
    public abstract void removeLast();
    public abstract Node<T> search(T target);
    public Node<T> search(Node<T> targetNode) {
        return search(targetNode.data);
    }
    public abstract Node<T> find(int index);
}
