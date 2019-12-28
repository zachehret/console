package org.zehret.console.data.ccl.cmd;

import java.awt.Color;
import java.awt.Font;

import org.zehret.console.Console;
import org.zehret.console.data.Command;
import org.zehret.console.data.error.Errors;
import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.FontSet;
import org.zehret.console.util.PL;

public class INT_CMD_02 extends Command
{
	public INT_CMD_02()
	{
		super("fontset","Set the font, size, and color for the console","fontset <defualt text hex> <default background hex> <font size> <font name>");
	}
	@Override
	public void process(String postPrefixArgs)
	{
		try
		{
			String cmd = postPrefixArgs;
			String[] args = cmd.split(" ", 4);
			
			System.out.println("Applying new fontset from command *[FG:"+args[0]+",BG:"+args[1]+",Font:"+args[2]+",Size:"+args[3]+",Modifier:"+args[4]+"]*");
			
			try
			{
				ConsoleConfiguration.TEXT_COLOR = Color.decode(args[0]);
				ConsoleConfiguration.BACKGROUND_COLOR = Color.decode(args[1]);
				ConsoleConfiguration.CONSOLE_FONT = new Font(args[2].replaceAll("_"," "), Font.PLAIN, Integer.parseInt(args[3]));
				//Console.consoleFontSet = new FontSet(Color.decode(args[0]),Color.decode(args[1]),new Font(args[2].replaceAll("_", " "),style,Integer.parseInt(args[3])));
			}catch(Exception e)
			{
				e.printStackTrace();
				PL.con("Error in command, see system console for details.", PL.WARN);
				PL.con(Errors.INCORRECT_COMMAND_ARGUMENT_FORMAT.getMessage());
			}
			
		//	Console.consoleWindow.applyFontSet();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			PL.con("Internal command usage: " + this.getPrefix());
			System.out.println("Internal command usage: " + this.getPrefix());
			PL.con(Errors.INCORRECT_COMMAND_FORMAT.getMessage());
		}
	}

}
