package linkedList;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import arraylist.MyCanvas;
import linkedList.Node;


public class MyLinkedList {
	private MyCanvas canvas;
	private GLabel operationNameTitle;
	private GLabel operationName;
	private GLabel operationNumberTitle;
	private GLabel operationNumber;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	private ArrayList<GLabel> labels = new ArrayList<GLabel>();
	private static Random rand = new Random();
	
	private static int CELL_EDGE_LEN = 50;
	private static int CONTENT_FONT_SIZE;
	private static int INITIAL_LEFT_BORDER = 100 ;
	
	private int currentDataLength = 0;
	private double leftBorder = 100;
	
	public MyLinkedList(int cell_edge_len , int leftBorder) {
		canvas = new MyCanvas("LinkedList Animation", 1280, 720);
		CELL_EDGE_LEN = cell_edge_len;
		CONTENT_FONT_SIZE = CELL_EDGE_LEN/3;
		this.leftBorder = INITIAL_LEFT_BORDER = leftBorder ;
		addLabels();
	}
	
	public int size(){
		return currentDataLength ;
	}
	
	/**
	 * Appends the specified number to the end of this linkedlist.
	 * 
	 * This method is equivalent to addLast.
	 * @param num : number to be added
	 * 	
	 */
	public void add(int num){
		addLast(num);
	}
	
	public void addFirst(int num){
		add(0,num);
	}
	
	/**
	 * Appends the specified number to the end of this linkedlist.
	 * 
	 * @param num : number to be added
	 */
	public void addLast(int num){
		operationName.setLabel("Insertion");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(num+"");
		if (num > 99 || num < 0) {
			operationName.setLabel("");
			operationNumber.setLabel("");
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}
		
		GLabel newNumLabel = new GLabel(num + "");
		newNumLabel.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		labels.add(newNumLabel);
		newNumLabel.setLocation(operationNumber.getX(), operationNumber.getY());
		
		operationNumber.setLabel("");
		canvas.addObject(newNumLabel);
		canvas.waitFor(500);
		if(currentDataLength != 0){
			leftBorder = nodes.get(currentDataLength-1).rightBorderX();
		}else{
			leftBorder = INITIAL_LEFT_BORDER;
		}
		//increment currentDataLength
		currentDataLength++;
		
		Node node = new Node(canvas,leftBorder,Math.random() * 400 + 150,num, CELL_EDGE_LEN);
		
		canvas.transferObjectTo(newNumLabel,node.value.getX(), node.value.getY(), 10, 10);
		canvas.waitFor(500);
		addNode(node);
		
		if(currentDataLength > 1){
			addArrow(new Arrow(canvas, nodes.get(currentDataLength - 2) , nodes.get(currentDataLength - 1)));
		}
		
		canvas.removeObject(newNumLabel);
		
		
	}
	
