package blackie.gavin.dsasimulatorserver.thread;

import java.util.LinkedList;

public class JavaLinkedListTestRunnable implements Runnable{
    @Override
    public void run() {
        long startTime, endTime, elapsedTime;
        startTime = System.nanoTime();

        LinkedList<Integer> list = new LinkedList<>();
        for (int iTest = 0; iTest < 1000000; iTest++) {
            list.add(iTest);
        }
        for (int iTest = 0; iTest < 1000000; iTest++) {
            list.removeLast();
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("[Java LinkedList Test] InsertingLast and RemovingLast Elapsed Time: " + elapsedTime);
    }
}
