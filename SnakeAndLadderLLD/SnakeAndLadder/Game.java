import java.util.*;

class Game {
    private Queue<Player> players;
    private ArrayList<Mover> snakes;
    private ArrayList<Mover> ladders;
    private Dice dice;
    private Board board;

    public Game() {
        players = new LinkedList<>();
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        dice = new SixSidedDice(1);
        board = new Board(10);
        Mover snake1 = new Mover(14, 7);
        Mover snake2 = new Mover(17, 4);
        Mover snake3 = new Mover(19, 8);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);
        Mover ladder1 = new Mover(5, 25);
        Mover ladder2 = new Mover(10, 29);
        Mover ladder3 = new Mover(3, 22);
        ladders.add(ladder1);
        ladders.add(ladder2);
        ladders.add(ladder3);
    }

    public void startGame() {
        System.out.println("let's start the game!");
        System.out.println("Enter number of players:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter name of player " + (i + 1) + ":");
            String name = sc.nextLine();
            System.out.println("Enter age of player " + (i + 1) + ":");
            int age = sc.nextInt();
            sc.nextLine();
            Player player = new Player(name, age);
            players.offer(player);
        }
        System.out.println("Lets start the game!");
        while (players.size() > 1) {
            Player currentPlayer = players.poll();
            System.out.println("Current Player: " + currentPlayer.getName() + "'s turn to roll dice");
            System.out.println("Press Enter to roll the dice...");
            sc.nextLine();
            int diceValue = dice.roll();
            System.out.println("Dice rolled: " + diceValue);
            int newPosition = currentPlayer.getPosition() + diceValue;
            if (newPosition > board.getSize()) {
                newPosition = currentPlayer.getPosition();
            }
            currentPlayer.setPosition(newPosition);
            System.out.println("Player " + currentPlayer.getName() + " moved to position " + newPosition);
            for (Mover snake : snakes) {
                if (snake.getStartPosition() == newPosition) {
                    currentPlayer.setPosition(snake.getEndPosition());
                    System.out.println("Player " + currentPlayer.getName() + " got bitten by a snake! Moved to "
                            + snake.getEndPosition());
                }
            }
            for (Mover ladder : ladders) {
                if (ladder.getStartPosition() == newPosition) {
                    currentPlayer.setPosition(ladder.getEndPosition());
                    System.out.println("Player " + currentPlayer.getName() + " climbed a ladder! Moved to "
                            + ladder.getEndPosition());
                }
            }
            if (currentPlayer.getPosition() == board.getSize()) {
                System.out.println("Player " + currentPlayer.getName() + " wins!");
            } else {
                players.offer(currentPlayer);
            }
        }
    }
}
