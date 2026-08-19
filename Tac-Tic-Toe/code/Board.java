public class Board {
    private int size;
    private BoardCell[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new BoardCell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new BoardCell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean boardCellFill(Player player, int slot) {
        if (slot < 0 || slot >= size * size) {
            return false;
        }
        int row = slot / size;
        int col = slot % size;
        if (!board[row][col].isEmpty()) {
            return false;
        }
        board[row][col].setSymbol(player.getSymbol());
        return true;
    }

    public void boardCellUnfill(int slot) {
        if (slot < 0 || slot >= size * size) return;
        int row = slot / size;
        int col = slot % size;
        board[row][col].setSymbol(null);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].setSymbol(null);
            }
        }
    }

    public boolean isWin(int slot) {
        int row = slot / size;
        int col = slot % size;
        Symbol sym = board[row][col].getSymbol();
        if (sym == null) return false;

        return rowCheck(row, sym)
                || columnCheck(col, sym)
                || diagonalCheck(sym);
    }

    private boolean rowCheck(int row, Symbol sym) {
        for (int j = 0; j < size; j++) {
            if (board[row][j].isEmpty() || board[row][j].getSymbol().get() != sym.get()) {
                return false;
            }
        }
        return true;
    }

    private boolean columnCheck(int col, Symbol sym) {
        for (int i = 0; i < size; i++) {
            if (board[i][col].isEmpty() || board[i][col].getSymbol().get() != sym.get()) {
                return false;
            }
        }
        return true;
    }

    private boolean diagonalCheck(Symbol sym) {
        boolean mainDiag = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i].isEmpty() || board[i][i].getSymbol().get() != sym.get()) {
                mainDiag = false;
                break;
            }
        }
        if (mainDiag) return true;

        boolean antiDiag = true;
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i].isEmpty() || board[i][size - 1 - i].getSymbol().get() != sym.get()) {
                antiDiag = false;
                break;
            }
        }
        return antiDiag;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    public void printBoard() {
        String separator = "-".repeat(size * 4 - 1);
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board[i][j].isEmpty()) {
                    System.out.print(" " + board[i][j].getSymbol().get() + " ");
                } else {
                    System.out.print(" " + (i * size + j) + " ");
                }
                if (j < size - 1) System.out.print("|");
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println(separator);
            }
        }
        System.out.println();
    }
}
