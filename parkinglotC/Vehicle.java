public class Vehicle {
    private VehicleType vehicleType;
    private String vehicleNO;

    Vehicle(VehicleType vehicleType, String vehicleNO) {
        this.vehicleType = vehicleType;
        this.vehicleNO = vehicleNO;
    }

    VehicleType getVehicleType() {
        return this.vehicleType;
    }

    String getVehicleNO() {
        return this.vehicleNO;
    }
}
