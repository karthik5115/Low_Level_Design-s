import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String userId;
    private final String name;
    private final List<Booking> bookings;

    public User(String userId, String name) {
        this(userId, name, new ArrayList<>());
    }

    public User(String userId, String name, List<Booking> bookings) {
        this.userId = userId;
        this.name = name;
        this.bookings = bookings != null ? bookings : new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
