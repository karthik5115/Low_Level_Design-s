import java.util.List;
import User.OrderController;
import User.UserController;
import Store.WareHouse;
import Store.WareHouseController;
import User.User;
import Utilities.Address;
import Store.Inventory;
import User.Order;

public class ProductDelivarySystem {
    OrderController orderController;
    UserController userController;
    WareHouseController wareHouseController;

    public ProductDelivarySystem(List<User> userList, List<WareHouse> warehouseList) {
        orderController = new OrderController();
        userController = new UserController(userList);
        wareHouseController = new WareHouseController(warehouseList, "nearestwarehouse");
    }

    public ProductDelivarySystem() {
        orderController = new OrderController();
        userController = new UserController();
        wareHouseController = new WareHouseController("nearestwarehouse");
    }

    public User getUser(int userId) {
        return userController.getUser(userId);
    }

    public WareHouse getWareHouse(Address address) {
        return wareHouseController.getWareHouse(address);
    }

    public Inventory getInventory(WareHouse wareHouse) {
        return wareHouse.getInventory();
    }

    public void addProductToCart(User user, int categoryId, int quantity) {
        Inventory inventory = getWareHouse(user.getAddress()).getInventory();
        // check for categoryId and quantity. available or not
        if (inventory.isProductAvailable(categoryId, quantity)) {
            user.getCart().addProduct(categoryId, quantity);
            System.out.println("Product added to cart successfully");
        } else {
            System.out.println("Product not available");
        }
    }

    public Order placeOrder(User user, WareHouse wareHouse) {
        Order order = orderController.createNewOrder(user, wareHouse);
        return order;
    }

    public void checkout(Order order) {
        order.checkOut();
    }

}
