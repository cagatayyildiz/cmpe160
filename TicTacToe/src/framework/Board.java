package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Board {
	
	public static char EMPTY_POS_MARK = '-';
	private final int BOARD_WIDTH = 500;
	private final int BOARD_HEIGHT = 500;
	
	char [][] board;
	Player player1;
	Player player2;
	TTTFrame frame;
	
	public Board(Player player1, Player player2){
		this.board = new char[3][3];
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				board[i][j] = EMPTY_POS_MARK;
			}
		}
		this.player1 = player1;
		this.player2 = player2;
		frame = new TTTFrame(BOARD_WIDTH, BOARD_HEIGHT);
	}
	
	public void runGame(Player startingPlayer) {
		Player player = startingPlayer;
		for (int r=0; r<9; r++) {
			if (gameOver()) {
				break;
			}
			System.out.print("Hey " + player.name + ", it's your turn! Enter your move: ");
			System.out.println();
			while (true) {
				printBoard();
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    String move;
				try {
					move = bufferRead.readLine();
					System.out.println(move);
					String[] pos = move.split(",");
					int posX = Integer.parseInt(pos[0]);
					int posY = Integer.parseInt(pos[1]);
					if (posX < 3 && posY < 3 && posX > -1 && posY > -1) {
						if (board[posX][posY] == EMPTY_POS_MARK) {
							board[posX][posY] = player.mark;
							frame.updateCanvas(posX,posY,player.mark);
							player = getOtherPlayer(player);
							break;
						}
						else {
							System.out.print("The position is full! Why don't you try again? Enter your move: ");
							System.out.println();
						}
					}
					else {
						System.out.print("C'mon, enter values less than 3: ");
						System.out.println();
					}
				} catch (Exception e) {
					System.out.println("Input format is incorrect!");
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean makeMove(Player player, int posX, int posY) {
		if (board[posX][posY] == EMPTY_POS_MARK) {
			board[posX][posY] = player.mark;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean gameOver() {
		for (int i=0; i<3; i++) {
			if (board[i][0] != EMPTY_POS_MARK && board[i][0] == board[i][1] &&  board[i][1] == board[i][2]) {
				announceEnding(board[i][0]);
				return true;
			}
			if (board[0][i] != EMPTY_POS_MARK && board[0][i] == board[1][i] &&  board[1][i] == board[2][i]) {
				announceEnding(board[0][i]);
				return true;
			}
		}
		return false;
	}
	
	public void printBoard() {
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	private void announceEnding(char mark) {
		Player winner;
		if (player1.mark == mark) {
			winner = player1;
		}
		else {
			winner = player2;
		}
		System.out.println("\n*** Congratulations " + winner.name + "! You have won the game! ***");
	}
	
	private Player getOtherPlayer(Player current) {
		if (current==player1) {
			return player2;
		}
		return player1;
	}
}
