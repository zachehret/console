package org.zehret.console.gui;

import org.zehret.console.data.Event;
import org.zehret.console.data.version.NumberVersionFormat;

@Deprecated
class ProcessingEvent extends Event
{

	public ProcessingEvent()
	{
		super("Processing","The event that handles a popup when something is loading/processing.",new NumberVersionFormat("A1"));
	}

}
