package blackie.gavin.dsasimulatorserver.domain.Linear;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Doubly<T> extends LinkedList<T> {

    public Node<T> tail = head;

    static void main() {
        LinkedList<Character> linkedList = new Doubly<>();
        linkedList.insertFirst('C');
        linkedList.insertFirst('B');
        linkedList.insertFirst('A');
        linkedList.insertLast('D');  // head -> A <-> B <-> C <-> D <- tail
        linkedList.displayDetails();

        Doubly<String> fruits = new Doubly<>();
        fruits.insertLast("Apple");
        fruits.insertFirst("Pear");
        fruits.insertLast("Banana"); // head -> Pear <-> Apple <-> Banana <- tail
        fruits.displayDetails();

        Node<String> searchResult = fruits.search("Banana");
        System.out.println("Search Result: " + searchResult.data);
        Node<String> findResult = fruits.find(1);
        System.out.println("Find Result: " + findResult.data);
        fruits.insert(findResult, "Pear");
        fruits.insert(fruits.tail, "Coconut");
        fruits.displayDetails();

        fruits.remove(fruits.tail);
        fruits.remove(fruits.head.next.next);
        fruits.displayDetails();
        fruits.removeLast();
        fruits.removeLast();
        fruits.displayDetails();
    }

    @Override
    public void insertFirst(T value) {
        super.insertFirst(value);
        if (head.next != null) {
            head.next.prev = head;
        }
        if (tail == null) { // initialize tail if we are inserting the first item in the whole list
            tail = head;
        }
    }

    @Override
    public void insert(Node<T> position, T value) {
        if (position == null)
            throw new IllegalArgumentException("Position cannot be null");

        // 1. Create the new node, insert it between position and the next position
        Node<T> newNode = new Node<>(value, position, position.next);

        // 2. If there is a node after "position", update its prev to be the new node
        if (position.next != null) {
            position.next.prev = newNode;
        } else { // "Position" was the tail, so update the tail
            tail = newNode;
        }
        // 3. Always link position back to the new node
        position.next = newNode;
        size++;
    }

    @Override
    public void insert(int index, T value) {
        this.insert(this.find(index), value);
    }

    @Override
    public void insertLast(T value) {
        // If tail already exists, add it and change it
        if (tail != null) {
            tail.next = new Node<>(value, tail, null);
            tail = tail.next;
        }
        // Else head and tail point to the same thing
        else {
            head = new Node<>(value, null, null);
            tail = head;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        super.removeFirst();

        if (head != null) {
            // Detach the old head's reference from the new head
            head.prev = null;
        } else {
            // The list just became empty, so reset tail to null too
            tail = null;
        }
    }

    @Override
    public void remove(Node<T> position) {
        if (position == null)
            throw new IllegalArgumentException("Position cannot be null");

        // 1. Update "left" neighbour, or move 'head' forward if position is the first node
        if (position.prev != null) {
            position.prev.next = position.next;
        } else {
            head = position.next;
        }

        // 2. Update "right" neighbour, or move 'tail' backwards if position is actually the last node
        if (position.next != null) {
            position.next.prev = position.prev;
        } else {
            tail = position.prev;
        }
        // 3. "Optional" cleanup (would do more of this if it was in C/C++)
        position.prev = null;
        position.next = null;

        // 4. Change the size of the list
        size--;
    }

    @Override
    public void remove(int index) {
        // Remove the element at the found index (utilise the find method)
        this.remove(this.find(index));
    }

    @Override
    public void removeLast() {
        if (tail == null)
            throw new NoSuchElementException("Cannot remove from an empty list");
        // Null both head and tail if there is only 1 element, set tail back otherwise
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    @Override
    public Node<T> search(T target) {
        if (head == null || target == null) return null;

        // Task 1: Search in forward direction from head
        Callable<Node<T>> searchFromHead = () -> {
            Node<T> current = head;
            while (current != null && !Thread.currentThread().isInterrupted()) {
                if (target.equals(current.data))
                    return current;
                current = current.next;
            }
            throw new Exception("Target could not be found from head");
        };
        // Task 2: Search in backwards direction from tail
        Callable<Node<T>> searchFromTail = () -> {
          Node<T> current = tail;
          while (current != null && !Thread.currentThread().isInterrupted()) {
              if (target.equals(current.data))
                  return current;
              current = current.prev;
          }
          throw new Exception("Target could not be found from tail");
        };

        // Try-to-search-with a multithread executor service!!! :0
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Invoke any: execute until the single fastest thread finishes
            // Returned value is the single fastest return between the two threads!
            return executor.invokeAny(List.of(searchFromHead, searchFromTail));
        }
        // Catch an interruption, or an execution exception which
        // occurs if BOTH threads fail to find the target value
        catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    @Override
    public Node<T> find(int index) {
        // 1. Validate bounds
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index" + index + " out of range, Size: " + size);
        Node<T> current;

        // 2. Pick a closer end and search
        // (No need to multi-thread, just calculate which end is better to start at)
        if (index < size / 2) {
            current = head;
            for (int iList = 0; iList < index; iList ++)
                current = current.next;
        } else {
            current = tail;
            for (int iList = size-1; iList > index; iList--)
                current = current.prev;
        }
        // 3. Return the node found
        return current;
    }

    @Override
    public void displayDetails() {
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
