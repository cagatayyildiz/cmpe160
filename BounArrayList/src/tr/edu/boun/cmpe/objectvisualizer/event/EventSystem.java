package tr.edu.boun.cmpe.objectvisualizer.event;

import java.util.ArrayList;

import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;

/**
 * Controls all events that occur in the frame.
 * 
 * EventSystem queues up all the events and runs them one by one.
 * An event can be implemented to wait for a specific condition.
 * If the specific condition cannot be satisfied, then the event
 * will block the other events and further events will not be called,
 * until the condition is resolved.
 * 
 * A delay can be set to events to delay the current event. Upcoming
 * events will be blocked while waiting for the delay of the current event.
 * 
 * @author triforce
 *
 * @see Event 
 */
public class EventSystem {

	private ObjectVisualizerContext context;

	private ArrayList<Event> eventQueue = new ArrayList<Event>();

	private long timer = 0;
	
	public EventSystem(ObjectVisualizerContext context) {
		this.context = context;
	}

	public void pushEvent(Event e) {
		eventQueue.add(e);
	}

	public void runEvents(long dt) {
		if (!eventQueue.isEmpty()) {
			timer += dt;
			Event e = eventQueue.get(0);
			if (e.getDelay() < timer &&  e.shouldRun()) {
				e.run();
				eventQueue.remove(0);
				timer = 0;
			}
		}
	}

	public ObjectVisualizerContext getContext() {
		return context;
	}

}
