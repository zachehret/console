package org.zehret.console.data.event;

public abstract class Event {
	
	public static final int TIMED_EVENT = 0;
	
	private final int EVENT_TYPE;
	private long scheduledTime;
	private boolean repeatable = false;
	private long delayTime;
	public String id;

	public Event(String id, int eventType) {
		this.id = id;
		this.EVENT_TYPE = eventType;
	}

	abstract public void process();
	
	public boolean eventDue() {
		if(this.EVENT_TYPE == Event.TIMED_EVENT)
			return System.currentTimeMillis() > this.scheduledTime;
		else
			return false;
	}
	
	public void setDelayBetweenExecutions(long time) {
		this.delayTime = time;
	}
	public void setScheduledTime(long time) {
		this.scheduledTime = time;
	}
	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}
	public boolean checkID(String id) {
		return this.id.equals(id);
	}
	public long getDelayTime() {
		return this.delayTime;
	}
	public boolean getRepeatable() {
		return this.repeatable;
	}
}
