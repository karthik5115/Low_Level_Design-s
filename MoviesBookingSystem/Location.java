import java.util.Objects;

public class Location {
    private String city;
    private String state;
    private String country;

    public Location(City city) {
        this.city = city.name();
        this.state = "";
        this.country = "India";
    }

    public Location(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(city != null ? city.toUpperCase() : null,
                location.city != null ? location.city.toUpperCase() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city != null ? city.toUpperCase() : "");
    }
}