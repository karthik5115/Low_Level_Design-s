public abstract class ParkingSlot {
    private boolean Empty;
    private int slotId;
    private Vehicle vehicle;

    ParkingSlot() {
        this.Empty = true;
    }

    public boolean isAvailable() {
        return Empty;
    }

    public double getPrice() {
        return 0;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.Empty = false;
    }

    public void unParkVehicle() {
        this.vehicle = null;
        this.Empty = true;
    }
}

class TwoWheeler extends ParkingSlot {
    TwoWheeler(int slotId) {
        super.setSlotId(slotId);
    }

    public double getPrice() {
        return 20;
    }
}

class FourWheeler extends ParkingSlot {
    FourWheeler(int slotId) {
        super.setSlotId(slotId);
    }

    public double getPrice() {
        return 40;
    }
}
