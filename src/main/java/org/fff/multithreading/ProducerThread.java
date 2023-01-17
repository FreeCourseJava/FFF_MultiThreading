package org.fff.multithreading;

import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;

public class ProducerThread implements Runnable {

    BlockingQueue<String> blockingQueue;

    public ProducerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; ; i++) {
            try {
                blockingQueue.put(LocalTime.now().toString());
                System.out.println("Поставил в очередь значение " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                 throw new RuntimeException(e);
            }
        }
    }
}
