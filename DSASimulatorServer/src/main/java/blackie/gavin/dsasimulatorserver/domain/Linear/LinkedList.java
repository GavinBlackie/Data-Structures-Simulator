package blackie.gavin.dsasimulatorserver.domain.Linear;

/* Represents an implementation of a singly linked list
 */
public class LinkedList<T> implements LinearStructure{

    static void main() {
        LinkedList<Character> linkedList = new LinkedList<>();
        System.out.println("IsEmpty?: " + linkedList.isEmpty());
        linkedList.insertFirst('B'); // B
        linkedList.insertFirst('A'); // A -> B
        linkedList.insertLast('C'); // A -> B -> C
        linkedList.insert(1, 'D'); // A -> D -> B -> C
        linkedList.insert(2, 'E'); // A -> D -> E -> B -> C

        linkedList.displayDetails();

        linkedList.insertLast('Z');
        linkedList.insertLast('Y');
        linkedList.removeLast();
        linkedList.removeFirst(); // D -> E -> B -> C -> Z
        linkedList.insert(2, 'F');
        linkedList.remove(1); // D -> F -> B -> C -> Z

        linkedList.displayDetails();
        linkedList.clear();
        linkedList.displayDetails();
        linkedList.insert(2, 'A'); // won't insert anything (index 2 not accessible/DNE)
        linkedList.insert(-4, 'B'); // also won't work (trying to insert to negative index)
        linkedList.insert(0, 'A');
        linkedList.displayDetails();
        System.out.println("\n**********************\n");

        LinkedList<String> fruits = new LinkedList<>();
        fruits.insertFirst("Apple");
        fruits.insert(1, "Banana");
        fruits.insertLast("Orange");
        fruits.displayDetails();

        Node<String> orange = fruits.search("Orange");
        System.out.println("Searched and Found: " + orange.data);
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
    }

    private Node<T> head = null;
    private int size = 0;

    public void insert(Node<T> position, T value) {
        // Cannot insert by position node if there is no list at all
        if (head == null)
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
                return;
            }
            prev = current;
            current = current.next;
        }
        size++;
    }
    public void insert(int index, T value) {
        if (index > size || index < 0) // stop trying if the index is out of the size range
            return;
        if (index == 0) {
            head = new Node<> (value, null);
            size++;
            return;
        }

        Node<T>  current = head;
        Node<T>  prev = null;

        // Traverse exactly "index" times
        int jCount = 0;
        while ( (current.next != null) && (jCount < index)) {
            prev = current;
            current = current.next;
            jCount++;
        }
        // Insert the element between prev and curr
        if (prev != null) {
            prev.next = new Node<>(value, current);
        } else { // else it is at the front, prev is null, so just add it to the existing head node
            head.next = new Node<>(value, null);
        }
        size++;
    }
    public void insertFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }
    public void insertLast(T value) {
        // Traverse until the next node is null (reached the last node)
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        // Add a new node
        current.next = new Node<>(value, null);
        size++;
    }
    public void remove(Node<T> position) {
        // Traverse until we reach that position
        Node<T> current = head;
        while (current.next.next != null) {
            if (current.next.equals(position) ) {
                // Link current with the one after that position, let Java delete current.next with garbage collection
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }
    public void remove(int index) {
        if (index >= size || index < 0) // stop trying if the index is out of the size range
            return;
        // Traverse exactly "index" - 1 times
        Node<T> current = head;
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
        size--;
    }
    public void removeFirst() {
        head = head.next;
        size--;
    }
    public void removeLast() {
        // Traverse until second last node, then delete
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
        size--;
    }
    public Node<T> search(T target) {
        // Search will take O(n) time
        // Traverse and try to find the node containing the target
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(target) ) {
                return current;
            }
            current = current.next;
        }
        // Return an empty node if nothing found (can be changed to a thrown exception later)
        return new Node<>(null, null);
    }
    public Node<T> find(int index) {
        Node<T> current = head;
        for (int jNode = 0; jNode < index; jNode++) {
            // If node does not exist, return an empty node
            if (current == null) {
                return new Node<>(null, null);
            }
            else {
                current = current.next;
            }
        }
        return current;
    }
    public void clear() {
        // Removes head, let Java's garbage collection handle the rest
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public void displayDetails() {
        StringBuilder printMsg = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            printMsg.append(current.data.toString().concat(" -> "));
            current = current.next;
        }
        if (current != head) {
            printMsg = new StringBuilder(printMsg.substring(0, printMsg.length() - 3));
        } else {
            printMsg = new StringBuilder("head -> null");
        }
        System.out.println(printMsg);
    }
}
