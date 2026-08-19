import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {

            System.out.print("Enter board size (e.g. 3): ");
            int size = Integer.parseInt(scan.nextLine().trim());

            System.out.print("Enter Player 1 Name: ");
            String name1 = scan.nextLine();
            Player player1 = new Player(name1, new X());

            System.out.print("Enter Player 2 Name: ");
            String name2 = scan.nextLine();
            Player player2 = new Player(name2, new O());

            List<Player> playerList = new ArrayList<>();
            playerList.add(player1);
            playerList.add(player2);

            Board board = new Board(size);
            Game game = new Game(board, playerList);
            game.startGame();
        }
    }
}
