package org.zehret.console;

import org.zehret.console.data.CommandHandler;
import org.zehret.console.data.ConsolePrintData;
import org.zehret.console.data.error.Errors;
import org.zehret.console.data.event.EventHandler;
import org.zehret.console.data.version.DateVersionFormat;
import org.zehret.console.data.version.Version;
import org.zehret.console.gui.NewConsoleWindow;
import org.zehret.console.gui.ProcessingPopup;
import org.zehret.console.util.ConsoleProperties;
import org.zehret.console.util.PL;

public class Console
{
	protected static String lastCommandEntry = ConsoleProperties.ILLEGAL_COMMAND_PREFIX;
	
	public static CommandHandler cmdHandler = null;
	
	public static boolean prompting = false;
	
	public static NewConsoleWindow consoleWindow;
	
	public static Version version = new Version(new DateVersionFormat("A16.2018.12.24-20:34:26"));
	
	private static ProcessingPopup processing;
	
	public static EventHandler eventHandler;
	
	/**
	 * use in prompts
	 */
	public static final int CENSORINPUTOPTION = 0, SHOWINPUTOPTION = 1, HIDEINPUTOPTION = 2, DEFAULTINPUTOPTION = 1;
	
	public static final int FORMAT_STRING = 234, FORMAT_DOUBLE = 235, FORMAT_FLOAT = 236, FORMAT_INTEGER = 237, FORMAT_LONG = 238, FORMAT_SHORT = 239, FORMAT_BYTE = 240, FORMAT_CHAR = 241, FORMAT_BOOL = 242;
	
	///**
	// * @param title The Title to be used for the console window.
	 //* @param cmd Command handler, if equal to null and showCommands = true, will throw illegal argument
	 //* @param showConsole Show the console at all? Useful for only logging.. True/false
	 //* @param showCommands Show the command line? True/false 
	 //* @param plr Program Log Prefix; Specifies the letter that is used in the first character of the log file name. '<char>'
	 //*/
	/**
	 * Setup ConsoleProperties prior to calling this constructor.
	 */
	public Console()
	{
		System.out.println("Console " + version.getVersion());
		System.out.println("Created by Zachary Ehret");
		System.out.println("2017-2019");
		System.out.println("According to configuration, commands should not contain or be \""+ ConsoleProperties.ILLEGAL_COMMAND_PREFIX + "\"");
		new PL();
		String consoleTitle = ConsoleProperties.CONSOLE_TITLE;
		
		if((ConsoleProperties.PROMPTING_CONSOLE)&&(!ConsoleProperties.SHOW_CMD_LINE))
			PL.con(ConsoleProperties.MESSAGE$CONFLICT_CFG_PROMPTING_CMDLINE);
		if((!ConsoleProperties.SHOW_CONSOLE)&&(ConsoleProperties.SHOW_CMD_LINE))
			PL.con(ConsoleProperties.MESSAGE$CONFLICT_CFG_SHOWCONSOLE_CMDLINE);
		
		cmdHandler = ConsoleProperties.COMMAND_HANDLER;
		try {
			cmdHandler.checkIntegrity();
		}catch(Exception e)
		{
			PL.con(Errors.INVALID_CMD_HANDLER.getMessage());
		}
		init();
		ConsoleProperties.PRINT_CONFIGURATION(false);
	}
	
	private static void init()
	{
		consoleWindow = new NewConsoleWindow();
		//consoleWindow.setUndecorated(ConsoleProperties.FULLSCREEN_MODE);
		consoleWindow.setVisible(ConsoleProperties.SHOW_CONSOLE);
		consoleWindow.updateFullscreen();
		if(ConsoleProperties.AUTOSTART_EVENT_HANDLER)
			new Thread(eventHandler = new EventHandler()).start();
		//new Thread(new SysOutCapture()).start();
	}
	
	public static boolean toggleConsole()
	{
		ConsoleProperties.SHOW_CONSOLE = !ConsoleProperties.SHOW_CONSOLE;
		consoleWindow.setVisible(ConsoleProperties.SHOW_CONSOLE);
		return ConsoleProperties.SHOW_CONSOLE;
	}

	public static void setConsoleCommandEntry(String s)
	{
		Console.lastCommandEntry = s;
		Console.consoleWindow.updateGUI();
	}
	public static void setLastCommandEntry(String s)
	{
		System.out.println("Processing command " + s);
		lastCommandEntry = s;
	}
	
	/**
	 * This will only return the last entered command. In any command handler, the string ConsoleProperties.ILLEGAL_COMMAND_PREFIX should be considered not changed. Console.newCommand() is available for use.
	 */
	public static String getLastCommandEntry()
	{
		return lastCommandEntry;
	}
	
	/**
	 * This should only be used for very primitive command handlers as it will interfere with any prompting program.
	 * @return The last command entered
	 */
	public static String getAndResetLastCommandEntry()
	{
		String s = new String(lastCommandEntry);
		lastCommandEntry = ConsoleProperties.ILLEGAL_COMMAND_PREFIX;
		return s;
	}
	
