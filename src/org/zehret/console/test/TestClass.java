package org.zehret.console.test;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import org.zehret.console.Console;
import org.zehret.console.gui.ConsoleWindow;
import org.zehret.console.util.ConsoleProperties;
import org.zehret.console.util.PL;

public class TestClass
{
	public static void main(String[] args)
	{
		System.out.println("**************************************************************************************");
		System.out.println("** This test program will utilize all parts of the console to test their basic use. **");
		System.out.println("**************************************************************************************");
		
		ConsoleProperties.SHOW_CMD_LINE = true;
		ConsoleProperties.PROMPTING_CONSOLE = true;
		ConsoleProperties.CONSOLE_FONT = new Font("Consolas", Font.PLAIN, 14);
		ConsoleProperties.DATE_TIME_PREFIX = ConsoleProperties.YYYYMMDD_HHMMSSms;
		ConsoleProperties.SHOW_ENTRY_IDENTIFIER = false;
		ConsoleProperties.FATAL_BACKGROUND_COLOR = Color.YELLOW;
		ConsoleProperties.CONSOLE_TITLE = "Test Program";
		ConsoleProperties.HIDE_VERSION = true;
		ConsoleProperties.FULLSCREEN_MODE = false;
		new Console();
		
		new PL(); 
		PL.con("normal, no value for severity");
		//PL.con(invalidInputMessage);
		//ConsoleWindow.help();
		ConsoleProperties.BACKGROUND_COLOR = Color.blue;
		PL.con("abnormal, extreme value for severity",43262546);
		ConsoleProperties.BACKGROUND_COLOR = Color.black;
		PL.con("Console only print; info prefix; no file.");
		PL.ExOUT("External Print out; fatal prefix; to file (When done)", PL.FATAL);
		PL.con("<p style=\"color:red;\">This is a paragraph.</p>");
		ConsoleProperties.MSG_CUSTOM_COLOR = Color.pink;
		PL.con_encap("Test message to test the encapsulation method", '*', PL.FATALC, true);
	//	Console.prompt("Prompt Test 1", Console.SHOWINPUTOPTION);
	//	Console.prompt("Prompt Test 2", Console.CENSORINPUTOPTION);
	//	Console.prompt("Prompt Test 3", Console.HIDEINPUTOPTION);
		System.out.println(System.currentTimeMillis());
		ConsoleProperties.BACKGROUND_CUSTOM_COLOR = Color.LIGHT_GRAY;
		ConsoleProperties.MSG_CUSTOM_COLOR = Color.black;
		PL.conPrintThreadStackTrace(Thread.currentThread(), "          Thread Stack          ", PL.INFOC, false);
		System.out.println(System.currentTimeMillis());
	//	PL.con("t\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt\n\tt",PL.SEVERE);
	//	PL.con(Console.consoleWindow.getOutputString());
		for(;;)
		{
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