package Store;

import java.util.List;
import Utilities.Address;
import Utilities.WareHouseSelectionStrategy;
import Utilities.NearestWareHouse;
import java.util.ArrayList;

public class WareHouseController {
    List<WareHouse> warehouses;
    WareHouseSelectionStrategy wareHouseSelectionStrategy;

    public WareHouseController(String type) {
        this.warehouses = new ArrayList<>();
        this.wareHouseSelectionStrategy = selectWareHouseSelectionStrategy(type);
    }

    public WareHouseController(List<WareHouse> warehouses, String type) {
        this.warehouses = warehouses;
        this.wareHouseSelectionStrategy = selectWareHouseSelectionStrategy(type);
    }

    private WareHouseSelectionStrategy selectWareHouseSelectionStrategy(String type) {
        type = type.toLowerCase();
        switch (type) {
            case "nearestwarehouse":
                return new NearestWareHouse();
            default:
                return new NearestWareHouse();
        }
    }

    public void addWareHouse(WareHouse wareHouse) {
        warehouses.add(wareHouse);
    }

    public void removeWareHouse(WareHouse wareHouse) {
        warehouses.remove(wareHouse);
    }

    public WareHouse getWareHouse(Address address) {
        return wareHouseSelectionStrategy.getWareHouse(address, warehouses);
    }
}
