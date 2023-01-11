package src;

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
        for (int i = 0; i < 10; i++) {
            String value = "";
            if (i == 5) {
                connectorDB.loseConnection();
            }
            try {
                value = blockingQueue.take();
                connectorDB.put(value);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (RuntimeException e) {
                try {
                    Thread.sleep(5000);
                    connectorDB.getConnection();
                    connectorDB.put(value);
                } catch (InterruptedException ex) {
                    throw new  RuntimeException(ex);
                }
            }
        }
    }
}

