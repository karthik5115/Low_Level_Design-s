import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private String name;
    private List<EntranceGate> entranceGates;
    private List<ExitGate> exitGates;
    private PricingStrategy defaultPricingStrategy;
    private findParkingSlotStrategy defaultSlotStrategy;

    ParkingLot(String name, int twoWheelerSlots, int fourWheelerSlots) {
        this.name = name;
        this.entranceGates = new ArrayList<>();
        this.exitGates = new ArrayList<>();
        this.defaultPricingStrategy = new HourlyRateStrategy(50);
        this.defaultSlotStrategy = new getNearestSlot();

        List<ParkingSlot> twoWheelerPool = new ArrayList<>();
        for (int i = 1; i <= twoWheelerSlots; i++) {
            twoWheelerPool.add(new TwoWheeler(i));
        }

        List<ParkingSlot> fourWheelerPool = new ArrayList<>();
        for (int i = 1; i <= fourWheelerSlots; i++) {
            fourWheelerPool.add(new FourWheeler(i));
        }

        ParkingManagerFactory.register(
            VehicleType.TwoWheeler,
            new TwoWheelerParkingSlotManager(twoWheelerPool, defaultSlotStrategy)
        );
        ParkingManagerFactory.register(
            VehicleType.FourWheeler,
            new FourWheelerParkingSlotManager(fourWheelerPool, defaultSlotStrategy)
        );

        entranceGates.add(new EntranceGate());
        exitGates.add(new ExitGate());

        System.out.println("Parking Lot '" + name + "' initialized.");
        System.out.println("   Two-Wheeler slots : " + twoWheelerSlots);
        System.out.println("   Four-Wheeler slots: " + fourWheelerSlots);
    }

    public Ticket vehicleEntry(Vehicle vehicle) {
        EntranceGate gate = entranceGates.get(0);

        ParkingSlot slot = gate.getParkingSlot(vehicle);
        if (slot == null) {
            System.out.println("No slot available for " + vehicle.getVehicleType());
            return null;
        }

        gate.parkVehicle(vehicle, slot);
        Ticket ticket = gate.issueTicket(vehicle, slot);

        System.out.println("Vehicle " + vehicle.getVehicleNO()
                + " parked at slot #" + slot.getSlotId()
                + " | Ticket ID: " + ticket.getTicketId());
        return ticket;
    }

    public double vehicleExit(Ticket ticket) {
        return vehicleExit(ticket, defaultPricingStrategy);
    }

    public double vehicleExit(Ticket ticket, PricingStrategy pricingStrategy) {
        ExitGate gate = exitGates.get(0);

        double cost = gate.processExit(ticket, pricingStrategy);

        System.out.println("Vehicle " + ticket.getVehicle().getVehicleNO()
                + " exited. Slot #" + ticket.getParkingSlot().getSlotId()
                + " is now free.");
        System.out.printf("   Cost charged: Rs %.2f%n", cost);
        return cost;
    }

    public void setPricingStrategy(PricingStrategy strategy) {
        this.defaultPricingStrategy = strategy;
    }

    public void setSlotStrategy(VehicleType type, findParkingSlotStrategy strategy) {
        ParkingSlotManager existing = ParkingManagerFactory.getManager(type);
        if (existing instanceof BaseParkingSlotManager) {
            ((BaseParkingSlotManager) existing).fpsStrategy = strategy;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ParkingLot lot = new ParkingLot("City Center Parking", 5, 3);
        System.out.println();

        Vehicle bike1 = new Vehicle(VehicleType.TwoWheeler,  "KA-01-HH-1234");
        Vehicle bike2 = new Vehicle(VehicleType.TwoWheeler,  "KA-02-MN-5678");
        Vehicle car1  = new Vehicle(VehicleType.FourWheeler, "MH-12-AB-9999");
        Vehicle car2  = new Vehicle(VehicleType.FourWheeler, "DL-03-CD-0001");

        Ticket t1 = lot.vehicleEntry(bike1);
        Ticket t2 = lot.vehicleEntry(car1);
        Ticket t3 = lot.vehicleEntry(bike2);
        Ticket t4 = lot.vehicleEntry(car2);
        System.out.println();

        Thread.sleep(2000);

        lot.vehicleExit(t1);
        System.out.println();

        lot.vehicleExit(t2, new MinBasedStrategy(2.0));
        System.out.println();

        lot.setPricingStrategy(new MinBasedStrategy(1.5));
        lot.vehicleExit(t3);
        lot.vehicleExit(t4);
    }
}
