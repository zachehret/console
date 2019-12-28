package org.zehret.console.data.event;

public abstract class Event {
	
	public static final int TIMED_EVENT = 0;
	public static final int TRIGGERED_EVENT = 1;
	
	private final int EVENT_TYPE;
	private long scheduledTime = 0;
	private boolean repeatable = false;
	private boolean delete = false;
	private long delayTime;
	public String id;

	public Event(String id, int eventType) {
		this.id = id;
		this.EVENT_TYPE = eventType;
	}

	public abstract void process();
	
	
	public abstract boolean checkTriggered();
	
	/**
	 * Checks if the event has been triggered by calling the checkTriggered() method.
	 * AND
	 * if the scheduled time is greater than the current time then the event is also to be triggered
	 * @return
	 */
	public final boolean eventTriggered() {
		if(this.EVENT_TYPE == Event.TRIGGERED_EVENT) {
			//The trigger will not be checked until the time is longer in the future. Thus allowing
			//the event itself to "save" whether or not it needs to fire when the scheduled time is no longer
			// in the future. Resetting the time will be up to the event handler..
			System.out.println(this.scheduledTime + " >< " + System.currentTimeMillis());
			if(this.isScheduledTimeFuture()) {
				return false;
			} else {
				return this.checkTriggered();
			}
		} else {
			throw new UnsupportedOperationException("Event type does not support condition based triggers.");
		}
	}
	
	public final boolean eventDue() {
		if(this.EVENT_TYPE == Event.TIMED_EVENT) //If the event is a timed event, return true if current time is greater than the scheduled time.
			return System.currentTimeMillis() > this.scheduledTime;
		else {
			throw new UnsupportedOperationException("Event type does not support time based triggers.");
		}
	}
	
	public void setDelayBetweenExecutions(long time) {
		this.delayTime = time;
	}
	
	/**
	 * Event will be triggered immediately unless a time is specified.
	 * @param time - System time in milliseconds
	 */
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

	public boolean isType(int type) {
		return this.EVENT_TYPE == type;
	}
	
	public boolean isScheduledTimeFuture() {
		return this.scheduledTime > System.currentTimeMillis();
	}

	public void tagForDeletion() {
		this.delete = true;		
	}
	public boolean isTaggedForDeletion() {
		return this.delete;
	}

}
