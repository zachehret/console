package org.zehret.console.data;

import org.zehret.console.data.version.Version;
import org.zehret.console.data.version.VersionFormat;

@Deprecated
public abstract class Event
{
	private String name, description;
	private VersionFormat version;
	
	public Event(String name, String desc, VersionFormat ver) 
	{
		this.name = name;
		this.description = desc;
		this.version = ver;
	}
}
