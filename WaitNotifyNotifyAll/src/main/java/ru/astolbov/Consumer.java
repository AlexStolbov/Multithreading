package ru.astolbov;

import java.util.Queue;

public class Consumer<T> implements Runnable {
    private final SimpleBlockingQueue<T> queue;
    private int pooledElements = 0;

    public Consumer(SimpleBlockingQueue<T> queue) {
        this.queue = queue;
    }

    public T poll() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                System.out.println("consumer wait");
                queue.wait();
            }
            T res = queue.poll();
            pooledElements++;
            queue.notify();
            return res;
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            testWait(i++);
            try {
                poll();
            } catch (InterruptedException e) {
                System.out.println("consumer interrupted");
            }
        }
    }

    public int getPooledElements() {
        return pooledElements;
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
