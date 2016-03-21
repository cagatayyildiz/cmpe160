package visualization;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import acm.graphics.GCanvas;
import acm.graphics.GObject;

public class MyCanvas {

	private JFrame frame;
	private GCanvas canvas;
	
	
	private ArrayList<GObject> objects = new ArrayList<GObject>();

	public MyCanvas(String boardName, int width, int height) {
		setCanvas(boardName, width, height);
	}

	private void setCanvas(String boardName, int width, int height) {
		frame = new JFrame(boardName);
		frame.setSize(width, height);
		canvas = new GCanvas();
		frame.getContentPane().add(canvas);

		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		addKeyBoardListener();
	}

	private void addKeyBoardListener() {
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent event) {
				int keyCode = event.getKeyCode();
				if (keyCode == KeyEvent.VK_UP) {
				} else if (keyCode == KeyEvent.VK_DOWN) {
				} else if (keyCode == KeyEvent.VK_RIGHT) {
				} else if (keyCode == KeyEvent.VK_LEFT) {
				}
			}
		});
	}

	public void waitFor(long millisecs) {
		try {
			Thread.sleep(millisecs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addObject(GObject g) {
		if (!objects.contains(g)) {
			canvas.add(g);
			objects.add(g);
		}
	}
	
	public void removeObject(GObject g) {
		if (objects.contains(g)) {
			canvas.remove(g);
			objects.remove(g);
		}
	}
	
	public void transferObjectTo(GObject g, double newX, double newY) {
		transferObjectTo(g, newX, newY, 10, 50);
	}
	
	public void transferObjectTo(GObject g, double newX, double newY, double speed, int waitDur) {
		if (objects.contains(g)) {
			double xChange = newX - g.getX();
			double yChange = newY - g.getY();
			double totDis = Math.sqrt(Math.pow(xChange, 2) + Math.pow(yChange, 2));
			int numIters = (int) (totDis/speed);
			for (int i=0; i<numIters; i++) {
				g.move(xChange/numIters, yChange/numIters);
				waitFor(waitDur);
			}
			rapidTransferTo(g,newX,newY);
		}
	}
	
	private void rapidTransferTo(GObject g, double newX, double newY) {
		if (objects.contains(g)) {
			g.move(newX-g.getX(), newY-g.getY());
		}
	}
	
	public double getWidth() {
		return frame.getWidth();
	}

	public double getHeight() {
		return frame.getHeight();
	}
	
}