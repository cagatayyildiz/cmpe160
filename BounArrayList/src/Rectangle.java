import java.awt.Color;
import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;


public class Rectangle extends BounObject {

	int width = 100;
	int height = 100;
	
	public Rectangle() {
		setX(0);
		setY(0);
	}
	
	@Override
	public void update(long dt) {
		
	}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.drawRect(getX(), getY(), width, height);
		
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

}
