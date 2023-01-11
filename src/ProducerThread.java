package src;

import java.time.LocalTime;
import java.util.concurrent.BlockingQueue;

public class ProducerThread implements Runnable {

    BlockingQueue<String> blockingQueue;

    public ProducerThread(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                blockingQueue.put(LocalTime.now().toString());
                System.out.println("положил значение " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                 throw new RuntimeException(e);
            }
        }
    }
}
