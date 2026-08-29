import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    List<Store> stores;
    List<User> users;

    public RentalSystem() {
        stores = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<User> getUsers() {
        return users;
    }

    // crud
    public void addStore(Store store) {
        stores.add(store);
        System.out.println("Store added: " + store.getStoreId());
    }

    public void removeStore(Store store) {
        stores.remove(store);
        System.out.println("Store removed: " + store.getStoreId());
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getUserId());
    }

    public void removeUser(User user) {
        users.remove(user);
        System.out.println("User removed: " + user.getUserId());
    }

    public Store getStoreByLocation(String city) {
        for (Store s : stores) {
            if (s.getLocation().getCity().equalsIgnoreCase(city)) {
                return s;
            }
        }
        return null;
    }

    public List<Store> getStoresByLocation(String city) {
        List<Store> result = new ArrayList<>();
        for (Store s : stores) {
            if (s.getLocation().getCity().equalsIgnoreCase(city)) {
                result.add(s);
            }
        }
        return result;
    }
}
