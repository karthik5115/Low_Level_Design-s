import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingController {

    private final Map<UUID, Booking> bookings = new HashMap<>();

    public Booking createBooking(User user, Show show, List<Integer> seats) {
        return book(user, show, seats);
    }

    public Booking book(User user, Show show, List<Integer> seats) {

        if (!show.lockSeats(seats)) {
            throw new RuntimeException("Seat unavailable");
        }

        // Simulated payment flow here, we can invoke pay method of Payment
        Payment payment = new Payment(PaymentStatus.SUCCESS, seats.size() * 200.0, PaymentMethod.UPI);

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            show.confirmSeats(seats);
            Booking booking = new Booking(user, show, seats, payment);
            bookings.put(booking.getBookingId(), booking);
            return booking;
        } else {
            show.releaseSeats(seats);
            throw new RuntimeException("Payment failed");
        }
    }

    public Booking getBooking(UUID bookingId) {
        return bookings.get(bookingId);
    }

    public List<Booking> getBookingsForUser(User user) {
        return bookings.values()
                .stream()
                .filter(b -> b.getUser() != null && b.getUser().equals(user))
                .toList();
    }
}
