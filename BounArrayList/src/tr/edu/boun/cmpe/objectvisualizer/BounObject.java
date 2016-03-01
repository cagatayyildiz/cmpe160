package tr.edu.boun.cmpe.objectvisualizer;

import java.awt.Color;
import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.animation.AlphaAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.AnimationSet;
import tr.edu.boun.cmpe.objectvisualizer.animation.BounAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.TranslateAnimation;
import tr.edu.boun.cmpe.objectvisualizer.exception.BounVisualizerException;

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

	private ObjectVisualizerContext context;
	
	private BounAnimation animation;
	/*
	 * Position parameters
	 */
	private int x = 0;
	private int y = 0;
	
	private int width = 1;
	private int height = 1;
	
	private boolean updateDimensionsFlag = false;
	
	private Color color = Color.BLACK;
	
	public BounObject() throws BounVisualizerException {
		if(BounStaticTracker.isStaticContextActive()) {
			this.context = BounStaticTracker.getStaticContext();
		} else {
			Log.error("No static context. Please initiate the static context first.");
			throw new BounVisualizerException();
		}
	}
	
	public BounObject(ObjectVisualizerContext context) {
		this.context = context;
	}
	
	public void updater(long dt) {
		if(updateDimensionsFlag) {
			measure();
			updateDimensionsFlag = false;
		}
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
	
	/**
	 * Sets the object's dimension parameters. 
	 * This method is called only if the updateDimensionsFlag
	 * is set during an update call.
	 */
	public void measure() {}
	
	/**
	 * Called when an event is dispatched on the object.
	 * @param e Event parameters
	 */
	public void handleEvent(Event e) {
		
	}
	
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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public void invalidate() {
		updateDimensionsFlag = true;
	}

	public ObjectVisualizerContext getContext() {
		return context;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(BounStaticTracker.isStaticContextActive()) {
			BounStaticTracker.getStaticContext().removeObject(this);
		}
	}
	
}
