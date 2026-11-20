package blackie.gavin.dsasimulatorserver.thread;

import blackie.gavin.dsasimulatorserver.domain.Linear.Doubly;
import blackie.gavin.dsasimulatorserver.domain.Linear.LinkedList;

public class NativeDoublyTestRunnable implements Runnable{
    @Override
    public void run() {
        long startTime, endTime, elapsedTime;
        startTime = System.nanoTime();

        LinkedList<Integer> list = new Doubly<>();
        for (int iTest = 0; iTest < 1000000; iTest++) {
            list.insertLast(iTest);
        }
        for (int iTest = 0; iTest < 1000000; iTest++) {
            list.removeLast();
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("[Native LinkedList Test] InsertingLast and RemovingLast Elapsed Time: " + elapsedTime);
    }
}
