class Vehicle {
    int vehicleId;
    String RegistrationNo;
    String Name;
    String Model;
    String YearOfManufacture;
    String color;
    double dailyRate;
    Type type;
    Status status;

    public Vehicle(int vehicleId, String registrationNo, String name, String model, String yearOfManufacture,
            String color, double dailyRate, Type type, Status status) {
        this.vehicleId = vehicleId;
        RegistrationNo = registrationNo;
        Name = name;
        Model = model;
        YearOfManufacture = yearOfManufacture;
        this.color = color;
        this.dailyRate = dailyRate;
        this.type = type;
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getRegistrationNo() {
        return RegistrationNo;
    }

    public String getName() {
        return Name;
    }

    public String getModel() {
        return Model;
    }

    public String getYearOfManufacture() {
        return YearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

class Bike extends Vehicle {

    Bike(int vehicleId, String registrationNo, String name, String model, String yearOfManufacture,
            String color, double dailyRate, Status status) {
        super(vehicleId, registrationNo, name, model, yearOfManufacture, color, dailyRate, Type.Bike, status);
    }

}

class Car extends Vehicle {

    Car(int vehicleId, String registrationNo, String name, String model, String yearOfManufacture,
            String color, double dailyRate, Status status) {
        super(vehicleId, registrationNo, name, model, yearOfManufacture, color, dailyRate, Type.Car, status);
    }

}
