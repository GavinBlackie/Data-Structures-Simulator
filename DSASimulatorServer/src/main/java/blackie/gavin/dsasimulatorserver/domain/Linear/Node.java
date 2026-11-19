package blackie.gavin.dsasimulatorserver.domain.Linear;

public class Node<T> {
    public T data;
    public Node<T> prev;
    public Node<T> next;

    public Node(T data) {
        this(data, null, null);
    }
    public Node(T data, Node<T> next) {
        this(data, null, next);
    }
    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}