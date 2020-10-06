package ru.job4j.concurrent.prodcons;



import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleBlockingQueueTest {

    @Test
    public void queueTest() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        List<Integer> result = new ArrayList<>();

        Runnable producer = () -> queue.offer(count.incrementAndGet());

        Runnable consumer = () -> result.add(queue.poll());

        for (int i = 0; i < 3; i++) {
            Thread producerThread = new Thread(producer);
            producerThread.start();
            producerThread.join();
        }

        for (int i = 0; i < 2; i++) {
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
            consumerThread.join();
        }

        Assert.assertThat(result,is(Arrays.asList(1,2)));
    }
}
