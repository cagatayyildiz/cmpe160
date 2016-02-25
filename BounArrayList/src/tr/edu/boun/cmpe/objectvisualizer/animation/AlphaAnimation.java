package tr.edu.boun.cmpe.objectvisualizer.animation;

public class AlphaAnimation extends BounAnimation {

	private float fromAlpha;
	private float toAlpha;
	
	private float dAlpha;
	
	public AlphaAnimation(float fromAlpha, float toAlpha) {
		this.fromAlpha = fromAlpha;
		this.toAlpha = toAlpha;
	}
	
	@Override
	public void init() {
		dAlpha = toAlpha - fromAlpha;
	}

	@Override
	public void update(float factor) {
		getTarget().setAlpha(fromAlpha + (dAlpha * factor));
	}

}
