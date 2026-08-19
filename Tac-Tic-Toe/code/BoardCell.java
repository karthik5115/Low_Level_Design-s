public class BoardCell {
    private Symbol symbol;

    public BoardCell() {
        this.symbol = null;
    }

    public boolean isEmpty() {
        return symbol == null;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
