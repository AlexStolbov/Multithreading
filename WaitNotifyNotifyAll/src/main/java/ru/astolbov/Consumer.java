package ru.astolbov;

import java.util.ArrayList;
import java.util.List;

public class Consumer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;
    private final List<T> destArray = new ArrayList<>();
    private int countElements;

    public Consumer(SimpleBlockingQueue<T> queue, int countElements) {
        this.queue = queue;
        this.countElements = countElements;
    }

    public void poll() throws InterruptedException {
        T res = queue.poll();
        destArray.add(res);
    }

    @Override
    public void run() {
        for (int i = 0; i < this.countElements; i++) {
            testWait(i);
            try {
                poll();
            } catch (InterruptedException e) {
                //
            }
        }
    }

    public List<T> getDestArray() {
        return destArray;
    }

    private void testWait(int i) {
        if (i % 20 == 0) {
            try {
                synchronized (this) {
                    wait(100);
                }
            } catch (InterruptedException e) {
                System.out.println("consumer interrupted");
            }
        }
    }

}
