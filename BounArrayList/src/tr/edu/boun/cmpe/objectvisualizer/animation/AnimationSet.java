package tr.edu.boun.cmpe.objectvisualizer.animation;

import java.util.ArrayList;

public class AnimationSet extends BounAnimation {

	private ArrayList<BounAnimation> animList = new ArrayList<BounAnimation>();
	
	public AnimationSet() {
		
	}
	
	public void addAnimation(BounAnimation anim) {
		animList.add(anim);
	}
	
	@Override
	public void init() {
		for (BounAnimation anim : animList) { 
			anim.init();
			anim.setTarget(getTarget());
			anim.setAnimationStatus(STATUS_RUNNING);
		}
	}

	@Override
	public void updateCall(long dt) {
		if(getAnimationStatus() == STATUS_STOPPED) {
			
		} else if(getAnimationStatus() == STATUS_RUNNING) {
			for (BounAnimation anim : animList) {
				anim.updateCall(dt);
			}
		}
	}
	
	@Override
	public void update(float factor) {}

}
