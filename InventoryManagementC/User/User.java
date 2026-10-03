package User;

import java.util.ArrayList;
import java.util.List;
import Utilities.Address;

public class User {
    private Cart cartDetails;
    private String userName;
    private int userId;
    private List<Order> orders;
    private Address address;

    public User(String userName, int userId, Address address) {
        this.userName = userName;
        this.userId = userId;
        this.address = address;
        this.cartDetails = new Cart();
        this.orders = new ArrayList<>();

    }

    public Cart getCart() {
        return cartDetails;

    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}