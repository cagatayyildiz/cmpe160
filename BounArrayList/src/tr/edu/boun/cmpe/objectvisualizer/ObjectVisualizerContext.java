package tr.edu.boun.cmpe.objectvisualizer;

import java.util.ArrayList;

import tr.edu.boun.cmpe.objectvisualizer.event.EventSystem;

/**
 * Used to pass object references between classes.
 * 
 * @author triforce
 *
 */
public class ObjectVisualizerContext {
	
	private VisualizerFrame frame;
	
	private EventSystem eventSystem;
	
	private ArrayList<BounObject> addQueue = new ArrayList<BounObject>();
	private ArrayList<BounObject> objectList = new ArrayList<BounObject>();
	private ArrayList<BounObject> removeQueue = new ArrayList<BounObject>();
	
	public ObjectVisualizerContext() {
		frame = new VisualizerFrame(this);
		eventSystem = new EventSystem(this);
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
	
	public EventSystem getEventSystem() {
		return eventSystem;
	}
	
}