	public void add(int index, int num){
		if(index < 0 || index > currentDataLength){
			throw new IndexOutOfBoundsException("Oops. Size : "+currentDataLength+", Index : "+index);
		}
		operationName.setLabel("Insertion to Index \""+index+"\"");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(num+"");
		if (num > 99 || num < 0) {
			operationName.setLabel("");
			operationNumber.setLabel("");
			throw new IllegalArgumentException("Enter integers in [0,99]");
		}
		
		GLabel newNumLabel = new GLabel(num + "");
		newNumLabel.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		labels.add(newNumLabel);
		canvas.addObject(newNumLabel);
		newNumLabel.setLocation(operationNumber.getX(), operationNumber.getY());
		operationNumber.setLabel("");
		
		canvas.waitFor(1000);
		indicateIndex(index);
		if(currentDataLength != 0){
			if(index == 0){
				leftBorder = INITIAL_LEFT_BORDER ;
				shiftRight(index, index , 60 + CELL_EDGE_LEN);
				canvas.waitFor(500);
			}else if(index == currentDataLength){
				leftBorder = nodes.get(index - 1).rightBorderX();
			}else if(index + 1 == currentDataLength){
				leftBorder = nodes.get(index - 1).rightBorderX();
				removeArrow(arrows.get(index - 1));
				canvas.waitFor(500);
				nodes.get(index).move(60 + CELL_EDGE_LEN,  0);
				canvas.waitFor(500);
			}else{
				leftBorder = nodes.get(index - 1).rightBorderX();
				removeArrow(arrows.get(index - 1));
				canvas.waitFor(500);
				shiftRight(index,index - 1, 60 + CELL_EDGE_LEN);
				canvas.waitFor(500);
			}
		}else{
			leftBorder = INITIAL_LEFT_BORDER;
		}
		
		
		
		
		
		Node node = new Node(canvas,leftBorder,Math.random() * 400 + 150,0, CELL_EDGE_LEN);
		node.value.setVisible(false);
		addNode(index, node);

		canvas.transferObjectTo(newNumLabel,nodes.get(index).value.getX(), nodes.get(index).value.getY(), 10, 10);
		canvas.waitFor(500);
		nodes.get(index).value.setLabel(newNumLabel.getLabel());
		nodes.get(index).value.setVisible(true);

		canvas.removeObject(newNumLabel);
		
		canvas.waitFor(500);
		
		if (index !=0 || currentDataLength != 0) {
			//add arrows to nodes
			if (index == 0) {
				addArrow(0, new Arrow(canvas, nodes.get(0), nodes.get(1)));
				canvas.waitFor(500);
			} else if (index == currentDataLength) {
				addArrow(new Arrow(canvas, nodes.get(index - 1), nodes.get(index)));
				canvas.waitFor(500);
			} else {
				addArrow(index - 1, new Arrow(canvas, nodes.get(index - 1), nodes.get(index)));
				canvas.waitFor(500);
				addArrow(index, new Arrow(canvas, nodes.get(index), nodes.get(index + 1)));
				canvas.waitFor(500);
			} 
		}
		//increment currentDataLength
		currentDataLength++;
	}
	
	public void remove(int index){
		if(index < 0 || index >= currentDataLength){
			throw new IndexOutOfBoundsException("Oops. Size : "+currentDataLength+", Index : "+index);
		}
		//boring stuff
		operationName.setLabel("Remove "+index+". element");
		operationNumberTitle.setLabel("Number:");
		canvas.waitFor(500);
		operationNumber.setLabel(nodes.get(index).value.getLabel());
		
		//TODO : throw exception if length is 0
		if(currentDataLength != 0){
			leftBorder = nodes.get(currentDataLength-1).rightBorderX();
		}
		
		//Animation that indicates the node to be deleted
		indicateIndex(index);
		
		//remove the arrow pointing to the node if it is not the first one
		if(index != 0){
			removeArrow(arrows.get(index - 1));
			canvas.waitFor(500);
		}
		
		//if the node to be deleted is not the last one, remove the arrow that points to next node
		if(index + 1 != currentDataLength){
			if(index == 0){
				removeArrow(arrows.get(0));
			}else{
				removeArrow(arrows.get(index - 1));
			}
			canvas.waitFor(500);
		}
		
		//finally, remove the node itself
		removeNode(nodes.get(index));
		canvas.waitFor(500);
		
		//move the nodes and arrows to the left for the sake of visualization
		if(index == 0){
			shiftLeft(0, 0, 60 + CELL_EDGE_LEN);
			canvas.waitFor(500);
		}else if(index != currentDataLength - 1){
			shiftLeft(index, index - 1, 60 + CELL_EDGE_LEN);
			canvas.waitFor(500);
		}
		
		//draw an arrow combining next and previous nodes, if deleted node
		//is not the last one or first one
		if(index + 1 != currentDataLength && index != 0){
			addArrow(index - 1, new Arrow(canvas, nodes.get(index - 1), nodes.get(index)));
			canvas.waitFor(500);
		}
		
		//decrement current data length
		currentDataLength-- ;
	}
	
	
	private void addNode(double x, double y, int num){
		Node node = new Node(canvas, x, y, num, CELL_EDGE_LEN);
		node.addComponentsToCanvas();
		nodes.add(node);
		
	}
	
