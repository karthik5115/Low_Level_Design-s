import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<Location, List<Movie>> moviesByLocation;
    Map<Integer, Movie> moviesById;

    public MovieController(Map<Location, List<Movie>> moviesByLocation, Map<Integer, Movie> moviesById) {
        this.moviesByLocation = moviesByLocation;
        this.moviesById = moviesById;
    }

    public List<Movie> getMoviesByLocation(Location location) {
        return moviesByLocation.get(location);
    }

    public Movie getMovieById(int movieId) {
        return moviesById.get(movieId);
    }

    public void addMovie(Movie movie, Location location) {
        moviesByLocation.computeIfAbsent(location, l -> new ArrayList<>()).add(movie);
        moviesById.put(movie.getMovieId(), movie);
    }

    public void removeMovie(Movie movie, Location location) {
        moviesByLocation.get(location).remove(movie);
        moviesById.remove(movie.getMovieId());
    }

    public void updateMovie(Movie movie, Location location) {
        moviesByLocation.get(location).remove(movie);
        moviesByLocation.get(location).add(movie);
    }
}