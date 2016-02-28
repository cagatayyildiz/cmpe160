package tr.edu.boun.cmpe.experimental;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import javax.naming.SizeLimitExceededException;

import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.animation.TranslateAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseInCubicInterpolator;
import tr.edu.boun.cmpe.objectvisualizer.widget.BounRectangle;
import tr.edu.boun.cmpe.objectvisualizer.widget.Label;

public class BounArrayList {

	private ObjectVisualizerContext context;
	private BounRectangle list;
	private ArrayList<BounRectangle> cells = new ArrayList<BounRectangle>();
	private ArrayList<Label> labels = new ArrayList<Label>();
	
	private static final int HEIGHT = 30;
	private static final int WIDTH = HEIGHT * 10;
	private static final int SPREAD = 300;
	private static final int TITLE_FONT_SIZE = 20;
	private static final int CONTENT_FONT_SIZE = 16;
	private static final int ANIM_DUR = 1000;

	private static final int INITIAL_SIZE = 10;
	private int[] data = new int[INITIAL_SIZE];
	private int currentDataLength = 0;
	Random rand = new Random();

	public BounArrayList(ObjectVisualizerContext context) {
		this.context = context;
		initArrayList();
		initCells();
	}

	public void insert(int num) throws Exception {
		if (num > 99 || num < 0) {
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}
		if (data.length == currentDataLength) {
			resize();
		}
		Label label = new Label(num + "");
		label.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		
		// make below an instance method in Label.java
		TranslateAnimation anim = new TranslateAnimation(0, 0, list.getX() + currentDataLength * HEIGHT + CONTENT_FONT_SIZE / 3,
				list.getY() + CONTENT_FONT_SIZE / 3);
		anim.setDuration(ANIM_DUR);
		anim.setInterpolator(new EaseInCubicInterpolator());
		label.setAnimation(anim);
		label.startAnimation();
		// make above an instance method in Label.java
		
		data[currentDataLength++] = num;
		context.addObject(label);
		labels.add(label);
		wait(ANIM_DUR);
	}
	
	public void resize() throws SizeLimitExceededException {
		int newLength = 3*currentDataLength/2+1;
		int wLim = HEIGHT * newLength;
		if (wLim > context.getFrame().FRAME_WIDTH) {
			throw new SizeLimitExceededException("No more storage left for new elements");
		}
		int newX = rand.nextInt(context.getFrame().FRAME_WIDTH - wLim);
		int newY = rand.nextInt(context.getFrame().FRAME_HEIGHT - TITLE_FONT_SIZE);
		list.setX(newX);
		list.setY(newY);
		list.setWidth(newLength*HEIGHT);
		for (int i=0; i<currentDataLength; i++) {
			cells.get(i).setX(newX + i * HEIGHT);
			cells.get(i).setY(newY);
			labels.get(i).setX(newX + i * HEIGHT + CONTENT_FONT_SIZE / 3);
			labels.get(i).setY(newY + CONTENT_FONT_SIZE / 3);
		}
		for (int i=currentDataLength; i<newLength; i++) {
			BounRectangle rect = new BounRectangle(newX + i * HEIGHT, newY, HEIGHT, HEIGHT);
			context.addObject(rect);
			cells.add(rect);
		}
		int [] newDataArray = new int[newLength];
		for (int i=0; i<data.length; i++) {
			newDataArray[i] = data[i];
		}
		data = newDataArray;
	}

	private void initCells() {
		int X = list.getX();
		int Y = list.getY();
		for (int i = 0; i < INITIAL_SIZE; i++) {
			BounRectangle rect = new BounRectangle(X + i * HEIGHT, Y, HEIGHT, HEIGHT);
			context.addObject(rect);
			cells.add(rect);
		}
	}

	private void initArrayList() {
		list = new BounRectangle(SPREAD);
		list.setWidth(WIDTH);
		list.setHeight(HEIGHT);
		context.addObject(list);
	}
	
	// MOVE THIS METHOD TO FRAME OF SOMEWHERE LIKE THIS
	public static void wait(int miliSec) {
		try {
			Thread.sleep(miliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
