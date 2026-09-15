public class Seat {
    private final int seatId;
    private final SeatCategory seatCategory;

    public Seat(int seatId, SeatCategory seatCategory) {
        this.seatId = seatId;
        this.seatCategory = seatCategory;
    }

    public Seat(int seatId, SeatType seatType) {
        this.seatId = seatId;
        this.seatCategory = SeatCategory.valueOf(seatType.name());
    }

    public int getSeatId() {
        return seatId;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public SeatType getSeatType() {
        try {
            return SeatType.valueOf(seatCategory.name());
        } catch (Exception e) {
            return SeatType.CLASSIC;
        }
    }
}

enum SeatType {
    CLASSIC,
    PREMIUM,
    LUXURY,
    SILVER,
    GOLD,
    PLATINUM
}
