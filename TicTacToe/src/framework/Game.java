package framework;

/**
 * 
 * @author Çağatay Yıldız
 * 
 */
public class Game {

	public int WAIT_BEFORE_CLOSE = 5000;
	public char EMPTY_POS_MARK = '-';
	public int BOARD_WIDTH = 500;
	public int BOARD_HEIGHT = 500;

	public char[][] boardArray;
	public Player player1;
	public Player player2;
	public Board board;
	public int round;

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

	public boolean updateBoardArray(Player player, int posX, int posY) {
		if (boardArray[posX][posY] == EMPTY_POS_MARK) {
			boardArray[posX][posY] = player.mark;
			return true;
		} else {
			return false;
		}
	}

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

	public Player switchPlayer(Player current) {
		if (current == player1) {
			return player2;
		}
		return player1;
	}

	public void killTheGame(int milisecs) {
		try {
			Thread.sleep(milisecs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}