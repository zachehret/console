package org.zehret.console.data.ccl.cmd;

import org.zehret.console.Console;
import org.zehret.console.data.Command;
import org.zehret.console.util.PL;

public class INT_CMD_N1 extends Command
{
	public INT_CMD_N1()
	{
		super("help","Shows the internal command help");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception
	{
		String printOut = "";
		for(int n = 0;n < Console.consoleWindow.consoleCommandLine.cmdHandler.getCommandCount();n++)
		{
			try 
			{
				printOut += (Console.consoleWindow.consoleCommandLine.cmdHandler.getCommandAtIndex(n).getPrefix() + " | " + Console.consoleWindow.consoleCommandLine.cmdHandler.getCommandAtIndex(n).getDescription() + " | " + Console.consoleWindow.consoleCommandLine.cmdHandler.getCommandAtIndex(n).getSyntax() +"\n");
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		PL.con("Internal Help Printout\n"+printOut);
	}

}
