package org.fff.multithreading;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ConsumerThread implements Runnable {

    BlockingQueue<String> blockingQueue;
    ConnectorDB connectorDB;

    public ConsumerThread(BlockingQueue<String> blockingQueue, ConnectorDB connectorDB) {
        this.blockingQueue = blockingQueue;
        this.connectorDB = connectorDB;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; ; i++) {
            String value = "";
            if (random.nextInt(2) == 0) {
                try {
                    value = blockingQueue.take();
                    connectorDB.put(value);
                    System.out.println("Разместил в БД значение " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }
            } else {
                System.out.println("База данных отвалилась! Попытка подключения через 5 секунд");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}

