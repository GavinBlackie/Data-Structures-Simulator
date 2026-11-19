package blackie.gavin.dsasimulatorserver.domain.Linear;

import java.util.NoSuchElementException;

/* Represents an implementation of a singly linked list
 */
public class Singly<T> extends LinkedList<T> {

    // A basic test case for singly linked lists, may be replaced with a designated testing class
    static void main() {
        Singly<Character> linkedList = new Singly<>();
        System.out.println("IsEmpty?: " + linkedList.isEmpty());
        linkedList.insertFirst('B'); // B
        linkedList.insertFirst('A'); // A -> B
        linkedList.insertLast('C');  // A -> B -> C
        linkedList.insert(1, 'D');   // A -> D -> B -> C
        linkedList.insert(2, 'E');   // A -> D -> E -> B -> C

        linkedList.displayDetails();

        linkedList.insertLast('Z');
        linkedList.insertLast('Y');
        linkedList.removeLast();
        linkedList.removeFirst(); // D -> E -> B -> C -> Z
        linkedList.insert(2, 'F');
        linkedList.remove(1);     // D -> F -> B -> C -> Z

        linkedList.displayDetails();
        linkedList.clear();
        linkedList.displayDetails();
        linkedList.insert(2, 'A');  // won't insert anything (index 2 not accessible/DNE)
        linkedList.insert(-4, 'B'); // also won't work (trying to insert to negative index)
        linkedList.insert(0, 'A');
        linkedList.displayDetails();
        System.out.println("\n**********************\n");

        Singly<String> fruits = new Singly<>();
        fruits.insertFirst("Apple");
        fruits.insert(1, "Banana");
        fruits.insertLast("Orange");
        fruits.displayDetails();

        Node<String> orange = fruits.search("Orange");
        System.out.println("Searched and Found: " + (orange != null ? orange.data : "null"));
        int searchIndex = 1;
        Node<String> banana = fruits.find(searchIndex);
        System.out.println("Found " + banana.data + " at index " + searchIndex);

        fruits.remove(banana);
        fruits.displayDetails();
        fruits.insert(orange, "Pineapple"); // inserting this way inserts before the position
        Node<String> pineapple = fruits.search("Pineapple");
        fruits.insert(pineapple, "Coconut");
        fruits.insert(1, "Banana"); // inserting this way also inserts like position based insert, but just at the index specified!!
        fruits.displayDetails();
        Node<String> pineapple2 = fruits.search(pineapple); // testing the abstract LinkedList method
        fruits.remove(pineapple2);
        fruits.displayDetails();
    }

    @Override
    public void insert(Node<T> position, T value) {
        // Cannot insert by position node if there is no list at all
        if (head == null || position == null)
            return;

        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {
            if (current.equals(position)) {

                // case that the position is the first node
                if (prev == null) {
                    head = new Node<>(value, current);
                } else {
                    prev.next = new Node<>(value, current);
                }
                size++;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    @Override
    public void insert(int index, T value) {
        if (index > size || index < 0) // stop trying if the index is out of the size range
            return;
        if (index == 0) {
            insertFirst(value);
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;

        // Traverse exactly "index" times
        int jCount = 0;
        while ((current != null) && (jCount < index)) {
            prev = current;
            current = current.next;
            jCount++;
        }
        // Insert the element between prev and curr
        if (prev != null) {
            prev.next = new Node<>(value, current);
        }
        size++;
    }

    @Override
    public void insertLast(T value) {
        if (head == null) {
            insertFirst(value);
            return;
        }

        // Traverse until the next node is null (reached the last node)
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        // Add a new node
        current.next = new Node<>(value, null);
        size++;
    }

    @Override
    public void remove(Node<T> position) {
        if (head == null || position == null)
            return;

        // Case that the position is the first node
        if (head.equals(position)) {
            removeFirst();
            return;
        }

        // Traverse until we reach that position
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.equals(position)) {
                // Link current with the one after that position, let Java delete current.next with garbage collection
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    @Override
    public void removeLast() {
        if (head == null) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }
        if (head.next == null) {
            head = null;
            size--;
            return;
        }

        // Traverse until second last node, then delete
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
        size--;
    }

    @Override
    public Node<T> search(T target) {
        if (head == null || target == null)
            return null;

        // Search will take O(n) time
        // Traverse and try to find the node containing the target
        Node<T> current = head;
        while (current != null) {
            if (target.equals(current.data)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public Node<T> find(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of range, Size: " + size);

        Node<T> current = head;
        for (int jNode = 0; jNode < index; jNode++) {
            current = current.next;
        }
        return current;
    }

    @Override
    protected String getSeparator() { return " -> "; }
    @Override
    protected String getEmptyDisplayMessage() {return "head -> null"; }
}
