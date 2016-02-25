package tr.edu.boun.cmpe.objectvisualizer.animation.interpolator;

public class EaseInCubicInterpolator extends Interpolator {

	@Override
	public float calculate(long currentTime, long duration) {
		return (float) (1 - Math.pow(1 - ((float)currentTime / duration), 3));
	}

}
