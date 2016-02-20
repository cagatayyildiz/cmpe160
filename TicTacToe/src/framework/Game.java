package framework;

public class Game {
	public static void main(String[] args) {

		Player player1 = new Player("Cagatay", 'C');
		Player player2 = new Player("Hande", 'H');

		Board board = new Board(player1, player2);
		board.runGame(player1); // player1 plays the game
	}
}
