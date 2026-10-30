package blackie.gavin.dsasimulatorserver.thread;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueueTestRunnable implements Runnable {

    @Override
    public void run() {
        long startTime, endTime, elapsedTime;
        startTime = System.nanoTime();

        Queue<Integer> queue = new LinkedList<>();
        for (int iTest = 0; iTest < 100000; iTest++) {
            queue.offer(iTest);
        }
        queue.element(); // just do a simple view to avoid warnings
        for (int iTest = 0; iTest < 100000; iTest++) {
            queue.poll();
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("JavaQueueRunnable Elapsed Time: " + elapsedTime);
    }
}
