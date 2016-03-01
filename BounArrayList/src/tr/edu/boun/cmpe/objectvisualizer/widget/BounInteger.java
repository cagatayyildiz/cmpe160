package tr.edu.boun.cmpe.objectvisualizer.widget;

import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.BounStaticTracker;
import tr.edu.boun.cmpe.objectvisualizer.Event;
import tr.edu.boun.cmpe.objectvisualizer.animation.AlphaAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.AnimationSet;
import tr.edu.boun.cmpe.objectvisualizer.animation.BounAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.BounAnimation.AnimationListener;
import tr.edu.boun.cmpe.objectvisualizer.animation.TranslateAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseInCubicInterpolator;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseOutCubicInterpolator;
import tr.edu.boun.cmpe.objectvisualizer.exception.BounVisualizerException;

/**
 * Representation of Integer in ObjectVisualizer.
 * @author triforce
 *
 */
public class BounInteger extends ObjectGroup implements AnimationListener{

	private int i = 0;
	private Label label;
	
	public BounInteger() throws BounVisualizerException {
		super();
		init();
	}
	
	public BounInteger(int i) throws BounVisualizerException {
		super();
		this.i = i;
		init();
	}
	
	private void init() {
		int x = (int) (Math.random() * 400);
		int y = (int) (Math.random() * 300);
		
		this.setX(x);
		this.setY(y);
		this.setWidth(20);
		this.setHeight(20);
		
		Rectangle r = new Rectangle();
		r.setX(0);
		r.setY(0);
		r.setWidth(20);
		r.setHeight(20);
		r.setAlpha(0.0f);
		
		Label l = new Label(String.valueOf(i));
		l.setX(0);
		l.setY(0);
		l.setAlpha(0.0f);
		
		this.label = l;
		
		this.addObject(r);
		this.addObject(l);
		
		if(BounStaticTracker.isStaticContextActive()) {
			BounStaticTracker.pushEvent(new Event(Event.TYPE_CREATE, this));
			BounStaticTracker.getStaticContext().addObject(this);
		}
		
	}
	
	@Override
	public void handleEvent(Event e) {
		if(BounStaticTracker.isStaticContextActive()) {
			if(e.getEventType() == Event.TYPE_CREATE) {
				
				TranslateAnimation tAnim = new TranslateAnimation(getX() + 50, getY() + 50, getX(), getY());
				AlphaAnimation aAnim = new AlphaAnimation(0.0f, 1.0f);
				
				tAnim.setDuration(600);
				aAnim.setDuration(600);
				
				tAnim.setInterpolator(new EaseInCubicInterpolator());
				aAnim.setInterpolator(new EaseInCubicInterpolator());
				
				AnimationSet set = new AnimationSet();
				set.addAnimation(tAnim);
				set.addAnimation(aAnim);
				
				this.setAnimation(set);
				this.startAnimation();
				
				
			} else if(e.getEventType() == Event.TYPE_CHANGE_VALUE) {
				TranslateAnimation tAnim = new TranslateAnimation(0, 0, 0, -50);
				AlphaAnimation aAnim = new AlphaAnimation(1.0f, 0.0f);
				
				TranslateAnimation tAnimReturn = new TranslateAnimation(0, 50, 0, 0);
				AlphaAnimation aAnimReturn = new AlphaAnimation(0.0f, 1.0f);
				
				
				tAnim.setDuration(600);
				aAnim.setDuration(600);
				tAnimReturn.setDuration(600);
				aAnimReturn.setDuration(600);
				
				tAnimReturn.setDelay(600);
				aAnimReturn.setDelay(600);
				
				tAnim.setInterpolator(new EaseOutCubicInterpolator());
				aAnim.setInterpolator(new EaseOutCubicInterpolator());
				tAnimReturn.setInterpolator(new EaseInCubicInterpolator());
				aAnimReturn.setInterpolator(new EaseInCubicInterpolator());
				
				AnimationSet set = new AnimationSet();
				set.addAnimation(tAnim);
				set.addAnimation(aAnim);
				set.addAnimation(tAnimReturn);
				set.addAnimation(aAnimReturn);
				
				tAnim.setAnimationListener(this);
				
				label.setAnimation(set);
				label.startAnimation();
				
			} else if(e.getEventType() == Event.TYPE_DESTROY) {
				
			}
		}
	}
	
	@Override
	public void update(long dt) {
		super.update(dt);
	}

	@Override
	public void draw(Graphics canvas) {
		super.draw(canvas);
	}
	
	public void setValue(int i) {
		this.i = i;
		BounStaticTracker.pushEvent(new Event(Event.TYPE_CHANGE_VALUE, this));
	}

	@Override
	public void onAnimationStart(BounAnimation anim) {
		
	}

	@Override
	public void onAnimationEnd(BounAnimation anim) {
		label.setText(String.valueOf(i));
	}

	@Override
	public void onAnimationLoop(BounAnimation anim) {
		// TODO Auto-generated method stub
		
	}
	
}
