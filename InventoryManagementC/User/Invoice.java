package User;

import java.util.Map;

import Store.Inventory;

public class Invoice {

    int totalItemPrice;
    int totalTax;
    int totalFinalPrice;
    Order order;
    Inventory inventory;

    public Invoice(Order order, Inventory inventory) {
        this.order = order;
        this.inventory = inventory;
    }

    // generate Invoice
    public Invoice generateInvoice() {
        // it will compute and update the above details
        for (Map.Entry<Integer, Integer> entry : order.productCategoryIdQuantityMap.entrySet()) {
            int productCategoryId = entry.getKey();
            int quantity = entry.getValue();
            double price = inventory.getCategory(productCategoryId).getPrice();
            totalItemPrice += (price * quantity);
            totalTax += (price * quantity * 0.1);
            totalFinalPrice += (price * quantity * 1.1);
        }
        return this;
    }

    public void printInvoice() {
        System.out.println("Total Item Price: " + totalItemPrice);
        System.out.println("Total Tax: " + totalTax);
        System.out.println("Total Final Price: " + totalFinalPrice);
    }
}
