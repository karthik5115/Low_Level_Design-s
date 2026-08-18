import java.util.Date;

public class Ticket {
    private Date inTime;
    private Vehicle vehicle;
    private ParkingSlot parkingSlot;
    private int ticketId;

    Ticket(Date inTime, Vehicle vehicle, ParkingSlot parkingSlot) {
        this.inTime = inTime;
        this.vehicle = vehicle;
        this.parkingSlot = parkingSlot;
        this.ticketId = (int) (Math.random() * 1000);
    }

    public int getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Date getInTime() {
        return this.inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSlot getParkingSlot() {
        return this.parkingSlot;
    }

    public void setParkingSlot(ParkingSlot parkingSlot) {
        this.parkingSlot = parkingSlot;
    }
}
