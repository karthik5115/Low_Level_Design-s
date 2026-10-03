import Store.*;
import User.*;
import Utilities.*;

import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String args[]) {
        Main mainObj = new Main();

        // 1. create warehouses in the system
        List<WareHouse> warehouseList = new ArrayList<>();
        warehouseList.add(mainObj.addWarehouseAndItsInventory());

        // 2. create users in the system
        List<User> userList = new ArrayList<>();
        userList.add(mainObj.createUser());

        // 3. feed the system with the initial informations
        ProductDelivarySystem productDeliverySystem = new ProductDelivarySystem(userList, warehouseList);

        mainObj.runDeliveryFlow(productDeliverySystem, 1);
    }

    private WareHouse addWarehouseAndItsInventory() {

        WareHouse WareHouse = new WareHouse(new Address("123 Main St", "Anytown", "CA", "USA", "12345"));
        Inventory inventory = new Inventory();

        // CREATE 3 Products
        ProductCategory productCategory1 = new ProductCategory(1, "soft drink", 100.00);
        ProductCategory productCategory2 = new ProductCategory(2, "CAke", 20.0);
        ProductCategory productCategory3 = new ProductCategory(3, "Chocolate", 10.0);

        productCategory1.addProducts(10);
        productCategory2.addProducts(15);
        productCategory3.addProducts(20);

        inventory.addCategory(productCategory1);
        inventory.addCategory(productCategory2);
        inventory.addCategory(productCategory3);

        WareHouse.wareHouseInventory = inventory;
        return WareHouse;
    }

    private User createUser() {
        User user = new User("karthik", 1, new Address("123 Main St", "Anytown", "CA", "USA", "12345"));
        return user;
    }

    private void runDeliveryFlow(ProductDelivarySystem productDeliverySystem, int userId) {

        // 1. Get the user object
        User user = productDeliverySystem.getUser(userId);

        // 2. get WareHouse based on user preference
        WareHouse WareHouse = productDeliverySystem
                .getWareHouse(new Address("134 Main St", "Anytown", "CA", "USA", "12345"));

        // 3. get all the inventory to show the user
        Inventory inventory = productDeliverySystem.getInventory(WareHouse);

        ProductCategory productCategoryIWantToOrder = null;
        for (ProductCategory productCategory : inventory.listCategories()) {

            if (productCategory.getCategoryName().equals("soft drink")) {
                productCategoryIWantToOrder = productCategory;
            }
        }

        // 4. add product to the cart
        productDeliverySystem.addProductToCart(user, productCategoryIWantToOrder.getCategoryId(), 2);

        // 4. place order
        Order order = productDeliverySystem.placeOrder(user, WareHouse);

        // 5. checkout
        productDeliverySystem.checkout(order);
        Invoice invoice = order.generateInvoice();
        invoice.printInvoice();

    }
}
