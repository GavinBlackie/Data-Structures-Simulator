package blackie.gavin.dsasimulatorserver.domain;

import blackie.gavin.dsasimulatorserver.thread.CircularQueueRunnable;
import blackie.gavin.dsasimulatorserver.thread.JavaQueueRunnable;

import java.util.Queue;
import java.util.LinkedList;

public class CircularQueue extends LinearStructure{
    // Remember LIFO: Last In, First Out
    // Will be a Circular Array Implementation!!
    // https://www.geeksforgeeks.org/dsa/introduction-to-circular-queue-1/ <-- this helped refresh some concepts for me

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

    static void main() {
        CircularQueue queue = new CircularQueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println("IsFull?: " + queue.isFull());
        System.out.println(queue.dequeue());
        queue.enqueue(5);
        queue.enqueue(6); // will cause a grow!
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10); // will cause a grow!


        // Testing queue runtime!! (Apparently mine is WAY faster :) )
        long startTime, endTime, elapsedTime;
        CircularQueue queue2 = new CircularQueue();

        startTime = System.nanoTime();
        queue2.enqueue(1);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println(elapsedTime);

        startTime = System.nanoTime();
        Queue<Integer> javaQueue = new LinkedList<>();
        javaQueue.offer(1);
        System.out.println(javaQueue.element());
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println(elapsedTime);

        // Testing using Multithreading!!
        Thread javaQueueThread =    new Thread(new JavaQueueRunnable() );
        Thread circularQueueThread =     new Thread(new CircularQueueRunnable() );
        javaQueueThread.start();
        circularQueueThread.start();
    }

    private int[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }
    public CircularQueue() {
        capacity = 10;
        array = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(int element) {
        // If the queue is full, we cannot enqueue and must grow
        if ( isFull() ) {
            this.grow();
        }

        // Recalculate rear, adjust with modulus for capacity in-case it resets to other side
        rear = (front + size) % capacity;
        // Add the element to the rear of "the cashier line"
        array[rear] = element;
        size++;
    }
    public Integer dequeue() {
        // If the queue is empty, then we cannot dequeue any elements
        if ( isEmpty() ) {
            return null; // return a null value if empty
        }

        // obtain the element at the "front of the line"
        int element = array[front];
        array[front] = 0; // "null" the value
        // Re-adjust
        front = (front + 1) % capacity; // move front to the right
        size--;
        return element;
    }

    private void grow() {
        int tempCapacity = capacity * 2;
        int[] tempArray = new int[tempCapacity];

        // Copy elements in correct order
        // eg. [D, E, F, A, B, C] logically becomes [D, E, F, _, _, _, _, _, _, A, B, C],
        //      but the literal equivalent created is [A, B, C, D, E, F, _, _, _, _, _, _]
        int staticSize = this.size; // save size to avoid logic problems (dequeue decreases size)
        for (int iDeq = 0; iDeq < staticSize; iDeq++) {
            tempArray[iDeq] = this.dequeue();
        }
        front = 0;
        if (staticSize > 0)
            rear = staticSize-1;
        else
            rear = 0;
        size = staticSize;
        capacity = tempCapacity;
        this.array = tempArray;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
    public boolean isFull() {
        return size >= capacity;
    }
}
