package ru.astolbov;

import java.util.List;

public class Producer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;
    private List<T> source;
    private int countOffers = 0;

    public Producer(SimpleBlockingQueue<T> queue, List<T> source) {
        this.queue = queue;
        this.source = source;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
            while (queue.isFull()) {
                System.out.println("producer wait");
                queue.wait();
            }
            queue.offer(value);
            queue.notify();
        }
    }

    public void run() {
        for (int i = 0; i <= source.size() - 1; i++) {
            testWait(i);
            try {
                offer(source.get(i));
                countOffers++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCountOffers() {
        return countOffers;
    }

    private void testWait(int i) {
        if (i % 25 == 0) {
            try {
                synchronized (this) {
                    wait(100);
                }
            } catch (InterruptedException e) {
                System.out.println("producer interrupted");
            }
        }
    }

}
