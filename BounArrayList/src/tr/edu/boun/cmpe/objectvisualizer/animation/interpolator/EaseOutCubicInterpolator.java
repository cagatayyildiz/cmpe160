package tr.edu.boun.cmpe.objectvisualizer.animation.interpolator;

public class EaseOutCubicInterpolator extends Interpolator {

	@Override
	public float calculate(long currentTime, long duration) {
		return (float) Math.pow((float)currentTime/duration, 3);
	}

}
