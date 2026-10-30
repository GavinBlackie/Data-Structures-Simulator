package blackie.gavin.dsasimulatorserver.thread;

import blackie.gavin.dsasimulatorserver.domain.Linear.CircularQueue;

public class CircularQueueTestRunnable implements Runnable{

    @Override
    public void run() {
        long startTime, endTime, elapsedTime;
        startTime = System.nanoTime();

        CircularQueue queue = new CircularQueue(1000);
        for (int iTest = 0; iTest < 100000; iTest++) {
            queue.enqueue(iTest);
        }
        for (int iTest = 0; iTest < 100000; iTest++) {
            queue.dequeue();
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("CircularQueueRunnable Elapsed Time: " + elapsedTime);
    }
}
