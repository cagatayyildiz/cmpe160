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
	
	private AnimationListener listener;
	
	private Interpolator interpolator = new LinearInterpolator();
	protected long currentTime = 0;
	protected long duration = 5000;
	protected long delay = 0; 
	private int animationType = 0;
	private int animationStatus = 0;
	
	private boolean forward = true;
	
	private boolean callbackFlag = false;
	
	public void updateCall(long dt) {
		if(animationStatus == STATUS_STOPPED) {
			
		} else if(animationStatus == STATUS_RUNNING) {
			if(animationType == ANIMATION_FORWARD_BACKWARD_LOOP) {
				if(forward) {
					currentTime += dt;
				} else {
					currentTime -= dt;
				}
				if(duration + delay < currentTime) {
					forward = false;
				}
				if(currentTime < 0) {
					forward = true;
					if(listener != null) {
						listener.onAnimationLoop(this);
					}
				}
			} else {
				currentTime += dt;
			}
			if(currentTime > delay) {
				if(!callbackFlag) {
					if(listener != null) {
						listener.onAnimationStart(this);
					}
					callbackFlag = true;
				}
				float factor = getInterpolator().calculate(currentTime - delay, duration);
				if(factor >= 1) {
					factor = 1;
					if(animationType == ANIMATION_LOOP) {
						currentTime = 0;
						if(listener != null) {
							listener.onAnimationLoop(this);
						}
					} else if(animationType == ANIMATION_ONE_TIME){
						if(listener != null) {
							listener.onAnimationEnd(this);
						}
						animationStatus = STATUS_STOPPED;
					}
				}
				update(factor);
			}
			
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
	
	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
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
	
	public void setAnimationListener(AnimationListener listener) {
		this.listener = listener;
	}
	
	public interface AnimationListener {
		public void onAnimationStart(BounAnimation anim);
		public void onAnimationEnd(BounAnimation anim);
		public void onAnimationLoop(BounAnimation anim);
	}
	
}
