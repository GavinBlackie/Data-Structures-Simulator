package blackie.gavin.dsasimulatorserver.domain;

public class RawQueueRunnable implements Runnable{

    @Override
    public void run() {
        long startTime, endTime, elapsedTime;
        startTime = System.nanoTime();

        RawQueue queue = new RawQueue(100);
        for (int iTest = 0; iTest < 100; iTest++) {
            queue.enqueue(iTest);
        }
        for (int iTest = 0; iTest < 100; iTest++) {
            try {
                queue.dequeue();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("RawQueueRunnable Elapsed Time: " + elapsedTime);
    }
}
