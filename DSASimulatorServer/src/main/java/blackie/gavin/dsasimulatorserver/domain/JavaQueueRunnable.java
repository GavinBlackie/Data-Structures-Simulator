package blackie.gavin.dsasimulatorserver.domain;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueueRunnable implements Runnable {

    @Override
    public void run() {
        long startTime, endTime, elapsedTime;
        startTime = System.nanoTime();

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int iTest = 0; iTest < 100; iTest++) {
            queue.offer(iTest);
        }
        for (int iTest = 0; iTest < 100; iTest++) {
            queue.poll();
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("JavaQueueRunnable Elapsed Time: " + elapsedTime);
    }
}
