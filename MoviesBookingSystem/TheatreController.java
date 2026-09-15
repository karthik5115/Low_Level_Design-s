import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TheatreController {
    private final Map<City, List<Theatre>> theatresByCity = new HashMap<>();
    private final Map<Location, List<Theatre>> theatresByLocation = new HashMap<>();
    private final Map<Integer, Theatre> theatresById = new HashMap<>();

    public TheatreController() {
    }

    public TheatreController(Map<Location, List<Theatre>> theatresByLocation, Map<Integer, Theatre> theatresById) {
        if (theatresByLocation != null) {
            this.theatresByLocation.putAll(theatresByLocation);
            theatresByLocation.values().forEach(list -> {
                if (list != null) {
                    list.forEach(this::addTheatre);
                }
            });
        }
        if (theatresById != null) {
            this.theatresById.putAll(theatresById);
        }
    }

    public void addTheatre(Theatre theatre) {
        if (theatre == null) return;

        theatresById.put(theatre.getTheatreId(), theatre);

        if (theatre.getCity() != null) {
            theatresByCity.computeIfAbsent(theatre.getCity(), c -> new ArrayList<>()).add(theatre);
        }

        if (theatre.getLocation() != null) {
            theatresByLocation.computeIfAbsent(theatre.getLocation(), l -> new ArrayList<>()).add(theatre);
        }
    }

    public void addThreatre(Theatre theatre) {
        addTheatre(theatre);
    }

    public Set<Movie> getMovies(City city, LocalDate date) {
        Set<Movie> movies = new HashSet<>();
        List<Theatre> theatres = theatresByCity.getOrDefault(city, List.of());
        for (Theatre theatre : theatres) {
            for (Screen screen : theatre.getScreens()) {
                for (Show show : screen.getShows(date)) {
                    if (show.getMovie() != null) {
                        movies.add(show.getMovie());
                    }
                }
            }
        }
        return movies;
    }

    public List<Theatre> getTheatres(City city, Movie movie, LocalDate date) {
        List<Theatre> result = new ArrayList<>();
        List<Theatre> theatres = theatresByCity.getOrDefault(city, List.of());
        for (Theatre theatre : theatres) {
            boolean hasMovieShow = false;
            for (Screen screen : theatre.getScreens()) {
                for (Show show : screen.getShows(date)) {
                    if (show.getMovie() != null && show.getMovie().equals(movie)) {
                        hasMovieShow = true;
                        break;
                    }
                }
                if (hasMovieShow) break;
            }
            if (hasMovieShow) {
                result.add(theatre);
            }
        }
        return result;
    }

    public List<Show> getShows(Movie movie, LocalDate date, Theatre theatre) {
        List<Show> result = new ArrayList<>();
        if (theatre == null || theatre.getScreens() == null) return result;

        for (Screen screen : theatre.getScreens()) {
            for (Show show : screen.getShows(date)) {
                if (show.getMovie() != null && show.getMovie().equals(movie)) {
                    result.add(show);
                }
            }
        }
        return result;
    }

    // Legacy date / location methods
    public Set<Movie> getMoviesByLocation(Location location, Date date) {
        if (date == null) return Set.of();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Set<Movie> movies = new HashSet<>();
        List<Theatre> theatres = theatresByLocation.getOrDefault(location, List.of());
        for (Theatre theatre : theatres) {
            for (Screen screen : theatre.getScreens()) {
                for (Show show : screen.getShows(localDate)) {
                    movies.add(show.getMovie());
                }
            }
        }
        return movies;
    }

    public List<Theatre> getTheatresByMovies(Date date, Movie movie, Location location) {
        if (date == null) return List.of();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Theatre> result = new ArrayList<>();
        List<Theatre> theatres = theatresByLocation.getOrDefault(location, List.of());
        for (Theatre theatre : theatres) {
            for (Screen screen : theatre.getScreens()) {
                for (Show show : screen.getShows(localDate)) {
                    if (show.getMovie() != null && show.getMovie().getMovieId() == movie.getMovieId()) {
                        result.add(theatre);
                        break;
                    }
                }
            }
        }
        return result;
    }

    public List<Show> getShowsByMovie(Date date, Movie movie, Location location, Theatre theatre) {
        if (date == null || theatre == null) return List.of();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return getShows(movie, localDate, theatre);
    }

    public List<Theatre> getThreatresByLocation(Location location) {
        return theatresByLocation.getOrDefault(location, List.of());
    }

    public Theatre getThreatreById(int theatreId) {
        return theatresById.get(theatreId);
    }

    public void removeThreatre(Theatre theatre) {
        removeTheatre(theatre);
    }

    public void removeTheatre(Theatre theatre) {
        if (theatre == null) return;
        if (theatre.getLocation() != null && theatresByLocation.containsKey(theatre.getLocation())) {
            theatresByLocation.get(theatre.getLocation()).remove(theatre);
        }
        if (theatre.getCity() != null && theatresByCity.containsKey(theatre.getCity())) {
            theatresByCity.get(theatre.getCity()).remove(theatre);
        }
        theatresById.remove(theatre.getTheatreId());
    }

    public void updateThreatre(Theatre theatre) {
        updateTheatre(theatre);
    }

    public void updateTheatre(Theatre theatre) {
        if (theatre == null) return;
        theatresById.put(theatre.getTheatreId(), theatre);
    }
}
