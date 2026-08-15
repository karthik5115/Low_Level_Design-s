import java.util.*;
class SamsungStore implements Observable{
    List<Customer> customers = new ArrayList<>();
    boolean inStock;
    @Override
    public void addObserver(Customer customer) {
        customers.add(customer);
    }
    @Override
    public void removeObserver(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Customer customer : customers) {
            customer.update("hi, " + customer.getName() + " " + message);
        }
    }
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
        if (inStock) {
            notifyObservers("Samsung Galaxy S23 is now in stock!");
        }
    }
}