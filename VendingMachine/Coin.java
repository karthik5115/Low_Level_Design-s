public enum Coin {
    ONE(1),
    FIVE(5),
    TEN(10),
    HUNDRED(100);

    private final int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "₹" + value;
    }
}
