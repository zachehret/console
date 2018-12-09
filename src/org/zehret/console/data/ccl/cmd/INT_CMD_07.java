package org.zehret.console.data.ccl.cmd;

import org.zehret.console.Console;
import org.zehret.console.data.Command;

public class INT_CMD_07 extends Command {

	public INT_CMD_07()
	{
		super("ochr","Override the requirement for a valid command handler to create the command line");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception
	{
		Console.showCommandLine();
		Console.consoleWindow.addCommandLine();
	}

}
