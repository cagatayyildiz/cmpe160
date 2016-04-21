package linkedList;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.stream.Collectors;

import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRectangle;
import arraylist.MyCanvas;


public class Arrow extends GObject{
	double ARROW_HEAD_RADIUS = 10 ;
	double startX, startY , lengthHorizontal,lengthVertical;
	GLine line1;
	GOval dot;
	MyCanvas canvas ;

	  
	public Arrow(MyCanvas canvas, Node startNode, Node endNode){
		this.canvas = canvas ;
		startX = startNode.rightX + - startNode.CELL_EDGE_LEN/6;
		startY = startNode.rightY ;
		lengthHorizontal = endNode.leftTopX - startX ;
		lengthVertical = endNode.rightY - startY ;
		line1 = new GLine(startX, startY, startX + lengthHorizontal, startY + lengthVertical);
		dot = new GOval(endNode.leftTopX - ARROW_HEAD_RADIUS/2 ,endNode.rightY - ARROW_HEAD_RADIUS/2,10, 10); 
		dot.setFilled(true);
		dot.setFillColor(Color.RED);
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
			canvas.addObject(line1);
			canvas.addObject(dot);
	}
	
	public void removeComponentsFromCanvas(){
			canvas.removeObject(line1);
			canvas.removeObject(dot);
	}
	
	@Override
	public void move(double arg0, double arg1) {
			line1.move(arg0, arg1);
			dot.move(arg0,arg1);
			startX += arg0 ;
			startY += arg1 ;
	}
	
	@Override
	public void setLocation(double arg0, double arg1) {
			line1.move(arg0 - line1.getX(), arg1 - line1.getY());
			dot.move(arg0 - dot.getX(), arg1 - dot.getY());
	}
	
	public void setVisible(boolean value){
			line1.setVisible(value);
			dot.setVisible(value);
	}
	
	double rightBorderX(){
		return startX + lengthHorizontal ;
	}
	
	
}
