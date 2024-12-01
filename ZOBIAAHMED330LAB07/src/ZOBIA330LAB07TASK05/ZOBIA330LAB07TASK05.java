//ZOBIA AHMED / 2022F-BSE-330 / LAB 07 / TASK 05:
package ZOBIA330LAB07TASK05;
import java.util.LinkedList;
import java.util.Queue;
public class ZOBIA330LAB07TASK05 {
	public static class BakeryInventory {
	    private final Queue<String> stockBuffer;
	    private final int capacity;
	    public BakeryInventory(int capacity) {
	        this.capacity = capacity;
	        this.stockBuffer = new LinkedList<>();
	    }
	    // Producer class that adds products to the buffer
	    private class Producer extends Thread {
	        private final String product;
	        public Producer(String product) {
	            this.product = product;
	        }

	        @Override
	        public void run() {
	            try {
	                while (true) {
	                    produce(product);
	                    Thread.sleep(10); // Simulate time taken to produce
	                }
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }
	        private synchronized void produce(String product) throws InterruptedException {
	            while (stockBuffer.size() == capacity) {
	                System.out.println("Buffer is full. Producer is waiting.");
	                wait(); // Wait until space is available
	            }
	            stockBuffer.add(product);
	            System.out.println("Produced: " + product);
	            notify(); // Notify a waiting consumer
	        }
	    }
	    // Consumer class that removes products from the buffer
	    private class Consumer extends Thread {
	        @Override
	        public void run() {
	            try {
	                while (true) {
	                    consume();
	                    Thread.sleep(10); // Simulate time taken to consume
	                }
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }
	        private synchronized void consume() throws InterruptedException {
	            while (stockBuffer.isEmpty()) {
	                System.out.println("Buffer is empty. Consumer is waiting.");
	                wait(); // Wait until items are available
	            }
	            String product = stockBuffer.poll();
	            System.out.println("Consumed: " + product);
	            notify(); // Notify a waiting producer
	        }
	    }
	    public static void main(String[] args) {
	        BakeryInventory inventory = new BakeryInventory(5); // Buffer capacity of 5
	        System.out.println("ZOBIA AHMED / 2022F-BSE-330 / LAB 07 / TASK 05:\n");
	    	System.out.println("(*): SCENERIO: BAKERY PRODUCT STOCK(BAKERY PRODUCT SELLING):");
	    	System.out.println("(*): OBJECTIVE: Inter-Thread Communication Using Synchronization:");
	        // Create producer and consumer threads
	        Producer producer1 = inventory.new Producer("CUP CAKES");
	        Producer producer2 = inventory.new Producer("BROWNIES");
	        Producer producer3 = inventory.new Producer("BISCUITS");
	        Producer producer4 = inventory.new Producer("LOAF BREADS");
	        Consumer consumer = inventory.new Consumer();
	        // Start the threads
	        producer1.start();
	        producer2.start();
	        producer3.start();
	        producer4.start();
	        consumer.start();
	    }
	}
}
