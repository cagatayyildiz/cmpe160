package tr.edu.boun.cmpe.objectvisualizer;

public class Event {
	
	public static final int TYPE_CREATE = 0;
	public static final int TYPE_CHANGE_VALUE = 1;
	public static final int TYPE_DESTROY = 2;
	
	private int eventType;
	private BounObject target;
	
	public Event() {
		
	}
	
	public Event(int type, BounObject obj) {
		this.eventType = type;
		this.target = obj;
	}
	
	public int getEventType() {
		return eventType;
	}
	
	public BounObject getTarget() {
		return target;
	}
	
	public class Builder {
		private Event e;
		public Builder() {
			e = new Event();
		}
		
		public Builder setEventType(int type) {
			this.e.eventType = type;
			return this;
		}
		
		public Builder setTarget(BounObject target) {
			this.e.target = target;
			return this;
		}
		
		public Event build() {
			return e;
		}
	}
}
