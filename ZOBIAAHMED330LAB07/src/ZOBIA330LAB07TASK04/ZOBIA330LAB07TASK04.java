//ZOBIA AHMED / 2022F-BSE-330 / LAB 07 / TASK 02:
package ZOBIA330LAB07TASK04;
import java.util.HashMap;
import java.util.Map;
public class ZOBIA330LAB07TASK04 {
	// Main class managing the bakery inventory
	public static class BakeryInventory {
	    private Map<String, Product> products;
	    public BakeryInventory() {
	        products = new HashMap<>();
	    }
	    // Product class representing each bakery product
	    private class Product {
	        String name;
	        int stock;

	        public Product(String name, int stock) {
	            this.name = name;
	            this.stock = stock;
	        }
	    }
	    // Method to add a new product to the inventory
	    public void addProduct(String productId, String name, int initialStock) {
	        products.put(productId, new Product(name, initialStock));
	    }
	    // Method to simulate selling a product
	    public synchronized void recordSale(String productId, int quantity) {
	        if (products.containsKey(productId)) {
	            Product product = products.get(productId);
	            if (product.stock >= quantity) {
	                product.stock -= quantity;
	                System.out.println(Thread.currentThread().getName() + ": Sold '" + quantity + "' " + product.name);
	            } else {
	                System.out.println(Thread.currentThread().getName() + ": Insufficient stock for " + product.name);
	            }
	        } else {
	            System.out.println(Thread.currentThread().getName() + ": Product not found: " + productId);
	        }
	    }
	    // Method to display current stock levels
	    public void displayStock() {
	        System.out.println("\n(*): Current Stock Levels:");
	        for (Map.Entry<String, Product> entry : products.entrySet()) {
	            System.out.println("Product ID: " + entry.getKey() + ", Name: " + entry.getValue().name + ", Stock: " + entry.getValue().stock);
	        }
	    }
	    // Thread for handling sales
	    private class SalesThread extends Thread {
	        private String productId;
	        private int quantity;

	        public SalesThread(String productId, int quantity) {
	            this.productId = productId;
	            this.quantity = quantity;
	        }
	        @Override
	        public void run() {
	            try {
	                // Simulate processing time
	                Thread.sleep(1000); // Sleep for 1 second
	                recordSale(productId, quantity);
	            } catch (InterruptedException e) {
	                System.out.println(Thread.currentThread().getName() + " was interrupted.");
	            }
	        }
	    }
	    // Thread for logging stock levels
	    private class LogThread extends Thread {
	        @Override
	        public void run() {
	            try {
	                // Simulate logging delay
	                Thread.sleep(500); // Sleep for 0.5 seconds
	                displayStock();
	            } catch (InterruptedException e) {
	                System.out.println(Thread.currentThread().getName() + " was interrupted.");
	            }
	        }
	    }
	    public static void main(String[] args) {
	    	System.out.println("ZOBIA AHMED / 2022F-BSE-330 / LAB 07 / TASK 02:\n");
	    	System.out.println("(*): SCENERIO: BAKERY PRODUCT STOCK(BAKERY PRODUCT SELLING):");
	    	System.out.println("(*): OBJECTIVE: Concurrency with Multithreading Mechanisms:");
	        BakeryInventory inventory = new BakeryInventory();
	        inventory.addProduct("001", "CUP CAKES", 100);
	        inventory.addProduct("002", "BROWNIES", 100);
	        inventory.addProduct("003", "BISCUITS", 1000);
	        inventory.addProduct("004", "PIZZAS", 60);
	        inventory.addProduct("005", "DOUGHNUTS", 300);
	        inventory.addProduct("006", "LOAF BREADS", 200);
	        // Display total stock before selling
	        inventory.displayStock();
	        // Perform selling transactions:
	        // Create threads for sales and logging:
	        System.out.println("");
	        System.out.println("(*): TOTAL ITEMS SOLD TODAY:");
	        SalesThread salesThread1 = inventory.new SalesThread("001", 5);
	        SalesThread salesThread2 = inventory.new SalesThread("002", 10);
	        LogThread logThread = inventory.new LogThread();
	        // Start sales threads
	        salesThread1.start();
	        salesThread2.start();
	        try {
	            // Ensure that sales threads complete before logging
	            salesThread1.join(); // Wait for salesThread1 to finish
	            salesThread2.join(); // Wait for salesThread2 to finish
	        } catch (InterruptedException e) {
	            System.out.println("Main thread was interrupted.");
	        }
	        // Start logging thread after sales are complete
	        logThread.start();
	        try {
	            // Wait for logging thread to complete
	            logThread.join(); // Ensure logThread completes before exiting main
	        } catch (InterruptedException e) {
	            System.out.println("Main thread was interrupted.");
	        }
	    }
	}
}
