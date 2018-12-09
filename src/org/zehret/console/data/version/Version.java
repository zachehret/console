package org.zehret.console.data.version;

public class Version
{
	VersionFormat versionFormat;
	public Version(VersionFormat f)
	{
		this.versionFormat = f;
		if(!this.versionFormat.isLegal())
		{
			/*
			 * Space for default action if illegal format.
			 */
		}
	}
	public String getVersion()
	{
		return this.versionFormat.getVersion();
	}
}
