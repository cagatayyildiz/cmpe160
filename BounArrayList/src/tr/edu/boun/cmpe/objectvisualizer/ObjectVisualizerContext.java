package tr.edu.boun.cmpe.objectvisualizer;

import java.util.ArrayList;

/**
 * Used to pass object references between classes.
 * 
 * @author triforce
 *
 */
public class ObjectVisualizerContext {
	
	private VisualizerFrame frame;
	
	private ArrayList<BounObject> addQueue = new ArrayList<BounObject>();
	private ArrayList<BounObject> objectList = new ArrayList<BounObject>();
	private ArrayList<BounObject> removeQueue = new ArrayList<BounObject>();
	
	public ObjectVisualizerContext() {
		frame = new VisualizerFrame(this);
	}

	public VisualizerFrame getFrame() {
		return frame;
	}

	public ArrayList<BounObject> getObjectList() {
		return objectList;
	}
	
	public ArrayList<BounObject> getAddQueue() {
		return addQueue;
	}
	
	public ArrayList<BounObject> getRemoveQueue() {
		return removeQueue;
	}
	
	public void addObject(BounObject obj) {
		addQueue.add(obj);
	}
	
	public void removeObject(BounObject obj) {
		removeQueue.add(obj);
	}
	
}
