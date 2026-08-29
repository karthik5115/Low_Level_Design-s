import java.util.ArrayList;
import java.util.List;

public class User {
    int userId;
    String username;
    String Mobilenumber;
    String licenseid;
    List<Reservation> reservations;

    // constructor
    public User(int userId, String username, String mobilenumber, String licenseid) {
        this.userId = userId;
        this.username = username;
        this.Mobilenumber = mobilenumber;
        this.licenseid = licenseid;
        this.reservations = new ArrayList<>();
    }

    // getter
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getMobilenumber() {
        return Mobilenumber;
    }

    public String getLicenseid() {
        return licenseid;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    // method to add reservation
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // method to remove reservation
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    // method to print all reservations
    public void printAllReservations() {
        System.out.println("\n--- All Reservations for User " + username + " ---");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        reservations.forEach(r -> System.out.println("Reservation ID: " + r.getReservationId() +
                ", Pickup: " + r.getPickupDate() +
                ", Return: " + r.getReturnDate() +
                ", Total Amount: " + r.getTotalAmount()));
    }

    // method to find reservation by ID
    public Reservation getReservationById(int reservationId) {
        return reservations.stream()
                .filter(r -> r.getReservationId() == reservationId)
                .findFirst()
                .orElse(null);
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setMobilenumber(String mobilenumber) {
        this.Mobilenumber = mobilenumber;
    }

    public void setLicenseid(String licenseid) {
        this.licenseid = licenseid;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
