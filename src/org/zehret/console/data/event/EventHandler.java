package org.zehret.console.data.event;

import java.util.ArrayList;

import org.zehret.console.util.PL;

public class EventHandler implements Runnable {
	
	private ArrayList<Event> events = new ArrayList<Event>();

	public EventHandler() {
		
	}

	@Override
	public void run() {
		PL.con("Event Handler Started");
		while(true) {
			for(int n = 0; n < events.size(); n++) {
				if(events.get(n).eventDue())
					events.get(n).process();
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