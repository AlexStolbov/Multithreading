package ru.astolbov;

import java.util.ArrayList;
import java.util.List;

public class Consumer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;
    private final List<T> destArray = new ArrayList<>();

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public void poll() throws InterruptedException {
        T res = queue.poll();
        destArray.add(res);
    }

    @Override
    public void run() {
        int i = 0;
        while (!(queue.isEmpty() & Thread.currentThread().isInterrupted())) {
            testWait(i++);
            try {
                poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public List<T> getDestArray() {
        return destArray;
    }

    private void testWait (int i) {
        if (i % 20 == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
