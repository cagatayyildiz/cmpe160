package tr.edu.boun.cmpe.objectvisualizer.animation.interpolator;

public class LinearInterpolator extends Interpolator {

	@Override
	public float calculate(long currentTime, long duration) {
		return (float)currentTime / duration;
	}

}
