package linkedList;

import java.awt.Graphics;
import java.util.ArrayList;

import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRectangle;
import arraylist.MyCanvas;


public class Arrow extends GObject{
	double startX, startY , lengthHorizontal,lengthVertical, angle;
	MyCanvas canvas ;
	private ArrayList<GLine> lines = new ArrayList<GLine>(3);
	  public Arrow(MyCanvas canvas, double startX, double startY, double length, double angle){
		  this.canvas = canvas ;
		  double ratio = 0.5 ;
		  this.startX = startX ;
		  this.startY = startY ;
		  this.lengthHorizontal = length ;
		  double arrowHeadLengthX = lengthHorizontal*ratio ;
		  double arrowHeadLengthY = arrowHeadLengthX*Math.tan(angle*0.0174532);
		  this.lengthVertical = 2 * arrowHeadLengthY ;
		  GLine line1 = new GLine(startX, startY, startX + length, startY);
		  GLine line2 = new GLine(startX + length, startY, startX + arrowHeadLengthX, startY + arrowHeadLengthY);
		  GLine line3 = new GLine(startX + length, startY, startX + arrowHeadLengthX, startY - arrowHeadLengthY);
		  lines.add(0, line1);
		  lines.add(1, line2);
		  lines.add(2, line3);
	  }
	@Override
	public GRectangle getBounds() {
		return new GRectangle(startX, startY - lengthVertical/2, lengthHorizontal, lengthVertical);
	}
	@Override
	public void paint(Graphics arg0) {
		// Individual components are already painted by canvas, nothing to add.
	}
	
	public void addComponentsToCanvas(){
		for(GLine l : lines){
			canvas.addObject(l);
		}
	}
	
	@Override
	public void move(double arg0, double arg1) {
		for(GLine l : lines){
			l.move(arg0, arg1);
		}
	}
	
	@Override
	public void setLocation(double arg0, double arg1) {
		for(GLine l : lines){
			l.move(arg0 - l.getX(), arg1 - l.getY());
		}
	}
	
	public void setVisible(boolean value){
		for(GLine l : lines){
			l.setVisible(value);
		}
	}
	
	double rightBorderX(){
		return startX + lengthHorizontal ;
	}
	
}
