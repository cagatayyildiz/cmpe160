package tr.edu.boun.cmpe.objectvisualizer.widget;
import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;
import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.exception.BounVisualizerException;


public class Rectangle extends BounObject {

	public Rectangle() throws BounVisualizerException {
		super();
	}
	
	public Rectangle(ObjectVisualizerContext context) {
		super(context);
		setX(0);
		setY(0);
	}
	
	@Override
	public void update(long dt) {
		
	}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.drawRect(getX(), getY(), getWidth(), getHeight());
		
	}

}
