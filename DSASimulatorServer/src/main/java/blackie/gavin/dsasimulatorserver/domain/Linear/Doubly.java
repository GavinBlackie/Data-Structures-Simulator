package blackie.gavin.dsasimulatorserver.domain.Linear;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        Node<String> searchResult = fruits.search("Banana");
        System.out.println("Search Result: " + searchResult.data);
        Node<String> findResult = fruits.find(1);
        System.out.println("Find Result: " + findResult.data);
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
          Node<T> current = head;
          while (current != null && !Thread.currentThread().isInterrupted()) {
              if (target.equals(current.data))
                  return current;
              current = current.next;
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
