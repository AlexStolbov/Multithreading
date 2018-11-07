package ru.astolbov;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    /**
     * Max count of elements in queue;
     */
    private final int maxSize = 20;

    /**
     * Add element into queue.
     * @param value - added element.
     */
    public synchronized void offer(T value) {
        while (isFull()) {
            try {
                System.out.println("---------- offer wait");
                wait();
            } catch (InterruptedException e) {
                //
            }
        }
        this.queue.add(value);
        System.out.println("offer " + value);
        notify();
    }

    /**
     * Return last added element from queue.
     */
    public synchronized T poll() {
        while (isEmpty()) {
            try {
                System.out.println("-------- polling wait");
                wait();
            } catch (InterruptedException e) {
                //
            }
        }
        T res = this.queue.poll();
        notify();
        return res;
    }

    /**
     * Indicates when the queue is empty.
     * @return true when no elements into the queue.
     */
    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }

    /**
     * Indicate when the queue is full.
     * @return true when is not possible add new element to queue.
     */
    public synchronized boolean isFull() {
        return queue.size() == maxSize;
    }

}
