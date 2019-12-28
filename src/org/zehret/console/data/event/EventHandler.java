package org.zehret.console.data.event;

import java.util.ArrayList;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.zehret.console.util.PL;

public class EventHandler implements Runnable {
	
	private ArrayList<Event> events = new ArrayList<Event>();

	public EventHandler() {
		
	}

	@Override
	public void run() {
		PL.con("|BETA| Event Handler Started");
		
		while(true) {
			for(int n = 0; n < events.size(); n++) {
				if(events.get(n).isType(Event.TIMED_EVENT))
				{
					if(events.get(n).eventDue()) {
						events.get(n).process();
						events.get(n).setScheduledTime(System.currentTimeMillis() + events.get(n).getDelayTime());
						if(!events.get(n).getRepeatable()) {
							events.get(n).tagForDeletion();
						}
					}
				} else if(events.get(n).isType(Event.TRIGGERED_EVENT)) {
					if(events.get(n).eventTriggered()) {
						events.get(n).process();
						//Set the scheduled time to the current time plus the delay time. This will great a minimum delay between
						//event fires. If the event is ready to fire but it is too soon, the event will trigger when the scheduled
						//time is no longer in the future.
						events.get(n).setScheduledTime(System.currentTimeMillis() + events.get(n).getDelayTime());
						if(!events.get(n).getRepeatable()) {
							events.get(n).tagForDeletion();
						}
					}
				}
			}
			removeTaggedEvents();
		}
	}
	
	private void removeTaggedEvents() {
		for(int i = 0; i < events.size(); i++) {
			if(events.get(i).isTaggedForDeletion()) {
				System.out.println("Event " + events.get(i).id + " is not repeatable. Removing...");
				events.remove(i);
				removeTaggedEvents();
			}
		}
	}
	
	public boolean removeEvent(String id) {
		for(int n = 0; n < events.size(); n++) {
			if(events.get(n).checkID(id)) {
				events.remove(n);
				return true;
			}
		}
		return false;
	}
	public int addEvent(Event e) {
		this.events.add(e);
		System.out.println("Added event >" + e.id + "<");
		return events.size();
	}
	
	public Event getEvent(String id) {
		for(int n = 0; n < events.size(); n++) {
			if(events.get(n).checkID(id)) {
				return events.get(n);
			}
		}
		return null;	
	}
}