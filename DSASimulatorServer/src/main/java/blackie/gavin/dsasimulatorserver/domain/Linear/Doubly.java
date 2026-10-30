package blackie.gavin.dsasimulatorserver.domain.Linear;

public class Doubly<T> extends LinkedList<T> {

    private Node<T> tail;

    static void main() {
        LinkedList<Character> linkedList = new Doubly<>();

    }

    @Override
    public void insert(Node<T> position, T value) {

    }

    @Override
    public void insert(int index, T value) {

    }

    @Override
    public void insertLast(T value) {

    }

    @Override
    public void remove(Node<T> position) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void removeLast() {

    }

    @Override
    public Node<T> search(T target) {
        // TODO: Implement multithreading to search from both sides!!

        return null;
    }

    @Override
    public Node<T> find(int index) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void displayDetails() {

    }

    @Override
    public void clear() {
        super.clear();
        tail = null;
    }
}
