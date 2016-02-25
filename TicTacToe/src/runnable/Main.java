package runnable;

import framework.Game;
import framework.Player;

public class Main {
	public static void main(String[] args) {

		Player player1 = new Player("Cagatay", 'C');
		Player player2 = new Player("Hande", 'H');

		Game game = new Game(player1, player2);
		game.runGame(player1); // player1 plays the game
	}
}
