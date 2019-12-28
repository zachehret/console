package org.zehret.console.data.ccl.cmd;

import org.zehret.console.Console;
import org.zehret.console.data.Command;
import org.zehret.console.util.ConsoleConfiguration;

public class INT_CMD_09 extends Command {

	public INT_CMD_09() {
		super("linewrap toggle","Toggle the linewrap property for ALL new and existing entries.");
	}

	@Override
	public void process(String postPrefixArgs) throws Exception {
		ConsoleConfiguration.ENTRY_LINEWRAP = !ConsoleConfiguration.ENTRY_LINEWRAP;
		Console.consoleWindow.setWordWrap(ConsoleConfiguration.ENTRY_LINEWRAP);
	}

}
