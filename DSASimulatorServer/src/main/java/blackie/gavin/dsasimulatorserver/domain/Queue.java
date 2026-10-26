package blackie.gavin.dsasimulatorserver.domain;

public class Queue extends LinearStructure{
    // Remember LIFO: Last In, First Out
    // Will be a Circular Array Implementation!!
    // https://www.geeksforgeeks.org/dsa/introduction-to-circular-queue-1/

    //region Example Scenario
    // example scenario:
    /*   fr                 capacity=4
        [_, _, _, _]    front=0 rear=0 size=0
        *enqueue(A)*
         fr
        [A, _, _, _]    front=0 rear=0 size=1
        *enqueue(B)*
         f  r
        [A, B, _, _]    front=0 rear=1 size=2
        *enqueue(C)*
         f     r
        [A, B, C, _]    front=0 rear=0 size=3
        *dequeue* => returns A  (LIFO)
            f  r
        [_, B, C, _]    front=1 rear=2 size=2
        *enqueue D*
            f     r
        [_, B, C, D]    front=1 rear=3 size=3
        *enqueue E*
         r  f
        [E, B, C, D]    front=1 rear=(front + size) % capacity = (1 + 3) % 4 = 4 % 4 = 0       size=4
        *dequeue, enqueue F*
            r  f
        [E, F, C, D]    front=3 rear=1      size=4
        *dequeue, dequeue*
         f  r
        [E, F, _, _]    front=(front + 1) % capacity = (3+1) % 4 = 0    rear=1      size=2
        *dequeue*
            fr
        [_, F, _, _]    front=(front + 1) % capacity = (0+1) % 4 = 1    rear=1      size=1
     */
    //endregion

    static void main() throws Exception {
        Queue queue = new Queue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println("IsFull?: " + queue.isFull());
        System.out.println(queue.dequeue());
        queue.enqueue(5);
        queue.enqueue(555);
    }

    private int[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        int front = 0;
        int rear = 0;
        int size = 0;
    }
    public Queue() {
        capacity = 10;
        array = new int[capacity];
        int front = 0;
        int rear = 0;
        int size = 0;
    }

    public void enqueue(int element) {
        // If the queue is full, we cannot enqueue
        if ( isFull() ) {
            System.out.println("Cannot enqueue a full queue");
            return;
        }

        // Recalculate rear, adjust with modulus for capacity in-case it resets to other side
        rear = (front + size) % capacity;
        // Add the element to the rear of "the cashier line"
        array[rear] = element;
        size++;
    }
    public int dequeue() throws Exception {
        // If the queue is empty, then we cannot dequeue any elements
        if ( isEmpty() ) {
            throw new Exception("Cannot dequeue from an empty queue");
        }

        // obtain the element at the "front of the line"
        int element = array[front];
        array[front] = 0; // "null" the value
        // Re-adjust
        front = (front + 1) % capacity; // move front to the right
        size--;
        return element;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
    @Override
    public boolean isFull() {
        return size == capacity;
    }
}
