package framework;

import java.awt.Color;

import javax.swing.JFrame;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class TTTFrame {

	int width;
	int height;
	JFrame frame = new JFrame("Tic-Tac-Toe");
	GCanvas canvas = new GCanvas();
	GRect rects[] = new GRect[4];
	
	public TTTFrame(int width, int height) {
		this.width = width;
		this.height = height;
		frame.getContentPane().add(canvas);
		frame.setSize(width, height);
		frame.show();
		
		rects[0] = new GRect(width/3, 0, 2, height);
		rects[1] = new GRect(2*width/3, 0, 2, height);
		rects[2] = new GRect(0, height/3, width, 2);
		rects[3] = new GRect(0, 2*height/3, width, 2);
		for (GRect rect : rects) {
			rect.setFilled(true);
			rect.setColor(Color.BLACK);
			canvas.add(rect);
		}
	}
	public void updateCanvas(int posX, int posY, char mark) {
		int markWidth = width/15;
		int markHeight = 4*height/15;
		int canvasPosX = posY*width/3 + markWidth;
		int canvasPosY = posX*height/3 + markHeight;
		
		GLabel label = new GLabel(mark + "", canvasPosX, canvasPosY);
		label.setFont("SansSerif-bold-150");
		canvas.add(label);
	}
}
