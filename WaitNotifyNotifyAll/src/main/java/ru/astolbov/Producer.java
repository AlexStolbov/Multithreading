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

    public void offer(T value){
            queue.offer(value);
    }

    public void run() {
        for (int i = 0; i <= source.size() - 1; i++) {
            testWait(i);
            offer(source.get(i));
            countOffers++;
        }
    }

    public int getCountOffers() {
        return countOffers;
    }

    private void testWait(int i) {
        if (i % 25 == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
