package org.zehret.console.data.ccl.cmd;

import org.zehret.console.Console;
import org.zehret.console.data.Command;
import org.zehret.console.data.error.Errors;
import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.PL;

public class INT_CMD_05 extends Command {

	public INT_CMD_05()
	{
		super("errorlist","Print the list of possible error codes");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception 
	{
		PL.con("Version #: " + ConsoleConfiguration.version.getVersion(),PL.INFO);
		PL.con("----------------------------------------------------------------------------------------------------------------");
		for(int n = 0; n < Errors.values().length; n++)
		{
			PL.con("- "+Errors.values()[n].toString());
		}
		PL.con("----------------------------------------------------------------------------------------------------------------");
	}

}
