package org.zehret.console.data.ccl.cmd;

import org.zehret.console.Console;
import org.zehret.console.data.Command;

public class INT_CMD_06 extends Command {

	public INT_CMD_06()
	{
		super("exit","Close the console command line");
	}
 
	@Override
	public void process(String postPrefixArgs) throws Exception
	{
		Console.consoleWindow.consoleCommandLine.setVisible(false);
	}

}
