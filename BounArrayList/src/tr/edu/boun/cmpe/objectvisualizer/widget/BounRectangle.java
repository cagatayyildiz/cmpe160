package tr.edu.boun.cmpe.objectvisualizer.widget;

import java.awt.Graphics;
import java.util.Random;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;
import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.animation.TranslateAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseInCubicInterpolator;

public class BounRectangle extends BounObject {

	int width = 100;
	int height = 100;
	
	public BounRectangle(ObjectVisualizerContext context) {
		super(context);
		setX(0);
		setY(0);
	}
	
	public BounRectangle(ObjectVisualizerContext context, int xInit, int yInit) {
		super(context);
		setX(xInit);
		setY(yInit);
	}
	
	public BounRectangle(ObjectVisualizerContext context, int xInit, int yInit, int width, int height) {
		super(context);
		setX(xInit);
		setY(yInit);
		setWidth(width);
		setHeight(height);
	}

	public BounRectangle(ObjectVisualizerContext context, int spread) {
		super(context);
		Random r = new Random();
		this.setX(spread/10 + r.nextInt(spread));
		this.setY(spread/10 + r.nextInt(spread));
	}
	
	
	@Override
	public void update(long dt) {
		
	}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.drawRect(getX(), getY(), width, height);
		
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void moveTo(int xOff, int yOff, int animDur) {
		TranslateAnimation anim = new TranslateAnimation(getX(), getY(), getX()+xOff, getY()+yOff);
		anim.setDuration(animDur);
		anim.setInterpolator(new EaseInCubicInterpolator());
		this.setAnimation(anim);
		this.startAnimation();
	}
	
	public void moveRandom(int spread,int animDur) {
		Random r = new Random();
		moveTo(r.nextInt(spread)-spread/2,r.nextInt(spread)-spread/2,animDur);
	}
	

}
