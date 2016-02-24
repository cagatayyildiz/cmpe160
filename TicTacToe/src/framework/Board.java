package framework;

import java.awt.Color;

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
	
	public Board(int width, int height, Player firstPlayer) {
		this.width = width;
		this.height = height;
		frame.getContentPane().add(canvas);
		frame.setSize(width, height+80);
		frame.setVisible(true);
		
		roundLabel = new GLabel("", 5,this.height + 20);
		roundLabel.setFont("SansSerif-bold-20");
		currentPlayerLabel = new GLabel("", 5, this.height + 40);
		currentPlayerLabel.setFont("SansSerif-bold-20");
		canvas.add(roundLabel);
		canvas.add(currentPlayerLabel);

		rects[0] = new GRect(0, 0, 2, height);
		rects[1] = new GRect(width/3, 0, 2, height);
		rects[2] = new GRect(2*width/3, 0, 2, height);
		rects[3] = new GRect(width-3, 0, 2, height);
		
		rects[4] = new GRect(0, 0, width, 2);
		rects[6] = new GRect(0, height/3, width, 2);
		rects[5] = new GRect(0, 2*height/3, width, 2);
		rects[7] = new GRect(0, height, width, 2);
		for (GRect rect : rects) {
			rect.setFilled(true);
			rect.setColor(Color.BLACK);
			canvas.add(rect);
		}
		updateGameInfo(firstPlayer,1);
	}
	
	public void updateCanvas(int posX, int posY, char mark, Player player, int round) {
		int markWidth = width/15;
		int markHeight = 4*height/15;
		int canvasPosX = posY*width/3 + markWidth;
		int canvasPosY = posX*height/3 + markHeight;
		
		GLabel label = new GLabel(mark + "", canvasPosX, canvasPosY);
		label.setFont("SansSerif-bold-150");
		canvas.add(label);
		
		updateGameInfo(player,round);
	}
	
	public void updateGameInfo(Player player, int round) {
		roundLabel.setLabel("Round: " + round);
		currentPlayerLabel.setLabel("It's " + player.name + "'s turn!");
	}
}
