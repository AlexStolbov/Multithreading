package ru.astolbov;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOffersAnyElementsThenPollExactlyEqualsElements() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            source.add(i);
        }
        Producer<Integer> producer = new Producer<>(queue, source);
        //Consumer<Integer> consumer = new Consumer<>(queue, source.size());
        Consumer<Integer> consumer = new Consumer<>(queue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        consumerThread.start();
        producerThread.start();
        producerThread.join();
        consumerThread.interrupt();
        consumerThread.join();

        assertThat(producer.getCountOffers(), is(source.size()));
        assertThat(consumer.getDestArray(), is(source));
    }
}