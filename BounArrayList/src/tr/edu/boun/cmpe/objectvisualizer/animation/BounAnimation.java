package tr.edu.boun.cmpe.objectvisualizer.animation;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.Interpolator;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.LinearInterpolator;

/**
 * Helper class to animate objects on the screen.
 * @author triforce
 *
 */
public abstract class BounAnimation {

	public static final int ANIMATION_ONE_TIME = 0;
	public static final int ANIMATION_LOOP = 1;
	public static final int ANIMATION_FORWARD_BACKWARD_LOOP = 2;
	
	public static final int STATUS_STOPPED = 0;
	public static final int STATUS_RUNNING = 1;
	
	private BounObject target;
	
	private Interpolator interpolator = new LinearInterpolator();
	protected long currentTime = 0;
	protected long duration = 5000;
	private int animationType = 0;
	private int animationStatus = 0;
	
	private boolean forward = true;
	
	public void updateCall(long dt) {
		if(animationStatus == STATUS_STOPPED) {
			
		} else if(animationStatus == STATUS_RUNNING) {
			if(animationType == ANIMATION_FORWARD_BACKWARD_LOOP) {
				if(forward) {
					currentTime += dt;
				} else {
					currentTime -= dt;
				}
				if(duration < currentTime) {
					forward = false;
				}
				if(currentTime < 0) {
					forward = true;
				}
			} else {
				currentTime += dt;
			}
			float factor = getInterpolator().calculate(currentTime, duration);
			if(factor >= 1) {
				factor = 1;
				if(animationType == ANIMATION_LOOP) {
					currentTime = 0;
				} else if(animationType == ANIMATION_ONE_TIME){
					animationStatus = STATUS_STOPPED;
				}
			}
			update(factor);
			
		}
	}
	
	public abstract void init();
	public abstract void update(float factor);
	
	public Interpolator getInterpolator() {
		return interpolator;
	}
	
	public void setInterpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
	}
	
	
	public int getAnimationType() {
		return animationType;
	}
	
	/**
	 * Can be {@link BounAnimation#ANIMATION_ONE_TIME} or 
	 * {@link BounAnimation#ANIMATION_LOOP}.
	 * @param animationType		Animation type to be set.
	 */
	public void setAnimationType(int animationType) {
		this.animationType = animationType;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public BounObject getTarget() {
		return target;
	}
	
	public void setTarget(BounObject target) {
		this.target = target;
	}
	
	public int getAnimationStatus() {
		return animationStatus;
	}
	
	public void setAnimationStatus(int animationStatus) {
		this.animationStatus = animationStatus;
	}
}
