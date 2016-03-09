package tr.edu.boun.cmpe.objectvisualizer.event;

import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;

/**
 * Base class to handle EventSystem events.
 * 
 * @author triforce
 * 
 * @see EventSystem
 */
public abstract class Event {
	
	private long delay = 0;
	
	private ObjectVisualizerContext context;
	
	public Event(ObjectVisualizerContext context) {
		this.context = context;
	}
	/**
	 * Run call for the event.
	 */
	public abstract void run();
	/**
	 * Adds a condition to event.
	 * Makes the event run only if the condition is true.
	 * 
	 * @return true if the event should run.
	 */
	public boolean shouldRun() {
		return true;
	}
	
	public ObjectVisualizerContext getContext() {
		return context;
	}
	
	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	public long getDelay() {
		return delay;
	}
}
