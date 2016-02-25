import java.awt.Font;

import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.animation.AlphaAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.AnimationSet;
import tr.edu.boun.cmpe.objectvisualizer.animation.BounAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.TranslateAnimation;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseInCubicInterpolator;
import tr.edu.boun.cmpe.objectvisualizer.animation.interpolator.EaseOutCubicInterpolator;
import tr.edu.boun.cmpe.objectvisualizer.widget.Label;

/**
 * Includes use cases of {@link ObjectVisualizerContext}
 * @author triforce
 *
 */
public class Main {
	public static void main(String[] args) {
//		ObjectVisualizerContext context = new ObjectVisualizerContext();
//		
//		final Rectangle r = new Rectangle();
//		context.addObject(r);
//		
//		AnimationSet aSet = new AnimationSet();
//		
//		TranslateAnimation anim = new TranslateAnimation(100, 100, 100, 380);
//		anim.setInterpolator(new EaseOutCubicInterpolator());
//		anim.setDuration(500);
//		anim.setAnimationType(BounAnimation.ANIMATION_FORWARD_BACKWARD_LOOP);
//		
//		AlphaAnimation alphaAnim = new AlphaAnimation(0.0f, 1.0f);
//		alphaAnim.setInterpolator(new EaseOutCubicInterpolator());
//		alphaAnim.setDuration(500);
//		alphaAnim.setAnimationType(BounAnimation.ANIMATION_FORWARD_BACKWARD_LOOP);
//		
//		aSet.addAnimation(anim);
//		aSet.addAnimation(alphaAnim);
//		
//		r.setAnimation(aSet);
//		r.startAnimation();
		
		test3();
		
		
	}
	/**
	 * Creating a canvas and adding objects to it.
	 */
	public static void test1() {
		//Create a context of object visualization. It will handle 
		// all objects added to it.
		ObjectVisualizerContext context = new ObjectVisualizerContext();
		
		//Create a component you need.
		Label label = new Label("Test Text Here!");
		//Avoid recreating components for performance. If you need to
		//create and delete a lot of components at the same time. Just
		//create a bunch of them at first, and reuse the old ones
		//whenever you need another.
		
		//Set position on screen.
		label.setX(0);
		label.setY(0);
		
		//Set font
		label.setFont(new Font(null, Font.BOLD, 18));
		
		//Add the object to the canvas.
		context.addObject(label);
		
	}
	
	/**
	 * Using animations.
	 */
	public static void test2() {
		//Create a context
		ObjectVisualizerContext context = new ObjectVisualizerContext();
		
		//Create an object and add it.
		Label label = new Label("Test");
		label.setX(100);
		label.setY(100);
		label.setFont(new Font(null, Font.PLAIN, 16));
		
		context.addObject(label);
		
		//Create an animation you like.
		//I'll create an AlphaAnimation (transparency) here.
		AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
		//Values has to be 0 <= x <= 1. 0 means transparent. 1 means solid.
		
		//duration is in milliseconds
		anim.setDuration(5000);
		//5000 = 5 seconds
		//It's actually too long for an animation.
		
		//Interpolators are a way of easing animations.
		//This step here is optional.
		//anim.setInterpolator(new EaseOutCubicInterpolator());
		
		//Give an animation to an object.
		label.setAnimation(anim);
		
		//Fire off the animation whenever you want.
		label.startAnimation();
		
		//animation callbacks are currently WIP.
	}
	
	
	/**
	 * Translate animation usage
	 */
	public static void test3() {
		//Create a context
		ObjectVisualizerContext context = new ObjectVisualizerContext();
		
		// Create an object and add it to the context.
		final Label l = new Label("Test");
		l.setX(10);
		l.setY(10);
		
		context.addObject(l);
		
		//Create a translate animation.
		TranslateAnimation anim = new TranslateAnimation(10, 10, 100, 100);
		//Parameters are initial x, y values (10, 10) and 
		//final x, y values (100, 100)
		
		//700 milliseconds is a reasonable duration for any kind of animation.
		anim.setDuration(1000);
		
		anim.setInterpolator(new EaseInCubicInterpolator());
		
		l.setAnimation(anim);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				l.startAnimation();
			}
		}).start();
		
		
	}
	
	
	
	
	
	
}
