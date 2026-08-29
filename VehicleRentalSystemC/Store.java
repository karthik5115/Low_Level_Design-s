import java.util.ArrayList;
import java.util.List;

public class Store {
    private int storeId;
    private Location location;
    private VehicleInventory vehicleInventory;
    private List<Reservation> reservationList;

    // constructor
    public Store(int storeId, Location location, VehicleInventory vehicleInventory) {
        this.storeId = storeId;
        this.location = location;
        this.vehicleInventory = vehicleInventory;
        this.reservationList = new ArrayList<>();
    }

    // getters
    public int getStoreId() {
        return storeId;
    }

    public Location getLocation() {
        return location;
    }

    public VehicleInventory getVehicleInventory() {
        return vehicleInventory;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    // setters
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setVehicleInventory(VehicleInventory vehicleInventory) {
        this.vehicleInventory = vehicleInventory;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void addReservation(Reservation reservation) {
        if (reservation != null) {
            this.reservationList.add(reservation);
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleInventory.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicleInventory.removeVehicle(vehicle);
    }

    public Vehicle getVehicleById(int vehicleId) {
        return vehicleInventory.getVehicleById(vehicleId);
    }

    public Vehicle getVehicleByRegistrationNo(String registrationNo) {
        return vehicleInventory.getVehicleByRegistrationNo(registrationNo);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleInventory.getAllVehicles();
    }

    public List<Vehicle> getAvailableVehiclesByType(Type type) {
        return vehicleInventory.getAvailableVehiclesByType(type);
    }

    public List<Vehicle> getAvailableVehiclesByTypeAndRate(Type type, double minRate, double maxRate) {
        return vehicleInventory.getAvailableVehiclesByTypeAndRate(type, minRate, maxRate);
    }

    public boolean updateVehicleStatus(int vehicleId, Status status) {
        return vehicleInventory.updateVehicleStatus(vehicleId, status);
    }

    public void printAllVehicles() {
        vehicleInventory.printAllVehicles();
    }

    public void printAvailableVehicles() {
        vehicleInventory.printAvailableVehicles();
    }
}

class CarStore extends Store {
    public CarStore(int storeId, Location location, VehicleInventory vehicleInventory) {
        super(storeId, location, vehicleInventory);
    }
}

class BikeStore extends Store {
    public BikeStore(int storeId, Location location, VehicleInventory vehicleInventory) {
        super(storeId, location, vehicleInventory);
    }
}