	/**
	 * 
	 * @return Returns whether or not the last command entry equals ConsoleProperties.ILLEGAL_COMMAND_PREFIX. Returns true if !=, false if ==
	 */
	public static boolean newCommand()
	{
		return !lastCommandEntry.equalsIgnoreCase(ConsoleProperties.ILLEGAL_COMMAND_PREFIX);
	}
	
	public static void showCommandLine()
	{
		System.out.println("Building cmd line gui");
		consoleWindow.setVisible(false);
		consoleWindow = null;
		consoleWindow = new NewConsoleWindow();
		consoleWindow.transferFocus();
		consoleWindow.setVisible(true);
	}
	@Deprecated
	public static String prompt(String prompt, boolean showInput)
	{
		if(showInput)
			return prompt(prompt,Console.SHOWINPUTOPTION);
		else if(!showInput)
			return prompt(prompt,Console.CENSORINPUTOPTION);
		return null;
	}
	
	public static String promptVerifiedInput(ConsolePrintData promptMessage, ConsolePrintData invalidInputMessage, int formatType, int inputOption) {
		
		while(true) {
			try {
				String prompt = prompt(promptMessage.getPrintMessage(), inputOption, promptMessage.getPrefix(), promptMessage.getAlertStatus());
				
				if(formatType == FORMAT_STRING) {
					return prompt;
				} else if (formatType == FORMAT_DOUBLE) {
					return ""+Double.parseDouble(prompt);
				} else if (formatType == FORMAT_FLOAT) {
					return ""+Float.parseFloat(prompt);
				} else if (formatType == FORMAT_INTEGER) {
					return ""+Integer.parseInt(prompt);
				} else if (formatType == FORMAT_LONG) {
					return ""+Long.parseLong(prompt);
				} else if (formatType == FORMAT_SHORT) {
					return ""+Short.parseShort(prompt);
				} else if (formatType == FORMAT_BYTE) {
					return ""+Byte.parseByte(prompt);
				} else if (formatType == FORMAT_CHAR) {
					return ""+prompt.charAt(0);
				} else if (formatType == FORMAT_BOOL) {
					return ""+Boolean.parseBoolean(prompt);
				} else {
					throw new IllegalArgumentException("Illegal format type. Allowable types are Console.FORMAT_STRING, Console.FORMAT_DOUBLE, Console.FORMAT_FLOAT, Console.FORMAT_INTEGER, Console.FORMAT_LONG, Console.FORMAT_SHORT, Console.FORMAT_BYTE, Console.FORMAT_CHAR, and Console.FORMAT_BOOL.");
				}
			}catch(NumberFormatException e) {
				PL.con(invalidInputMessage);
			}
		}
	}

	public static String prompt(String prompt,int inputOption) {
		return prompt(prompt,inputOption, PL.INFO, false);
	}
	 
