package Utilities;

import java.util.List;

import Store.WareHouse;

public interface WareHouseSelectionStrategy {
    public WareHouse getWareHouse(Address address, List<WareHouse> wareHouses);
}
