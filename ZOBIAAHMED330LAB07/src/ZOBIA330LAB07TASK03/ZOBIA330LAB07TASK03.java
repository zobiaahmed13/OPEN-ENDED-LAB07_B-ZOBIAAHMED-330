//ZOBIA AHMED / 2022F-BSE-330 / LAB 07 / TASK 01:
package ZOBIA330LAB07TASK03;
import java.util.HashMap;
import java.util.Map;
public class ZOBIA330LAB07TASK03 {
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
	    // Add a new product to the inventory
	    public void addProduct(String productId, String name, int initialStock) {
	        products.put(productId, new Product(name, initialStock));
	    }
	    // Update stock after a sale
	    public void recordSale(String productId, int quantity) {
	        if (products.containsKey(productId)) {
	            Product product = products.get(productId);
	            if (product.stock >= quantity) {
	                product.stock -= quantity;
	                System.out.println("Sold " + quantity + " of " + product.name);
	            } else {
	                System.out.println("Insufficient stock for " + product.name);
	            }
	        } else {
	            System.out.println("Product not found: " + productId);
	        }
	    }
	    // Get current stock level
	    public int getStockLevel(String productId) {
	        if (products.containsKey(productId)) {
	            return products.get(productId).stock;
	        }
	        System.out.println("Product not found: " + productId);
	        return 0; // or throw an exception
	    }
	    // Display all products in stock
	    public void displayStock() {
	        System.out.println("(*): Current Stock Levels:");
	        for (Map.Entry<String, Product> entry : products.entrySet()) {
	            System.out.println("Product ID: " + entry.getKey() + ", Name: " + entry.getValue().name + ", Stock: " + entry.getValue().stock);
	        }
	    }
	    public static void main(String[] args) {
	    	System.out.println("ZOBIA AHMED / 2022F-BSE-330 / LAB 07 / TASK 01:\n");
	    	System.out.println("(*): SCENERIO: BAKERY PRODUCT STOCK(BAKERY PRODUCT SELLING):");
	    	System.out.println("(*): OBJECTIVE: Implement Good Code Practices and Optimization Techniques:\n");
	        BakeryInventory inventory = new BakeryInventory();
	        inventory.addProduct("001", "CUP CAKES", 100);
	        inventory.addProduct("002", "BROWNIES", 100);
	        inventory.addProduct("003", "BISCUITS", 1000);
	        inventory.addProduct("004", "PIZZAS", 60);
	        inventory.addProduct("005", "DOUGHNUTS", 300);
	        inventory.addProduct("006", "LOAF BREADS", 200);
	        // Display total stock before selling
	        inventory.displayStock();
	        // Perform selling transactions
	        System.out.println("\n");
	        System.out.println("(*): TOTAL ITEMS SOLD TODAY:");
	        inventory.recordSale("001", 80);
	        inventory.recordSale("002", 75);
	        inventory.recordSale("003", 880);
	        inventory.recordSale("004", 35);
	        inventory.recordSale("005", 250);
	        inventory.recordSale("006", 175);
	        // Display remaining stock after selling
	        System.out.println("\n(*): Remaining Stock After Sales:");
	        inventory.displayStock();
	    }
	}
}


