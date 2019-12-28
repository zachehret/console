package org.zehret.console.data.ccl.cmd;

import java.awt.Color;

import org.zehret.console.Console;
import org.zehret.console.data.Command;
import org.zehret.console.data.error.Errors;
import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.FontSet;
import org.zehret.console.util.PL;

public class INT_CMD_01 extends Command
{

	public INT_CMD_01()
	{
		super("col","Set the console foreground and background colors for new entries","col <default_text> <default_background>");
	}
	@Override
	public void process(String postPrefixArgs)
	{
		String cmd = postPrefixArgs;
		String[] args = cmd.split(" ",2);
		System.out.println("Applying new color scheme from command *[FG:"+args[0]+",BG:"+args[1]+"]*");
		ConsoleConfiguration.TEXT_COLOR = Color.decode(args[0]);
		ConsoleConfiguration.BACKGROUND_COLOR = Color.decode(args[1]);
	}

}
