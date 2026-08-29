import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleInventory {
    private List<Vehicle> vehicles;
    Store store;

    public VehicleInventory() {
        this.vehicles = new ArrayList<>();
    }

    // getter store
    public Store getStore() {
        return store;
    }

    // setter store
    public void setStore(Store store) {
        this.store = store;
    }

    // Add vehicle to inventory
    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            vehicles.add(vehicle);
            System.out.println("Vehicle added to inventory: " + vehicle.getRegistrationNo());
        }
    }

    // Remove vehicle from inventory
    public void removeVehicle(Vehicle vehicle) {
        if (vehicle != null && vehicles.remove(vehicle)) {
            System.out.println("Vehicle removed from inventory: " + vehicle.getRegistrationNo());
        } else {
            System.out.println("Vehicle not found in inventory: " + vehicle.getRegistrationNo());
        }
    }

    // Find vehicle by ID
    public Vehicle getVehicleById(int vehicleId) {
        return vehicles.stream()
                .filter(v -> v.getVehicleId() == vehicleId)
                .findFirst()
                .orElse(null);
    }

    // Find vehicle by registration number
    public Vehicle getVehicleByRegistrationNo(String registrationNo) {
        return vehicles.stream()
                .filter(v -> v.getRegistrationNo().equalsIgnoreCase(registrationNo))
                .findFirst()
                .orElse(null);
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    // Get available vehicles of a specific type
    public List<Vehicle> getAvailableVehiclesByType(Type type) {
        return vehicles.stream()
                .filter(v -> v.getType() == type && v.getStatus() == Status.active)
                .collect(Collectors.toList());
    }

    // Get available vehicles of a specific type with daily rate within range
    public List<Vehicle> getAvailableVehiclesByTypeAndRate(Type type, double minRate, double maxRate) {
        return vehicles.stream()
                .filter(v -> v.getType() == type &&
                        v.getStatus() == Status.active &&
                        v.getDailyRate() >= minRate &&
                        v.getDailyRate() <= maxRate)
                .collect(Collectors.toList());
    }

    // Update vehicle status
    public boolean updateVehicleStatus(int vehicleId, Status status) {
        Vehicle vehicle = getVehicleById(vehicleId);
        if (vehicle != null) {
            vehicle.setStatus(status);
            System.out.println("Vehicle " + vehicleId + " status updated to: " + status);
            return true;
        }
        System.out.println("Vehicle not found: " + vehicleId);
        return false;
    }

    // Print all vehicles
    public void printAllVehicles() {
        System.out.println("\n--- All Vehicles in Inventory ---");
        if (vehicles.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        vehicles.forEach(v -> System.out.println("ID: " + v.getVehicleId() +
                ", Reg: " + v.getRegistrationNo() +
                ", Name: " + v.getName() +
                ", Model: " + v.getModel() +
                ", Type: " + v.getType() +
                ", Rate: " + v.getDailyRate() +
                ", Status: " + v.getStatus()));
    }

    // Print available vehicles
    public void printAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        List<Vehicle> availableVehicles = vehicles.stream()
                .filter(v -> v.getStatus() == Status.active)
                .collect(Collectors.toList());

        if (availableVehicles.isEmpty()) {
            System.out.println("No vehicles are currently available.");
            return;
        }

        availableVehicles.forEach(v -> System.out.println("ID: " + v.getVehicleId() +
                ", Reg: " + v.getRegistrationNo() +
                ", Name: " + v.getName() +
                ", Model: " + v.getModel() +
                ", Type: " + v.getType() +
                ", Rate: " + v.getDailyRate()));
    }
}

class CarInventory extends VehicleInventory {

    public CarInventory() {
        super();
    }

}

class BikeInventory extends VehicleInventory {

    public BikeInventory() {
        super();
    }

}
