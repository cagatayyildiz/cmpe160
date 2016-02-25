package tr.edu.boun.cmpe.objectvisualizer;

import java.awt.Color;
import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.animation.BounAnimation;

/**
 * Base object model for all the Boun objects.<br />
 * <br />
 * Main aspect of these objects is that they can 
 * be visualized when given in an {@link ObjectVisualizerContext}.
 * It is necessary to implement {@link BounObject#update(long)}
 * and {@link BounObject#draw(Graphics)} methods in subclasses. Though
 * they can be left empty if not sure about what to do. But at least 
 * the draw method should be overridden to display some information
 *  on screen. <br />
 * 
 * @author triforce
 *
 */
public abstract class BounObject {

	private BounAnimation animation;
	/*
	 * Position parameters
	 */
	private int x = 0;
	private int y = 0;
	
	private Color color = Color.BLACK;
	
	protected void updater(long dt) {
		if(animation != null && 
				animation.getAnimationStatus() == BounAnimation.STATUS_RUNNING) {
			animation.updateCall(dt);
		}
		update(dt);
	}
	
	/**
	 * Called on each frame of the simulation.
	 * @param dt		Time passed since the last
	 * 					frame generation process.
	 * 					(in milli)
	 */
	public abstract void update(long dt);
	
	/**
	 * Called on each frame of the simulation.
	 * @param canvas	Canvas to be drawn.
	 */
	public abstract void draw(Graphics canvas);
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public float getAlpha() {
		return color.getAlpha() / 255f;
	}

	public void setAlpha(float alpha) {
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(alpha * 255));
	}
	
	public void translateTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setAnimation(BounAnimation animation) {
		this.animation = animation;
		animation.setTarget(this);
	}
	
	public void startAnimation() {
		this.animation.init();
		this.animation.setAnimationStatus(BounAnimation.STATUS_RUNNING);
	}

	
}
