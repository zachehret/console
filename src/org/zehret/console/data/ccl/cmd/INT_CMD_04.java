package org.zehret.console.data.ccl.cmd;

import org.zehret.console.data.Command;
import org.zehret.console.data.error.Errors;

public class INT_CMD_04 extends Command {

	public INT_CMD_04()
	{
		super("endall","Terminate the console and running application");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception
	{
		System.exit(Integer.parseInt(Errors.PLANNED_EXIT.getCode().replaceAll("0x", ""),16));
	}

}
