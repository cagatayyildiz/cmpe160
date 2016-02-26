package test;
import tr.edu.boun.cmpe.objectvisualizer.BounStaticTracker;
import tr.edu.boun.cmpe.objectvisualizer.widget.BounInteger;
import tr.edu.boun.cmpe.objectvisualizer.widget.Label;
import tr.edu.boun.cmpe.objectvisualizer.widget.Rectangle;


public class StudentPerspective {
	public static void main(String[] args){
		BounStaticTracker.init();
		
		BounInteger integer = new BounInteger(10);
		BounInteger int2 = new BounInteger(20);
		BounInteger int3 = new BounInteger(30);
		integer.setValue(100);
		int3.setValue(50);
		
		
	}
}
