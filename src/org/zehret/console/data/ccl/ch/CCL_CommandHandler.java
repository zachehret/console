package org.zehret.console.data.ccl.ch;

import org.zehret.console.data.ConsoleCommandHandler;
import org.zehret.console.data.ccl.cmd.*;

public final class CCL_CommandHandler extends ConsoleCommandHandler
{

	public CCL_CommandHandler()
	{
		super();		
		this.ADD_COMMAND(new INT_CMD_N1());
	//	this.ADD_COMMAND(new INT_CMD_00());
		this.ADD_COMMAND(new INT_CMD_01());
		this.ADD_COMMAND(new INT_CMD_02());
		this.ADD_COMMAND(new INT_CMD_03());
		this.ADD_COMMAND(new INT_CMD_04());
		this.ADD_COMMAND(new INT_CMD_05());
		this.ADD_COMMAND(new INT_CMD_06());
		this.ADD_COMMAND(new INT_CMD_07());
		this.ADD_COMMAND(new INT_CMD_08());
		this.ADD_COMMAND(new INT_CMD_09());
		
		try {
			this.alphabeticalSort();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