	private void addNode(Node node){
		node.addComponentsToCanvas();
		nodes.add(node);
		
	}
	
	private void addNode(int index, Node node){
		node.addComponentsToCanvas();
		nodes.add(index,node);
		
	}
	
	private void addArrow(Arrow arrow){
		arrow.addComponentsToCanvas();
		arrows.add(arrow);
	}
	
	private void addArrow(int index, Arrow arrow){
		arrow.addComponentsToCanvas();
		arrows.add(index,arrow);
	}
	
	private void removeNode(Node node){
		node.removeComponentsFromCanvas();
		nodes.remove(node);
	}
	
	private void removeArrow(Arrow arrow){
		arrow.removeComponentsFromCanvas();
		arrows.remove(arrow);
	}
	
	private void addLabels() {
		operationNameTitle = new GLabel("Operation:",CONTENT_FONT_SIZE,CONTENT_FONT_SIZE);
		operationNameTitle.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		operationName = new GLabel("",CONTENT_FONT_SIZE*8,CONTENT_FONT_SIZE);
		operationName.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		
		operationNumberTitle = new GLabel("Number:",CONTENT_FONT_SIZE,CONTENT_FONT_SIZE*2);
		operationNumberTitle.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));
		operationNumber = new GLabel("",CONTENT_FONT_SIZE*8,CONTENT_FONT_SIZE*2);
		operationNumber.setFont(new Font(null, Font.BOLD, CONTENT_FONT_SIZE));

		canvas.addObject(operationNameTitle);
		canvas.addObject(operationName);
		canvas.addObject(operationNumberTitle);
		canvas.addObject(operationNumber);
	}
	
	
	
	private void shiftRight(int nodeIndex, int arrowIndex, double distance){
		if (nodeIndex >= 0) {
			for (int i = nodeIndex; i < nodes.size(); i++) {
				nodes.get(i).move(distance, 0);
			} 
		}
		
		if (arrowIndex >= 0) {
			for (int i = arrowIndex; i < arrows.size(); i++) {
				arrows.get(i).move(distance, 0);
			} 
		}
	}
	
	private void shiftLeft(int nodeIndex, int arrowIndex, double distance){
		if (nodeIndex >= 0) {
			for (int i = nodeIndex; i < nodes.size(); i++) {
				nodes.get(i).move(-distance, 0);
			} 
		}
		
		if (arrowIndex >= 0) {
			for (int i = arrowIndex; i < arrows.size(); i++) {
				arrows.get(i).move(-distance, 0);
			} 
		}
	}
	
	private void indicateIndex(int index){
		if(currentDataLength == 0){
			return;
		}
		GOval oval = new GOval(nodes.get(0).leftTopX, nodes.get(0).leftTopY, CELL_EDGE_LEN,CELL_EDGE_LEN);
		oval.setColor(Color.RED);
		oval.setVisible(false);
		canvas.addObject(oval);
		
		for (int i = 0; i <= index && i != currentDataLength; i++) {
			oval.setLocation(nodes.get(i).leftTopX, nodes.get(i).leftTopY);
			oval.setVisible(true);
			canvas.waitFor(500);
			oval.setVisible(false);
			canvas.waitFor(500);
		}
		if(index == currentDataLength){
			return;
		}
		oval.setVisible(true);
		canvas.waitFor(500);
		oval.setVisible(false);
		canvas.waitFor(500);
		oval.setVisible(true);
		canvas.waitFor(500);
		canvas.removeObject(oval);
		canvas.waitFor(1000);
	}
	
	
	
	
}
