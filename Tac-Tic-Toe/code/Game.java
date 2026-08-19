import java.util.*;

public class Game {
    private Board board;
    private Queue<Player> players;

    public Game(Board board, List<Player> playerList) {
        this.board = board;
        this.players = new LinkedList<>(playerList);
    }

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        boolean win = false;

        while (!win) {
            Player player = players.poll();

            if (player == null) break;

            System.out.println("\n" + player.getName() + "'s turn [" + player.getSymbol().get() + "]. Choose a slot (0 to " + (board.getSize() * board.getSize() - 1) + "):");
            board.printBoard();

            int slot = -1;
            boolean placed = false;

            while (!placed) {
                slot = scan.nextInt();
                placed = board.boardCellFill(player, slot);
                if (!placed) {
                    System.out.println("Invalid slot or already occupied. Try again:");
                }
            }

            if (board.isWin(slot)) {
                board.printBoard();
                System.out.println("Winner: " + player.getName() + " [" + player.getSymbol().get() + "]");
                win = true;
            } else if (board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw! Clearing the board.");
                board.clear();
                break;
            } else {
                players.offer(player);
            }
        }

        scan.close();
    }
}
