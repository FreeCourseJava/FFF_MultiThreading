package org.fff.multithreading;

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
        for (int i = 0; ; i++) {
            String value = "";
            try {
                value = blockingQueue.take();
                connectorDB.put(value);
                System.out.println("Разместил в БД значение " + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e) {
                System.out.println("База данных отвалилась! Попытка подключения через 5 секунд");
                i--;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e);
                }
            }

        }

    }

}



