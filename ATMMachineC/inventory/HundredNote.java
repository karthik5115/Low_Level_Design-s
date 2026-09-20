package inventory;

public class HundredNote implements Note {
    private static final int VALUE = 100;

    @Override
    public int getValue() {
        return VALUE;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Note)) return false;
        Note other = (Note) obj;
        return this.getValue() == other.getValue();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(VALUE);
    }

    @Override
    public String toString() {
        return "Rs. 100 Note";
    }
}
