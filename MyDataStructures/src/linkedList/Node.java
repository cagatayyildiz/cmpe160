package linkedList;

import java.awt.Font;
import java.awt.Graphics;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import arraylist.MyCanvas;

public class Node extends GObject{
	GRect box ;
	double leftTopX, leftTopY, rightX, rightY;
	int CELL_EDGE_LEN;
	GLabel value ;
	GLine seperator;
	MyCanvas canvas ;
	
	public Node(MyCanvas canvas, double leftTopX, double leftTopY, int value, int cell_edge_len){
		CELL_EDGE_LEN = cell_edge_len ;
		int CONTENT_FONT_SIZE = CELL_EDGE_LEN/3;
		this.leftTopX = leftTopX ;
		this.leftTopY = leftTopY ;
		this.rightX = this.leftTopX + CELL_EDGE_LEN;
		this.rightY = this.leftTopY + CELL_EDGE_LEN/2;
		this.value = new GLabel(value+"");
		this.value.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		//added 3 to x coordinate of the label "value" , so that it looks better with the arrowhead
		this.value.setLocation( 3+leftTopX + ((CELL_EDGE_LEN*2)/3 - this.value.getWidth())/2, leftTopY + (CELL_EDGE_LEN*2)/3);
		box = new GRect(leftTopX, leftTopY, CELL_EDGE_LEN, CELL_EDGE_LEN ) ;
		seperator = new GLine(leftTopX + (CELL_EDGE_LEN*2)/3, leftTopY, leftTopX + (CELL_EDGE_LEN*2)/3, leftTopY + CELL_EDGE_LEN);
		this.canvas = canvas ;
	}
	
	public void addComponentsToCanvas(){
		canvas.addObject(box);
		canvas.addObject(seperator);
		canvas.addObject(this.value);
	}
	
	public void removeComponentsFromCanvas(){
		canvas.removeObject(box);
		canvas.removeObject(seperator);
		canvas.removeObject(this.value);
		
		
	}
	

	@Override
	public GRectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paint(Graphics arg0) {
		// Individual components are already painted by canvas, nothing to add.	
	}
	
	@Override
	public void move(double arg0, double arg1) {
		box.move(arg0, arg1);
		seperator.move(arg0, arg1);
		value.move(arg0, arg1);
		leftTopX += arg0 ;
		rightX += arg0 ;
		leftTopY += arg1 ;
		rightY += arg1 ;
	}
	
	double rightBorderX(){
		//oh no this is an arbitrary number :<
		return leftTopX + CELL_EDGE_LEN + 60;
	}
	
	double rightBorderY(){
		return leftTopY + CELL_EDGE_LEN/2;
	}
	
	
	
}