package org.fff.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThreadDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        ConnectorDB connectDB = new ConnectorDB();

        Thread myProducer = new Thread(new ProducerThread(blockingQueue));
        Thread myConsumer = new Thread(new ConsumerThread(blockingQueue, connectDB));
        if (args.length != 1) {
            myProducer.start();
            myConsumer.start();
        } else if (args[0].equals("-p")) {
            connectDB.showDBContent();
        }
    }
}
