package Utilities;

import Store.WareHouse;
import java.util.List;

public class NearestWareHouse implements WareHouseSelectionStrategy {

    @Override
    public WareHouse getWareHouse(Address address, List<WareHouse> wareHouses) {
        // actually the nearestwarehouse is based on the address longitude and latitue.
        // but simply i am getting the first warehouse
        for (WareHouse wareHouse : wareHouses) {
            return wareHouse;
        }
        return null;
    }

    // public double getDistance(Address address1, Address address2) {
    // double lat1 = address1.getLatitude();
    // double lon1 = address1.getLongitude();
    // double lat2 = address2.getLatitude();
    // double lon2 = address2.getLongitude();
    // double R = 6371;
    // double dLat = Math.toRadians(lat2 - lat1);
    // double dLon = Math.toRadians(lon2 - lon1);
    // double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    // Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
    // Math.sin(dLon / 2) * Math.sin(dLon / 2);
    // double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    // return R * c;
    // } sin^2(dlat/2)+cos(lat1)*cos(lat2)*sin^2(dlon/2)
}
