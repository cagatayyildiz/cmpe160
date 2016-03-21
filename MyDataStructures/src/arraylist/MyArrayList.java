package arraylist;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.SizeLimitExceededException;

import visualization.MyCanvas;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;


public class MyArrayList  {

	private MyCanvas canvas;
	private GLabel operationNameTitle;
	private GLabel operationName;
	private GLabel operationNumberTitle;
	private GLabel operationNumber;
	private GRect wrapperRect;
	private ArrayList<GRect> cells = new ArrayList<GRect>();
	private ArrayList<GLabel> labels = new ArrayList<GLabel>();
	
	private static int CELL_EDGE_LEN = 30;
	private static int WRAPPER_WIDTH;
	private static int SPREAD;
	private static int CONTENT_FONT_SIZE;

	private static final int INITIAL_SIZE = 10;
	private int[] data = new int[INITIAL_SIZE];
	private int currentDataLength = 0;
	private Random rand = new Random();
	
	public MyArrayList(int cell_edge_len) {
		CELL_EDGE_LEN = cell_edge_len;
		setSizes();
		canvas = new MyCanvas("ArrayList Example", 1280, 720);
		addwrapperRect();
		addCells();
		addLabels();
	}

	private void setSizes() {
		WRAPPER_WIDTH = CELL_EDGE_LEN * 10;
		SPREAD = 300;
		CONTENT_FONT_SIZE = CELL_EDGE_LEN/2;
	}
	
	public void add(int num) throws Exception {
		operationName.setLabel("Insertion");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(num+"");
		if (num > 99 || num < 0) {
			operationName.setLabel("");
			operationNumber.setLabel("");
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}
		if (data.length == currentDataLength) {
			resize();
		}

		GLabel newNumLabel = new GLabel(num + "");
		newNumLabel.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		labels.add(newNumLabel);
		newNumLabel.setLocation(operationNumber.getX(), operationNumber.getY());
		operationNumber.setLabel("");
		canvas.addObject(newNumLabel);
		canvas.waitFor(500);
		canvas.transferObjectTo(newNumLabel, wrapperRect.getX() + currentDataLength * CELL_EDGE_LEN + CONTENT_FONT_SIZE / 3,
				wrapperRect.getY() + 4*CONTENT_FONT_SIZE/3, 10, 10);
		
		data[currentDataLength++] = num;
		operationName.setLabel("");
	}
	
	public void deleteAt(int index) {
		if (index < 0 || index>currentDataLength) {
			throw new IllegalArgumentException("Enter a valid index");
		}
		// preparation
		operationName.setLabel("Deletion");
		operationNumberTitle.setLabel("Index:");
		canvas.waitFor(500);
		operationNumber.setLabel(index+"");
		GOval oval = new GOval((int) (wrapperRect.getX()+index*CELL_EDGE_LEN), wrapperRect.getY(), 
				CELL_EDGE_LEN,CELL_EDGE_LEN);
		oval.setColor(Color.RED);
		canvas.addObject(oval);
		canvas.waitFor(500);
		canvas.removeObject(oval);
		
		canvas.removeObject(labels.get(index));
		for (int i=index; i<currentDataLength; i++) {
			labels.get(i).move(-CELL_EDGE_LEN, 0);
			canvas.waitFor(500);
		}
		labels.remove(index);
		currentDataLength--;
		
		canvas.waitFor(1000);
		operationName.setLabel("");
		operationNumber.setLabel("");
		
	}
	
	public void resize() throws SizeLimitExceededException {
		int newLength = 3*currentDataLength/2+1;
		int wLim = CELL_EDGE_LEN * newLength;
		if (wLim > canvas.getWidth()) {
			throw new SizeLimitExceededException("No more storage left for new elements");
		}
		int newX = rand.nextInt((int) canvas.getWidth()-wLim);
		int newY = rand.nextInt((int) canvas.getHeight()-2*CONTENT_FONT_SIZE) + 2*CONTENT_FONT_SIZE;
		
		wrapperRect.setLocation(newX,newY);
		wrapperRect.setSize(newLength*CELL_EDGE_LEN,wrapperRect.getHeight());
		
		moveRelativeToWrapper(newLength,newX,newY);
	}
	
	private void moveRelativeToWrapper(int newLength, double newX, double newY) {
		for (int i=0; i<currentDataLength; i++) {
			cells.get(i).setLocation(newX + i * CELL_EDGE_LEN,newY);
			labels.get(i).setLocation(newX + i * CELL_EDGE_LEN + CONTENT_FONT_SIZE / 3, newY + 4*CONTENT_FONT_SIZE/3);
		}
		for (int i=currentDataLength; i<newLength; i++) {
			GRect rect = new GRect(newX + i * CELL_EDGE_LEN, newY, CELL_EDGE_LEN, CELL_EDGE_LEN);
			canvas.addObject(rect);
			cells.add(rect);
		}
		int [] newDataArray = new int[newLength];
		for (int i=0; i<data.length; i++) {
			newDataArray[i] = data[i];
		}
		data = newDataArray;
		
	}

	private void addCells() {
		double X = wrapperRect.getX();
		double Y = wrapperRect.getY();
		for (int i = 0; i < INITIAL_SIZE; i++) {
			GRect rect = new GRect(X + i * CELL_EDGE_LEN, Y, CELL_EDGE_LEN, CELL_EDGE_LEN);
			canvas.addObject(rect);
			cells.add(rect);
		}
	}

	private void addwrapperRect() {
		wrapperRect = new GRect(rand.nextInt(SPREAD),rand.nextInt(SPREAD)+2*CONTENT_FONT_SIZE,WRAPPER_WIDTH,CELL_EDGE_LEN);
		canvas.addObject(wrapperRect); // adds to canvas
	}
	
	private void addLabels() {
		operationNameTitle = new GLabel("Operation:",CONTENT_FONT_SIZE,CONTENT_FONT_SIZE);
		operationNameTitle.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		operationName = new GLabel("",CONTENT_FONT_SIZE*8,CONTENT_FONT_SIZE);
		operationName.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		
		operationNumberTitle = new GLabel("Number:",CONTENT_FONT_SIZE,CONTENT_FONT_SIZE*2);
		operationNumberTitle.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		operationNumber = new GLabel("",CONTENT_FONT_SIZE*8,CONTENT_FONT_SIZE*2);
		operationNumber.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));

		canvas.addObject(operationNameTitle);
		canvas.addObject(operationName);
		canvas.addObject(operationNumberTitle);
		canvas.addObject(operationNumber);
	}
	
	public static void main(String[] args) throws Exception {
		MyArrayList list = new MyArrayList(50);
		Random r = new Random();
		for (int i=0; i<15; i++) {
			list.add(r.nextInt(100));
		}
		list.deleteAt(1);
		list.deleteAt(1);
		list.deleteAt(5);
	}
	
}
