package org.zehret.console.data.event.builtin;

import javax.naming.OperationNotSupportedException;

import org.zehret.console.data.event.Event;
import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.PL;

public class CaptureSysOutSettingChangeEvent extends Event {
	
	private boolean _CFG_CAPTURE_SYS_OUT = ConsoleConfiguration.CAPTURE_SYS_OUT;
	
	public CaptureSysOutSettingChangeEvent() throws OperationNotSupportedException {
		super("Capture_Sys_Out_Setting_Change_Event", Event.TRIGGERED_EVENT);
		this.setDelayBetweenExecutions(50);
		this.setRepeatable(true);
		this.setScheduledTime(0);
		throw new OperationNotSupportedException("Feature is not yet complete");
	}

	@Override
	public void process() {
		PL.con(this.id + " > event fired");
	}

	@Override
	public boolean checkTriggered() {
		return (ConsoleConfiguration.CAPTURE_SYS_OUT != this._CFG_CAPTURE_SYS_OUT);
	}
}
