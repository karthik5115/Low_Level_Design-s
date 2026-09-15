import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Movie {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    private final int movieId;
    private final String movieName;
    private final int duration;
    private final String language;

    public Movie(String movieName) {
        this(ID_GENERATOR.getAndIncrement(), movieName, 120, "English");
    }

    public Movie(int movieId, String movieName, int duration, String language) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
        this.language = language;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getName() {
        return movieName;
    }

    public int getDuration() {
        return duration;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId || Objects.equals(movieName, movie.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName != null ? movieName.toUpperCase() : movieId);
    }
}
