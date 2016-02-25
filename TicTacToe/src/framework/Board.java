package framework;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Board {

	int width;
	int height;
	JFrame frame = new JFrame("Tic-Tac-Toe");
	GCanvas canvas = new GCanvas();
	GRect rects[] = new GRect[8];
	GLabel roundLabel = new GLabel("");
	GLabel currentPlayerLabel = new GLabel("");
	int mouseX = -1;
	int mouseY = -1;
	boolean waitforInput = true;

	public Board(int width_, int height_, String firstPlayerName) {
		width = width_;
		height = height_;
		frame.getContentPane().add(canvas);
		canvas.addMouseListener(new CustomMouseListener());
		frame.setSize(width, height + 80);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		roundLabel = new GLabel("", 5, this.height + 20);
		roundLabel.setFont("SansSerif-bold-20");
		currentPlayerLabel = new GLabel("", 5, this.height + 40);
		currentPlayerLabel.setFont("SansSerif-bold-20");
		canvas.add(roundLabel);
		canvas.add(currentPlayerLabel);

		rects[0] = new GRect(0, 0, 2, height);
		rects[1] = new GRect(width / 3, 0, 2, height);
		rects[2] = new GRect(2 * width / 3, 0, 2, height);
		rects[3] = new GRect(width - 3, 0, 2, height);

		rects[4] = new GRect(0, 0, width, 2);
		rects[6] = new GRect(0, height / 3, width, 2);
		rects[5] = new GRect(0, 2 * height / 3, width, 2);
		rects[7] = new GRect(0, height, width, 2);
		for (GRect rect : rects) {
			rect.setFilled(true);
			rect.setColor(Color.BLACK);
			canvas.add(rect);
		}
		updateGameInfo(firstPlayerName, 1);
	}

	public void updateCanvas(int posX, int posY, char mark, String currentPlayerName, int round) {
		int markWidth = width / 15;
		int markHeight = 4 * height / 15;
		int canvasPosX = posY * width / 3 + markWidth;
		int canvasPosY = posX * height / 3 + markHeight;

		GLabel label = new GLabel(mark + "", canvasPosX, canvasPosY);
		label.setFont("SansSerif-bold-150");
		canvas.add(label);

		updateGameInfo(currentPlayerName, round);
	}

	public void updateGameInfo(String currentPlayerName, int round) {
		roundLabel.setLabel("Round: " + round);
		currentPlayerLabel.setLabel("It's " + currentPlayerName + "'s turn!");
		waitforInput = true;
	}
	
	public void announceTie(int round) {
		roundLabel.setLabel("Round: " + (round-1));
		currentPlayerLabel.setLabel("This is a tie, no winner:(");
		waitforInput = true;
	}

	public void announceWinner(String winner, int round) {
		roundLabel.setLabel("Round: " + (round-2));
		currentPlayerLabel.setLabel(winner + " has won the game!");
		waitforInput = true;
	}

	class CustomMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			// System.out.println(e.getX() + ", " + e.getY() + " is clicked.");
			if (e.getX()<width && e.getY()<height) {
				mouseY = e.getX()/(width/3);
				mouseX = e.getY()/(height/3);
				waitforInput = false;
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

}
