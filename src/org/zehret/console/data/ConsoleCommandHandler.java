package org.zehret.console.data;

import org.zehret.console.data.error.Errors;
import org.zehret.console.util.PL;

public class ConsoleCommandHandler extends CommandHandler {

	public ConsoleCommandHandler()
	{
		
	}

	
	//col #aaafff #bbbccc
	@Override
	public void searchForCommand(String cmd)
	{
	//	PL.con("<CCH> Searching for command " + cmd);
		for(int n = 0; n < this.getCommandCount(); n++)
		{
			if(cmd.regionMatches(0, this.getCommandAtIndex(n).getPrefix(), 0, this.getCommandAtIndex(n).getPrefix().length()))
			{
				try
				{
					//PL.con("<CCH> Found command (" + cmd + ")@["+n+"] : i="+this.getCommandAtIndex(n).getPrefix());
					try
					{
						this.getCommandAtIndex(n).process(cmd.substring(this.getCommandAtIndex(n).getPrefix().length()+1, cmd.length()));
					}catch(StringIndexOutOfBoundsException e)
					{
						this.getCommandAtIndex(n).process(cmd.substring(this.getCommandAtIndex(n).getPrefix().length(), cmd.length()));
					}
					return;
				}catch(StringIndexOutOfBoundsException e0)
				{
					PL.con(Errors.INT_INCORRECT_COMMAND_SYNTAX.getMessage());
					return;
				}catch(Exception e1)
				{
					PL.con(Errors.INT_FAILED_TO_IDENTIFY_ARGUMENTS.getMessage());
					PL.con("<CCH> Command syntax: " + this.getCommandAtIndex(n).getSyntax());
					e1.printStackTrace();
					return;
				}
			}
		}
		PL.con(Errors.UNKOWN_INTERNAL_COMMAND.getMessage());
	}

	@Override
	public void processCommandSend(String cmd)
	{
		searchForCommand(cmd);
	}

}
