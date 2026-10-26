package blackie.gavin.dsasimulatorserver.domain;

public class Queue {

    int[] array;
    int front; // Will be a Circular Array Implementation!!
    int rear;

    public Queue(int size) {
        array = new int[size];
        int front = 0;
        int rear = size - 1;
    }

    public void enqueue(int element) {

    }
    public int dequeue() {
        return 0;
    }
}
