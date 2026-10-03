package Store;

import Utilities.Address;

public class WareHouse {
    public Inventory wareHouseInventory;
    Address address;

    public WareHouse(Address address) {
        this.address = address;
        wareHouseInventory = new Inventory();
    }

    public void addCategory(ProductCategory category) {
        wareHouseInventory.addCategory(category);
    }

    public void removeCategory(int categoryId) {
        wareHouseInventory.removeCategory(categoryId);
    }

    public Inventory getInventory() {
        return wareHouseInventory;
    }

    public Address getAddress() {
        return address;
    }
}
