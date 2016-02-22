package tr.edu.boun.cmpe.objectvisualizer.animation.interpolator;

public abstract class Interpolator {
	public abstract float calculate(long currentTime, long duration);
}
