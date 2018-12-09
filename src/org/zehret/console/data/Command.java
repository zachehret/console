package org.zehret.console.data;

import java.util.ArrayList;

import org.zehret.console.data.error.Errors;
import org.zehret.console.util.PL;

public abstract class Command
{
	/**
	 * Flags are used to specify certain behaviors and parameters must NOT be longer than two (2) characters excluding any parameters.
	*/
	private ArrayList<String> flags = new ArrayList<String>();
	
	/**
	 * Command information
	 */
	final String prefix, description, syntax;
	
	/**
	 * Wouldn't use this.
	 */
	public Command()
	{ 
		this.prefix = "null0";
		this.description = "null1";
		this.syntax = "null2";
	}
	
	/**
	 * 
	 * @param prefix Command prefix (before arguments)
	 * @param description Command description
	 * 
	 * *Use only with commands that have no arguments, syntax = prefix.
	 */
	public Command(String prefix, String description)
	{
		this.prefix = prefix;
		this.description = description;
		this.syntax = prefix;
	}
	
	/**
	 * 
	 * @param prefix Command prefix (before arguments)
	 * @param description Command description
	 * @param syntax Command argument syntax
	 */
	public Command(String prefix, String description, String syntax)
	{
		this.prefix = prefix;
		this.description = description;
		this.syntax = syntax;
	}

	@Deprecated
	/**
	 * 
	 * This is currently supported by your OWN command handler.
	 * @return success?
	 */
	public boolean addFlag(String flag)
	{
		if(flag.length() > 2)
		{
			System.out.println("Failed to add flag " + flag + " because it is longer than the 2 character protocol: " + flag.length());
			return false;
		}
		else
		{
			try
			{
				System.out.println("Adding flag " + flag + " to command " + prefix);
				this.flags.add(flag);
				return true;
			}catch(Exception e)
			{
				e.printStackTrace();
				PL.con(Errors.FAILED_TO_ADD_FLAG.getMessage());
				return false;
			}
		}
	}

	public String getPrefix()
	{
		return this.prefix;
	}
	public String getDescription()
	{
		return this.description;
	}
	public String getSyntax()
	{
		return this.syntax;
	}
	
	public abstract void process(String postPrefixArgs) throws Exception;
}
