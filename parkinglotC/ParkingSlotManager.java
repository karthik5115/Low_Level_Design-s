import java.util.List;
import java.util.Map;
import java.util.HashMap;

interface ParkingSlotManager {
    void addParkingSlot(ParkingSlot slot);
    void removeParkingSlot(ParkingSlot slot);
    ParkingSlot getParkingSlot();
}

abstract class BaseParkingSlotManager implements ParkingSlotManager {
    protected List<ParkingSlot> slots;
    protected findParkingSlotStrategy fpsStrategy;

    BaseParkingSlotManager(List<ParkingSlot> slots, findParkingSlotStrategy fpsStrategy) {
        this.slots = slots;
        this.fpsStrategy = fpsStrategy;
    }

    public void addParkingSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public void removeParkingSlot(ParkingSlot slot) {
        slots.remove(slot);
    }

    public ParkingSlot getParkingSlot() {
        return fpsStrategy.getSlot(slots);
    }
}

class TwoWheelerParkingSlotManager extends BaseParkingSlotManager {
    TwoWheelerParkingSlotManager(List<ParkingSlot> slots, findParkingSlotStrategy fpsStrategy) {
        super(slots, fpsStrategy);
    }
}

class FourWheelerParkingSlotManager extends BaseParkingSlotManager {
    FourWheelerParkingSlotManager(List<ParkingSlot> slots, findParkingSlotStrategy fpsStrategy) {
        super(slots, fpsStrategy);
    }
}

enum VehicleType {
    TwoWheeler,
    FourWheeler;
}

class ParkingManagerFactory {
    private static final Map<VehicleType, ParkingSlotManager> registry = new HashMap<>();

    public static void register(VehicleType vehicleType, ParkingSlotManager manager) {
        registry.put(vehicleType, manager);
    }

    public static ParkingSlotManager getManager(VehicleType vehicleType) {
        ParkingSlotManager manager = registry.get(vehicleType);
        if (manager == null) {
            throw new IllegalArgumentException("No manager registered for vehicle type: " + vehicleType);
        }
        return manager;
    }
}
