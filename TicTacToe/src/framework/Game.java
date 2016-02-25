package framework;

public class Game {

	public static char EMPTY_POS_MARK = '-';
	private final int BOARD_WIDTH = 500;
	private final int BOARD_HEIGHT = 500;

	char[][] boardArray;
	Player player1;
	Player player2;
	Board board;
	int round;

	public Game(Player firstPlayer, Player secondPlayer) {
		this.boardArray = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boardArray[i][j] = EMPTY_POS_MARK;
			}
		}
		this.player1 = firstPlayer;
		this.player2 = secondPlayer;
		this.board = new Board(BOARD_WIDTH, BOARD_HEIGHT, firstPlayer.name);
		this.round = 1;
	}

	public void runGame(Player startingPlayer) {
		Player currentPlayer = startingPlayer;
		for (int r = 0; r < 9; r++) {
			round++;
			if (isGameOver()) {
				break;
			}
			while (true) {
				if (!board.waitforInput) {
					board.waitforInput = true;
					int posX = this.board.mouseX;
					int posY = this.board.mouseY;
					if (posX < 3 && posY < 3 && posX > -1 && posY > -1) {
						if (boardArray[posX][posY] == EMPTY_POS_MARK) {
							char mark = currentPlayer.mark;
							boardArray[posX][posY] = mark;
							currentPlayer = switchPlayer(currentPlayer);
							board.updateCanvas(posX, posY, mark, currentPlayer.name, round);
							break;
						} else {
							this.board.announceLocationMarked();
						}
					}
				}
				else {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		this.board.announceTie();
		killTheGame(3000);
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

	public void printBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(boardArray[i][j] + "  ");
			}
			System.out.println();
		}
	}

	private void announceWinner(char mark) {
		Player winner;
		if (player1.mark == mark) {
			winner = player1;
		} else {
			winner = player2;
		}
		this.board.announceWinner(winner.name);
		killTheGame(3000);
	}

	private Player switchPlayer(Player current) {
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
