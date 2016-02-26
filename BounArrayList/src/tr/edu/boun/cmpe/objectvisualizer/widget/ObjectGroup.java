package tr.edu.boun.cmpe.objectvisualizer.widget;

import java.awt.Graphics;
import java.util.ArrayList;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;
import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.exception.BounVisualizerException;

public class ObjectGroup extends BounObject {

	public ObjectGroup() throws BounVisualizerException {
		super();
	}
	
	public ObjectGroup(ObjectVisualizerContext context) {
		super(context);
	}

	ArrayList<BounObject> addQueue = new ArrayList<BounObject>();
	ArrayList<BounObject> objectList = new ArrayList<BounObject>();
	
	public void addObject(BounObject obj) {
		addQueue.add(obj);
	}
	
	@Override
	public void updater(long dt) {
		super.updater(dt);
		for (BounObject obj : objectList) {
			obj.updater(dt);
		}
	}
	
	@Override
	public void update(long dt) {
		if(!addQueue.isEmpty()) {
			for (BounObject obj : addQueue) {
				objectList.add(obj);
			}
			addQueue.clear();
		}
	}

	@Override
	public void draw(Graphics canvas) {
		//FIXME This approach is not thread-safe. Change it later.
		//
		for (BounObject obj : objectList) {
			//store values.
			int realX = obj.getX();
			int realY = obj.getY();
			
			obj.setX(realX + getX());
			obj.setY(realY + getY());
			
			obj.draw(canvas);
			
			//reset values
			obj.setX(realX);
			obj.setY(realY);
		}
	}
	
	@Override
	public void setAlpha(float alpha) {
		super.setAlpha(alpha);
		//set alpha component of each child.
		for (BounObject obj : objectList) {
			obj.setAlpha(alpha);
		}
	}

}
