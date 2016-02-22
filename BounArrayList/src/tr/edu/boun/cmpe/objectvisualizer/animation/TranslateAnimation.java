package tr.edu.boun.cmpe.objectvisualizer.animation;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;

/**
 * Animates translation on a {@link BounObject}.
 * @author triforce
 *
 */
public class TranslateAnimation extends BounAnimation {

	int xi, yi;
	int xf, yf;
	
	int xDiff, yDiff;
	
	/**
	 * Standard constructor. Translates an object from (xi, yi)
	 * to (xf, yf).
	 * @param xi		Initial X position.
	 * @param yi		Initial Y position.
	 * @param xf		Final X position.
	 * @param yf		Final Y position.
	 */
	public TranslateAnimation(int xi, int yi, int xf, int yf) {
		this.xi = xi;
		this.yi = yi;
		this.xf = xf;
		this.yf = yf;
		
		
	}
	
	public void init() {
		this.xDiff = xf - xi;
		this.yDiff = yf - yi; 
	}
	
	@Override
	public void update(float factor) {
		getTarget().translateTo(xi + (int)(xDiff * factor), yi + (int)(yDiff * factor));
	}

}
