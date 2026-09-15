import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen {
    private final int screenId;
    private String screenName;
    private int totalSeats;
    private List<Seat> seats = new ArrayList<>();
    private final Map<LocalDate, List<Show>> showsByLocalDate = new HashMap<>();

    public Screen(int screenId, List<Seat> seats) {
        this.screenId = screenId;
        this.screenName = "Screen " + screenId;
        this.seats = seats != null ? seats : new ArrayList<>();
        this.totalSeats = this.seats.size();
    }

    public Screen(int screenId, String screenName, int totalSeats, Map<Date, List<Show>> legacyShows) {
        this.screenId = screenId;
        this.screenName = screenName;
        this.totalSeats = totalSeats;
        if (legacyShows != null) {
            legacyShows.forEach((date, shows) -> {
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                showsByLocalDate.put(localDate, new ArrayList<>(shows));
            });
        }
    }

    public Screen(int screenId, String screenName, int totalSeats, List<Seat> seats, Map<Date, List<Show>> legacyShows) {
        this(screenId, screenName, totalSeats, legacyShows);
        this.seats = seats != null ? seats : new ArrayList<>();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void addShow(Show show) {
        if (show != null && show.getShowDate() != null) {
            showsByLocalDate.computeIfAbsent(show.getShowDate(), d -> new ArrayList<>()).add(show);
        }
    }

    public void addShow(Show show, Date date) {
        if (date != null && show != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            showsByLocalDate.computeIfAbsent(localDate, d -> new ArrayList<>()).add(show);
        }
    }

    public List<Show> getShows(LocalDate date) {
        return showsByLocalDate.getOrDefault(date, Collections.emptyList());
    }

    public List<Show> getShows(Date date) {
        if (date == null) return Collections.emptyList();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return getShows(localDate);
    }

    public void removeShow(Show show, Date date) {
        if (date != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            List<Show> shows = showsByLocalDate.get(localDate);
            if (shows != null) {
                shows.remove(show);
            }
        }
    }

    public void updateShow(Show show, Date date) {
        removeShow(show, date);
        addShow(show, date);
    }

    public int getScreenId() {
        return screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }
}
