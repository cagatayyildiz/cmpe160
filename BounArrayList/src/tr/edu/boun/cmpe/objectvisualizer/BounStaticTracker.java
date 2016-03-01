package tr.edu.boun.cmpe.objectvisualizer;

import java.util.ArrayList;

public class BounStaticTracker {

	public static final int DELAY_BETWEEN_EVENTS = 1500;
	
	private static ObjectVisualizerContext sContext;
	private static EventDisplayThread displayThread;
	private static boolean staticContextActive = false;
	
	private static ArrayList<Event> eventQueue = new ArrayList<Event>();
	
	public static void init() {
		sContext = new ObjectVisualizerContext();
		staticContextActive = true;
		displayThread = new EventDisplayThread();
		
		displayThread.start();
	}
	
	public static boolean isStaticContextActive() {
		return staticContextActive;
	}
	
	public static ObjectVisualizerContext getStaticContext() {
		return sContext;
	}
	
	public static void pushEvent(Event e) {
		eventQueue.add(e);
	}
	
	public static class EventDisplayThread extends Thread {
		
		boolean running = true;
		
		@Override
		public void run() {
			
			while(running) {
				try {
					Thread.sleep(DELAY_BETWEEN_EVENTS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!eventQueue.isEmpty()) {
					Event e = eventQueue.get(0);
					e.getTarget().handleEvent(e);
					eventQueue.remove(0);
				}
				
			}
		}
		
	}
	
}
