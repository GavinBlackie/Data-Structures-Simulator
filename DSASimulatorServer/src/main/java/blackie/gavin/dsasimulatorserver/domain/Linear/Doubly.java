package blackie.gavin.dsasimulatorserver.domain.Linear;

public class Doubly<T> extends LinkedList<T> {

    private Node<T> tail = head;

    static void main() {
        LinkedList<Character> linkedList = new Doubly<>();
        linkedList.insertFirst('C');
        linkedList.insertFirst('B');
        linkedList.insertFirst('A');
        linkedList.insertLast('D');  // head -> A <-> B <-> C <-> D <- tail
        linkedList.displayDetails();

        LinkedList<String> fruits = new Doubly<>();
        fruits.insertLast("Apple");
        fruits.insertFirst("Pear");
        fruits.insertLast("Banana"); // head -> Pear <-> Apple <-> Banana <- tail
        fruits.displayDetails();
    }

    @Override
    public void insertFirst(T value) {
        super.insertFirst(value);
        if (head.next != null) {
            head.next.prev = head;
        }
        if (tail == null) { // initialize tail if we are inserting the first item in the whole list
            tail = head.next;
        }
    }

    @Override
    public void insert(Node<T> position, T value) {

    }

    @Override
    public void insert(int index, T value) {

    }

    @Override
    public void insertLast(T value) {
        // If tail already exists, add it and change it
        if (tail != null) {
            tail.next = new Node<T>(value, tail, null);
            tail = tail.next;
            size++;
        }
        // Else head and tail point to the same thing
        else {
            head = new Node<T>(value, null, null);
            tail = head;
            size++;
        }
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

        Thread headThread = new Thread( () -> {
            System.out.println("Head Thread");
            Node<T> current = head;
            System.out.println(current);
        });
        Thread tailThread = new Thread( () -> {
            System.out.println("Tail Thread");
            Node<T> current = tail;
            System.out.println(current);
        });

        headThread.start();
        tailThread.start();

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
        // TODO: generalize this code for both linkedlist types
        StringBuilder printMsg = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            printMsg.append(current.data.toString().concat(" <-> "));
            current = current.next;
        }
        if (current != head) {
            printMsg = new StringBuilder(printMsg.substring(0, printMsg.length() - 4));
        } else {
            printMsg = new StringBuilder("head -> null <- tail");
        }
        System.out.println(printMsg);
    }

    @Override
    public void clear() {
        super.clear();
        tail = null;
    }
}
