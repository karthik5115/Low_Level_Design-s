import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Show {
    private final Movie movie;
    private final LocalDate showDate;
    private final LocalTime startTime;

    private final Map<Integer, SeatStatus> seatStatusMap = new HashMap<>();
    private final Map<Integer, ReentrantLock> seatLocks = new HashMap<>();

    public Show(Movie movie, Screen screen, LocalDate date, LocalTime time) {
        this.movie = movie;
        this.showDate = date;
        this.startTime = time;

        if (screen != null && screen.getSeats() != null) {
            for (Seat seat : screen.getSeats()) {
                seatStatusMap.put(seat.getSeatId(), SeatStatus.AVAILABLE);
                seatLocks.put(seat.getSeatId(), new ReentrantLock());
            }
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Map<Integer, SeatStatus> getSeatStatusMap() {
        return Collections.unmodifiableMap(seatStatusMap);
    }

    public boolean lockSeats(List<Integer> seatIds) {
        if (seatIds == null || seatIds.isEmpty()) {
            return false;
        }

        List<Integer> sorted = new ArrayList<>(seatIds);
        // Sorting seat IDs in global order to avoid deadlock scenarios
        Collections.sort(sorted);

        List<ReentrantLock> acquiredLocks = new ArrayList<>();

        try {
            // Phase 1: acquire all locks
            for (int seatId : sorted) {
                ReentrantLock lock = seatLocks.get(seatId);
                if (lock == null) {
                    return false;
                }
                lock.lock();
                acquiredLocks.add(lock);
            }

            // Phase 2: validate availability
            for (int seatId : sorted) {
                if (seatStatusMap.get(seatId) != SeatStatus.AVAILABLE) {
                    return false;
                }
            }

            // Phase 3: mark LOCKED
            for (int seatId : sorted) {
                seatStatusMap.put(seatId, SeatStatus.LOCKED);
            }

            return true;

        } finally {
            // Phase 4: release locks
            for (ReentrantLock lock : acquiredLocks) {
                lock.unlock();
            }
        }
    }

    public void confirmSeats(List<Integer> seatIds) {
        if (seatIds == null) return;
        for (int seatId : seatIds) {
            seatStatusMap.put(seatId, SeatStatus.BOOKED);
        }
    }

    public void releaseSeats(List<Integer> seatIds) {
        if (seatIds == null) return;
        for (int seatId : seatIds) {
            seatStatusMap.put(seatId, SeatStatus.AVAILABLE);
        }
    }
}