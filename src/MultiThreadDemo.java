package src;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThreadDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        ConnectorDB connectDB = new ConnectorDB();

        Thread myProducer = new Thread(new ProducerThread(blockingQueue));
        Thread myConsumer = new Thread(new ConsumerThread(blockingQueue, connectDB));
        myProducer.start();
        myConsumer.start();
    }
}
