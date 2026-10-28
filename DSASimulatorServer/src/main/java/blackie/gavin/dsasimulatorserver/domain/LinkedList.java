package blackie.gavin.dsasimulatorserver.domain;

/* Represents an implementation of a singly linked list
 */
public class LinkedList<T> extends LinearStructure{

    static void main() {
        LinkedList<Character> linkedList = new LinkedList<>();
        linkedList.insertFirst('B'); // B
        linkedList.insertFirst('A'); // A -> B
        linkedList.insertLast('C'); // A -> B -> C
    }
    

    public class Node {
        public T data;
        public Node next;
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;
    private int size = 0;

    public void insert(Node position, T value) {
        Node current = head;
        Node prev = null;
        while (current.next != null) {
            if (current.equals(position)) {

                // case that the position is the first node
                if (prev == null) {
                    head = new Node(value, current);
                } else {
                    prev.next = new Node(value, current);
                }
            }
            prev = current;
            current = current.next;
        }
    }
    public void insert(int index, T value) {
        if (index >= size || index < 0) // stop trying if the index is out of the size range
            return;

        Node current = head;
        Node prev = null;

        // Traverse exactly "index" - 1 times
        int jCount = 0;
        while (current.next != null && jCount < index - 1) {
            prev = current;
            current = current.next;
            jCount++;
        }
        // Insert the element between prev and curr
        if (prev != null) {
            prev.next = new Node(value, current);
        } else { // else its at the front, index = 0
            Node node = new Node(value, current);
        }
    }
    public void insertFirst(T value) {
        head = new Node(value, head);
        size++;
    }
    public void insertLast(T value) {
        // Traverse until the next node is null (reached the last node)
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        // Add a new node
        current.next = new Node(value, null);
        size++;
    }
    public void delete(Node position) {
        // Traverse until we reach that position
        Node current = head;
        while (current.next.next != null) {
            if (current.next.equals(position) ) {
                // Link current with the one after that position, let Java delete current.next with garbage collection
                current.next = current.next.next;
            }
            current = current.next;
        }
    }
    public void delete(int index) {
        if (index >= size || index < 0) // stop trying if the index is out of the size range
            return;
        // Traverse exactly "index" - 1 times
        Node current = head;
        for (int jCount = 0; jCount < index - 1; jCount++) {
            if (current.next == null)
                return;
            current = current.next;
        }
        // If there is something after, ensure the list remains connected
        if (current.next.next != null) {
            current.next = current.next.next;
        }
        // Else just remove
        else {
            current.next = null;
        }
    }
    public void deleteFirst() {
        head = head.next;
    }
    public void deleteLast() {
        // Traverse until second last node, then delete
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
}
