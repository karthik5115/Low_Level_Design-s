import java.util.*;
class IphoneStore implements Observable {
    private List<Customer> observers = new ArrayList<>();
    private boolean instock;
    @Override
    public void addObserver(Customer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Customer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Customer observer : observers) {
            observer.update(observer.getName() +" "+message);
        }
    }
    public void setInstock(boolean stockavailable){
        this.instock=stockavailable;
        if (instock) {
            notifyObservers("iphone stock is available");
        }
    }
}