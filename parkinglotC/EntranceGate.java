import java.util.Date;

public class EntranceGate {
    private int gateId;

    ParkingSlot getParkingSlot(Vehicle vehicle) {
        return ParkingManagerFactory.getManager(vehicle.getVehicleType()).getParkingSlot();
    }

    Ticket issueTicket(Vehicle vehicle, ParkingSlot parkingSlot) {
        Date inTime = new Date();
        Ticket ticket = new Ticket(inTime, vehicle, parkingSlot);
        return ticket;
    }

    void parkVehicle(Vehicle vehicle, ParkingSlot slot) {
        slot.parkVehicle(vehicle);
    }
}
