package org.zehret.console.data;

import java.util.ArrayList;

import org.zehret.console.Console;
import org.zehret.console.data.error.Errors;
import org.zehret.console.util.PL;

/**
 * @author Zach Ehret
 *	All outputs in this class must use the system output unless the out happens after the initialization.
 */
public abstract class CommandHandler
{
	ArrayList<Command> commands = new ArrayList<Command>();
	
	public CommandHandler()
	{
	}
	 
	/**
	 * @return success?
	 */
	public boolean ADD_COMMAND(Command c)
	{
		try
		{
			System.out.println("Adding command " + c.getPrefix());
			for(int n = 0; n < commands.size();n++)
			{
				if(c.getPrefix().equalsIgnoreCase(commands.get(n).getPrefix()))
				{
					PL.con(Errors.FAILED_TO_ADD_COMMAND_$_DUPLICATE_ENTRY.getMessage());
					throw new IllegalArgumentException("Duplicate Command Entry");
				}
				else
					continue;
			}
			commands.add(c);
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			PL.con(Errors.FAILED_TO_ADD_COMMAND.getMessage());
			return false;
		}
	}
	
	public Command getCommandAtIndex(int i)
	{
		try
		{
			return commands.get(i);
		}catch(Exception e)
		{
			e.printStackTrace();
			PL.con(Errors.INVALID_COMMAND_INDEX.getMessage());
			return null;
		}
	}
	
	/**
	 * @param prefix The command to be searched for.. not case sensitive.
	 * @return
	 */
	public int getCommandIndex(String prefix)
	{
		for(int i = 0; i < commands.size(); i++)
		{
			if(commands.get(i).getPrefix().equalsIgnoreCase(prefix))
				return i;
		}
		PL.con(Errors.UNKOWN_COMMAND_INDEX.getMessage());
		return -1;
	}
	
	public abstract void searchForCommand(String cmd);
	public abstract void processCommandSend(String cmd);
	
	public void checkIntegrity()
	{
		return;
	}
	public int getCommandCount()
	{
		return this.commands.size();
	}
	public void alphabeticalSort() throws Exception
	{
		System.out.println("Sorting command list...");
		String[] prefixes = new String[this.getCommandCount()];
		for(int n = 0; n < this.getCommandCount();n++)
		{
			prefixes[n] = this.getCommandAtIndex(n).getPrefix();
		}
		
		int i , j;
		String temp;
		for (i = 0; i < prefixes.length - 1;  i++ )
        {
			for ( j = i + 1;  j < prefixes.length;  j++ )
            {  
				if (prefixes[i].compareToIgnoreCase(prefixes[j]) > 0 )
                {                                             // ascending sort
					temp = prefixes[ i ];
					prefixes[i] = prefixes[j];    // swapping
					prefixes[j] = temp; 
                                    
                } 
            } 
        } 
		
		ArrayList<Command> NEW_ORDER_COMMANDS = new ArrayList<Command>();
		for(int n = 0; n < prefixes.length; n++)
		{
			NEW_ORDER_COMMANDS.add(this.getCommandAtIndex(this.getCommandIndex(prefixes[n])));
		}
		System.out.println("Applying changes...");
		this.commands = NEW_ORDER_COMMANDS;
		System.out.println("Changes applied.");
		
		/*
		  int i=0; 
          while(i<this.getCommandCount()-1 && i>=0)
          {
           if(this.getCommandAtIndex(i).getPrefix().compareTo(this.getCommandAtIndex(i+1).getPrefix()) >= 1 )
           {
               Command temp;
               temp = this.getCommandAtIndex(i);
               this.commands.add(i,this.getCommandAtIndex(i+1));
               this.commands.add(i+1,temp);
               i--;
           }
           else
           { 
        	   i++;
           }
       }
       */
	}
}