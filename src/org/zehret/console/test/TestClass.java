package org.zehret.console.test;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.naming.OperationNotSupportedException;

import org.zehret.console.Console;
import org.zehret.console.data.event.builtin.CaptureSysOutSettingChangeEvent;
import org.zehret.console.gui.ConsoleWindow;
import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.ConsoleProperties;
import org.zehret.console.util.PL;

public class TestClass
{
	public static void main(String[] args) throws OperationNotSupportedException
	{
		System.out.println("**************************************************************************************");
		System.out.println("** This test program will utilize all parts of the console to test their basic use. **");
		System.out.println("**************************************************************************************");
		
		ConsoleConfiguration.SHOW_CMD_LINE = true;
		ConsoleConfiguration.PROMPTING_CONSOLE = true;
		ConsoleConfiguration.CONSOLE_FONT = new Font("Consolas", Font.PLAIN, 14);
		ConsoleConfiguration.DATE_TIME_PREFIX = ConsoleConfiguration.YYYYMMDD_HHMMSSms;
		ConsoleConfiguration.SHOW_ENTRY_IDENTIFIER = false;
		ConsoleConfiguration.FATAL_BACKGROUND_COLOR = Color.YELLOW;
		//ConsoleConfiguration.CONSOLE_TITLE = "Test Program";
		//ConsoleConfiguration.HIDE_VERSION = true;
		ConsoleConfiguration.FULLSCREEN_MODE = false;
		ConsoleConfiguration.AUTOSTART_EVENT_HANDLER = true;
		
		ConsoleConfiguration.CONSOLE_FONT = new Font("Courier New", Font.PLAIN, 16);
		new Console();
	//	Console.eventHandler.addEvent(new CaptureSysOutSettingChangeEvent());
		
		new PL(); 
		PL.con("normal, no value for severity");
		//PL.con(invalidInputMessage);
		//ConsoleWindow.help();
		ConsoleConfiguration.BACKGROUND_COLOR = Color.blue;
		PL.con("abnormal, extreme value for severity",43262546);
		ConsoleConfiguration.BACKGROUND_COLOR = Color.black;
		PL.con("Console only print; info prefix; no file.");
		PL.ExOUT("External Print out; fatal prefix; to file (When done)", PL.FATAL);
		PL.con("<p style=\"color:red;\">This is a paragraph.</p>");
		ConsoleConfiguration.MSG_CUSTOM_COLOR = Color.pink;
		PL.con_encap("Test message to test the encapsulation method", '*', PL.FATALC, true);
	//	Console.prompt("Prompt Test 1", Console.SHOWINPUTOPTION);
	//	Console.prompt("Prompt Test 2", Console.CENSORINPUTOPTION);
	//	Console.prompt("Prompt Test 3", Console.HIDEINPUTOPTION);
		System.out.println(System.currentTimeMillis());
		ConsoleConfiguration.BACKGROUND_CUSTOM_COLOR = Color.LIGHT_GRAY;
		ConsoleConfiguration.MSG_CUSTOM_COLOR = Color.black;
		PL.conPrintThreadStackTrace(Thread.currentThread(), "          Thread Stack          ", PL.INFOC, false);
		System.out.println(System.currentTimeMillis());
	//	PL.con("t\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt",PL.SEVERE);
	//	PL.con(Console.consoleWindow.getOutputString());
		for(;;)
		{
			ConsoleConfiguration.CAPTURE_SYS_OUT = !ConsoleConfiguration.CAPTURE_SYS_OUT;
			/*
			if(System.currentTimeMillis()- lastUpdateTime >= 1000 + r.nextInt(500)-250)
			{
				Console.setProgress(Console.getProgress()+1);
				lastUpdateTime = System.currentTimeMillis();
			}
			if(Console.getProgress() >= 100)
			{
				Console.closeProgressPopup();
				Console.setupProgressPopup(0);
			}
			*/
			//if(System.currentTimeMillis() % 250 == 0)
				//PL.con("Hello! " + new Random().nextLong(),new Random().nextInt(PL.prefixes.length));
		}
	}
}