	/**
	 * 
	 * @param prompt prompt message
	 * @return a string that is equal to the entered command.
	 */
	public static String prompt(String prompt,int inputOption, int prefix, boolean alert)
	{
		if(!ConsoleProperties.PROMPTING_CONSOLE) {
			PL.con_encap("Console must be defined as a prompting application.", '*',PL.FATAL,true);
			
			return "* invalid setup *";
		}
		lastCommandEntry = ConsoleProperties.ILLEGAL_COMMAND_PREFIX;
		PL.con(prompt,prefix,alert);
		String prePomptOutputWithoutPrompt = consoleWindow.getLastOutputFieldText().replaceAll(prompt, "");
		String prePomptOutputWithPrompt = consoleWindow.getLastOutputFieldText();
		long lastDotTime = System.currentTimeMillis();
		boolean stage0 = false,stage1 = false, stage2 = false, stage3 = false;
		String prompter = "";//used to store the prompter waiting character(s)
		String cmdInput = "";//used to detect a change in the input
		consoleWindow.validate();
		consoleWindow.forceVerticalScroll();
		while(true) {
			String commandLineInput = "" + consoleWindow.getCommandLineText();
			
			if(inputOption == Console.SHOWINPUTOPTION)
			{
				commandLineInput = consoleWindow.getCommandLineText();
			}
			else if((inputOption == Console.CENSORINPUTOPTION)||(inputOption == Console.HIDEINPUTOPTION))
			{
				int len = commandLineInput.length();
				StringBuilder sb = new StringBuilder(len);
				for(int i = 0; i < len; i++){
				    sb.append(ConsoleProperties.PROMPT_INPUT_CENSOR);
				} 
				commandLineInput = sb.toString(); 
			}
			
			if(System.currentTimeMillis() % 5 == 0)
			{
				prompting=true;
				if(!lastCommandEntry.equalsIgnoreCase(ConsoleProperties.ILLEGAL_COMMAND_PREFIX)) break;
			}
				
			if((System.currentTimeMillis() - lastDotTime == 500)&&(!stage0))
			{
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt);
				prompter = "_";
				stage0 = true;
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt +"\t >> "+ commandLineInput + prompter);
			}
			else if((System.currentTimeMillis() - lastDotTime == 1000)&&(!stage1))
			{
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt);
				prompter = " ";
				stage1 = true;
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt +"\t >> "+ commandLineInput + prompter);
			}
			else if((System.currentTimeMillis() - lastDotTime == 1500)&&(!stage2))
			{
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt);
				prompter = "_";
				stage2 = true;
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt +"\t >> "+ commandLineInput + prompter);
			}
			//else if((System.currentTimeMillis() - lastDotTime == 2000)&&(!stage3))
			//{
			//	consoleWindow.setLastOutputFieldText(prePrompt);
			//	consoleWindow.appendText(" \t"+consoleWindow.getCommandLineText());
			//}
			else if(System.currentTimeMillis() - lastDotTime >= 2500)
			{
				prompter = " ";
				lastDotTime = System.currentTimeMillis();
				stage0 = false;
				stage1 = false;
				stage2 = false;
				stage3 = false;
				consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt +"\t >> "+ commandLineInput + prompter);
			}
			
			try {
				if(!cmdInput.equals(commandLineInput)) {
					consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt +"\t >> "+ commandLineInput + prompter);
					cmdInput=commandLineInput;
				}
			}catch(NullPointerException e ) {}
		}
		
		if(inputOption == Console.SHOWINPUTOPTION)
		{
			consoleWindow.setLastOutputFieldText(prePomptOutputWithPrompt + " >> " + lastCommandEntry);
		}
		else if(inputOption == Console.CENSORINPUTOPTION)
		{
			int len = lastCommandEntry.length();
			StringBuilder sb = new StringBuilder(len);
			for(int i = 0; i < len; i++){
			    sb.append(ConsoleProperties.PROMPT_INPUT_CENSOR);
			}
			consoleWindow.setLastOutputFieldText(prePomptOutputWithoutPrompt + " " + prompt +" >> " + sb.toString());
		}
		else if(inputOption == Console.HIDEINPUTOPTION)
		{
			consoleWindow.removeLastOutputField();
		}
		else
		{
			consoleWindow.setLastOutputFieldText("* INVALID INPUT OPTION *");
		}
		prompting=false;
		String cmd = lastCommandEntry;
		lastCommandEntry = ConsoleProperties.ILLEGAL_COMMAND_PREFIX;
		return cmd;
	}
	
	public static String getTitle()
	{
		return consoleWindow.getTitle();
	}
	
	
	public static void setProgress(float percentage)
	{
		try
		{
			processing.setProgress(percentage);
		}catch(Exception e)
		{
			PL.con("Failed to set progress, has the processing popup been setup?",PL.WARN);
		}
	}
	/**
	 * Must be called to start the pop-up
	 * @param startingPercentage The percentage to start the bar at.
	 */
	@Deprecated
	public static void setupProgressPopup(float startingPercentage)
	{
		setupProgressPopup(startingPercentage,false);
	}
	
	/**
	 * Must be called to start the pop-up
	 * @param startingPercentage starting percentage for the bar
	 * @param indeterminate is the progress bar indeterminate?
	 */
	public static void setupProgressPopup(float startingPercentage,boolean indeterminate)
	{
		try
		{
			processing = new ProcessingPopup();
			processing.setVisible(true);
			processing.setBarIndeterminate(indeterminate);
			setProgress(startingPercentage);
		}catch(Exception e)
		{
			e.printStackTrace();
			PL.con("Could not setup progress pop-up.",PL.WARN);
		}
	}
	
	/**
	 * Must be called to end the pop-up
	 */
	public static void closeProgressPopup()
	{
		try
		{
			processing.dispose();
		}catch(Exception e)
		{
			e.printStackTrace();
			PL.con("Could not close progress pop-up.",PL.WARN);
		}
	}

	/**
	 * @return The progress pop-up progression value
	 */
	public static int getProgress()
	{
		try
		{
			return processing.getProgressValue();
		}catch(Exception e)
		{
			PL.con("Failed to get progress, has the processing pop-up been setup?",PL.WARN);
			return -1;
		}
	}
	
	public static boolean setBarIndeterminate(boolean t)
	{
		return processing.setBarIndeterminate(t);
	}
	
	
	public static boolean disableCommandLine()
	{
		return consoleWindow.setCommandLineEnabled(false);
	}
	public static boolean enableCommandLine()
	{
		return consoleWindow.setCommandLineEnabled(true);
	}
	
	/**
	 * 
	 * @return success
	 */
	public static boolean hide() {
		try {
			consoleWindow.setVisible(false);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @return success
	 */
	public static boolean show() {
		try {
			consoleWindow.setVisible(true);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	/**
	 * 
	 * @return success
	 */
	public static boolean toggleVisibleConsole() {
		try {
			consoleWindow.setVisible(!consoleWindow.isVisible());
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Pauses the program by first calling Thread.sleep and if that fails then looping until the desired time has elapsed.
	 * @param time milliseconds
	 */
	public static void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			long start = System.currentTimeMillis();
			while(System.currentTimeMillis() - start <= time) {
				
			}
		}
	}
}
