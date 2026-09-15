import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Theatre {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private final int theatreId;
    private final String theatreName;
    private final Location location;
    private final City city;
    private final List<Screen> screens;

    public Theatre(String theatreName, City city, List<Screen> screens) {
        this.theatreId = ID_GENERATOR.getAndIncrement();
        this.theatreName = theatreName;
        this.city = city;
        this.location = new Location(city);
        this.screens = screens != null ? screens : new ArrayList<>();
    }

    public Theatre(int theatreId, String theatreName, Location location, List<Screen> screens) {
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.location = location;
        City matchedCity = null;
        if (location != null && location.getCity() != null) {
            try {
                matchedCity = City.valueOf(location.getCity().toUpperCase());
            } catch (Exception ignored) {
            }
        }
        this.city = matchedCity;
        this.screens = screens != null ? screens : new ArrayList<>();
    }

    public int getTheatreId() {
        return theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getName() {
        return theatreName;
    }

    public Location getLocation() {
        return location;
    }

    public City getCity() {
        return city;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    // Alias for existing code using typo
    public List<Screen> getSreens() {
        return screens;
    }
}
