package framework;

/**
 * The implementation of the game engine. All aspects of the game
 * apart from the visual and input-output related ones are implemented
 * in this class. 
 * <p>
 * 
 * The game board is stored as a 3x3 char array. The board size is fixed 
 * and many pieces of the code is implemented based on this. The number of 
 * players is also fixed to 2.
 * <p>
 * 
 * The game ends whenever a row or a cell contains the same mark in each cell.
 * There can be at most 9 rounds in the game. When all cells are filled (which
 * takes exactly 9 rounds) and no player wins the game, the result is announced
 * to be a tie.
 * 
 * @author Cagatay Yildiz
 * 
 * @see Board
 * @see Player
 * 
 */
public class Game {

	/**
	 * The amount of time spent before the frame window is closed
	 */
	public int WAIT_BEFORE_CLOSE = 5000;
	/**
	 * The mark representing empty cells stored within this program
	 */
	public char EMPTY_POS_MARK = '-';
	/**
	 * The default width of the board
	 */
	public int BOARD_WIDTH = 500;
	/**
	 * The default height of the board
	 */
	public int BOARD_HEIGHT = 500;

	/**
	 * A 3-by-3 array storing the marks on the cells. 
	 */
	public char[][] boardArray;
	/**
	 * The first player instance in this game. 
	 * This is the one that starts this game.
	 */
	public Player player1;
	/**
	 * The second player instance
	 */
	public Player player2;
	/**
	 * The board of this game. All visual actions are performed
	 * in the Board class.
	 */
	public Board board;
	/**
	 * The counter storing current round
	 */
	public int round;

	/**
	 * Constructs a new Game. The names of the players in this
	 * game is given in the parameters. Their marks are by default
	 * set to 'X' and 'O'.
	 * 
	 * @param firstPlayer the name of the first player
	 * @param secondPlayer the name of the first player
	 */
	public Game(String firstPlayer, String secondPlayer) {
		boardArray = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boardArray[i][j] = EMPTY_POS_MARK;
			}
		}
		player1 = new Player(firstPlayer, 'X');
		player2 = new Player(secondPlayer, 'O');

		board = new Board(BOARD_WIDTH, BOARD_HEIGHT, player1.name);
		round = 1;
	}

	/**
	 * The main loop in this game. In each round, which has a maximum
	 * value of 9, if the game is not over, a valid input from the user
	 * is being waited. Then the boardArray field and the canvas is updated.
	 * If none of the players win the game after 9 rounds, tie is announced.
	 */
	public void runGame() {
		Player currentPlayer = player1;
		for (int r = 0; r < 9; r++) {
			round++;
			if (isGameOver()) {
				break;
			}
			while (true) {
				if (!board.waitforInput) {
					board.waitforInput = true;
					int posX = board.mouseX;
					int posY = board.mouseY;
					if (posX < 3 && posY < 3 && posX > -1 && posY > -1) {
						if (boardArray[posX][posY] == EMPTY_POS_MARK) {
							char mark = currentPlayer.mark;
							boardArray[posX][posY] = mark;
							currentPlayer = switchPlayer(currentPlayer);
							board.updateCanvas(posX, posY, mark, currentPlayer.name, round);
							break;
						} else {
							board.announceLocationMarked();
						}
					}
				}
				else {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		board.announceTie();
		killTheGame(WAIT_BEFORE_CLOSE);
	}

	/**
	 * Fills a location in a cell with the mark of a player.
	 * Zero based numbering is used in the array and the upper
	 * left corner is denoted by the tuple (0,0). Moving on a row changes
	 * the first value in the tuple whereas it is the second one
	 * when moved on a column
	 * 
	 * @param player the player whose mark is added to the array
	 * @param posX X position of the cell to be modified
	 * @param posY Y position of the cell to be modified
	 * @return true if the location is empty; false, otherwise.
	 */
	public boolean updateBoardArray(Player player, int posX, int posY) {
		if (boardArray[posX][posY] == EMPTY_POS_MARK) {
			boardArray[posX][posY] = player.mark;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks for each row and column in the board that contains a single mark
	 * other than the empty position mark.
	 * 
	 * @return true if all cells in a row or column are of the same mark.
	 * 				false otherwise
	 */
	public boolean isGameOver() {
		for (int i = 0; i < 3; i++) {
			if (boardArray[i][0] != EMPTY_POS_MARK && boardArray[i][0] == boardArray[i][1]
					&& boardArray[i][1] == boardArray[i][2]) {
				announceWinner(boardArray[i][0]);
				return true;
			}
			if (boardArray[0][i] != EMPTY_POS_MARK && boardArray[0][i] == boardArray[1][i]
					&& boardArray[1][i] == boardArray[2][i]) {
				announceWinner(boardArray[0][i]);
				return true;
			}
		}
		return false;
	}

	/**
	 * Looking at the mark of the winner, finds and prints its name
	 * to the canvas and then kills the program
	 * 
	 * @param mark the mark of the winner
	 */
	public void announceWinner(char mark) {
		Player winner;
		if (player1.mark == mark) {
			winner = player1;
		} else {
			winner = player2;
		}
		board.announceWinner(winner.name);
		killTheGame(WAIT_BEFORE_CLOSE);
	}

	/**
	 * Returns the player that is not in the parameter
	 * 
	 * @param current one of the players
	 * @return the other player
	 */
	public Player switchPlayer(Player current) {
		if (current == player1) {
			return player2;
		}
		return player1;
	}

	/**
	 * Waits for milisecs/1000 seconds and then terminates the
	 * program.
	 * 
	 * @param milisecs the amount of waiting duration in miliseconds
	 * 						before the game window is closed.
	 */
	public void killTheGame(int milisecs) {
		try {
			Thread.sleep(milisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}