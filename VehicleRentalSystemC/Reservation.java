import java.sql.Date;

public class Reservation {
    private int reservationId;
    private Date reservationDate;
    private Date pickupDate;
    private Date returnDate;
    private Store store;
    private User user;
    private Vehicle vehicle;
    private double totalAmount;
    private ReservationStatus rstatus;
    private Payment payment;

    public Reservation(Date reservationDate, Date pickupDate, Date returnDate, Store store, User user,
            Vehicle vehicle) {
        this.reservationDate = reservationDate;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.store = store;
        this.user = user;
        this.vehicle = vehicle;
        this.reservationId = (int) (Math.random() * 10000);
        this.rstatus = ReservationStatus.Pending;
        this.totalAmount = calculateRate();
    }

    private double calculateRate() {
        if (pickupDate == null || returnDate == null || vehicle == null) {
            return 0.0;
        }
        long diff = returnDate.getTime() - pickupDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        if (days <= 0) {
            days = 1;
        }
        return days * vehicle.getDailyRate();
    }

    // getter
    public int getReservationId() {
        return reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Store getStore() {
        return store;
    }

    public User getUser() {
        return user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public ReservationStatus getRstatus() {
        return rstatus;
    }

    public Payment getPayment() {
        return payment;
    }

    // setter
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        this.totalAmount = calculateRate();
    }

    public void setRstatus(ReservationStatus rstatus) {
        this.rstatus = rstatus;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
