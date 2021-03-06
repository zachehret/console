package org.zehret.console.data.ccl.cmd;

import org.zehret.console.data.Command;
import org.zehret.console.util.ConsoleConfiguration;

public class INT_CMD_08 extends Command {

	public INT_CMD_08() {
		super("console configuration","Prints the current console configuration to the console.");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception {
		ConsoleConfiguration.PRINT_CONFIGURATION(true);
	}

}
