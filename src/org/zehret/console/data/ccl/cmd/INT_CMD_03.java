package org.zehret.console.data.ccl.cmd;

import org.zehret.console.Console;
import org.zehret.console.data.Command;
import org.zehret.console.util.ConsoleProperties;
import org.zehret.console.util.PL;

public class INT_CMD_03 extends Command {

	public INT_CMD_03()
	{
		super("conver","Shows the version of console you are running");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception
	{
		PL.con("Version #: " + ConsoleProperties.version.getVersion(),PL.INFO);
	}

}
