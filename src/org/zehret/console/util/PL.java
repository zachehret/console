package org.zehret.console.util;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.zehret.console.Console;
import org.zehret.console.data.error.Errors;

/**
 * 
 * @author Zachary Ehret
 * 
 * @version 0.9
 * This class is the worker class for printing to the console/system and for logging important information to file in the location of /log/console/out/T[YYYY]-[MM]-[DD]x[HH].[MM].[SS].[MS].log
 * All data dumps or debug information the console itself creates will be saved in various locations such as /log/console/pl/T[YYYY]-[MM]-[DD]x[HH].[MM].[SS].[MS].log OR /log/console/window/T[YYYY]-[MM]-[DD]x[HH].[MM].[SS].[MS].log OR /log/console/cmd/T[YYYY]-[MM]-[DD]x[HH].[MM].[SS].[MS].log OR /log/console/version/T[YYYY]-[MM]-[DD]x[HH].[MM].[SS].[MS].log
 */
public class PL
{

	public static final int INFO = 0, WARN = 1, SEVERE = 2, FATAL = 3, DEBUG = 4, PRIORITY_INFO = 5;
	public static final int INFOC = 6, WARNC = 7, SEVEREC = 8, FATALC = 9, DEBUGC = 10, PRIORITY_INFOC = 11 , NONEC = 12, NONE = 13;
	public static final String[] prefixes = {"[INFO]","[WARN]","[SEVR]","[FATAL]","[DEBUG]","[+INFO+]","[INFO]","[WARN]","[SEVR]","[FATAL]","[DEBUG]","[+INFO+]","",""};
	public static final boolean ALERT = true, NO_ALERT = false;
	
	
//	
//	/**
//	 * Color Identifiers
//	 */
//	public static final int BLACK = 0, RED = 1, BLUE = 2, YELLOW = 3, GREEN = 4, ORANGE = 5;
//	public static final String[] colorNames = {"BLACK","RED","BLUE","YELLOW","GREEN","ORANGE"};
//	public static final Color[] colors = {Color.black, Color.red, Color.blue, Color.yellow, Color.green};
//	/**
//	 * Format Identifiers
//	 */
//	public static final int PLAIN = 0, BOLD = 1, ITALIC = 2, UNDERLINE = 3, STRIKETHROUGH = 4;
//	public static final String[] formats = {"PLAIN","BOLD","ITALIC","UNDERLINE","STRIKETHROUGH"};
	
	
	/**
	 * Used to print consistent error messages
	 */
	public static String con(String pre,Errors e,String post, int severity, boolean alert) {
		return con(pre + e.getMessage() + post, severity,alert);
	}
	
	/**
	 * Prints to the console window ONLY. Encapsulates the msg with a chosen character in a message format.
	 */
	public static String con_encap(String msg, char e, int severity, boolean alert) {
		boolean temp = ConsoleProperties.AUTO_RESET_CUSTOM_COLOR;
		String top_bottom = "";
		for(int n = 0; n < msg.length() + 4; n++) {
			top_bottom+=e;
		}
		ConsoleProperties.AUTO_RESET_CUSTOM_COLOR = false;
						con(top_bottom,severity,alert);
		String msgprint = con(e + " " + msg + " " + e,severity,alert);
						con(top_bottom,severity,alert);
		ConsoleProperties.AUTO_RESET_CUSTOM_COLOR = temp;
		if(ConsoleProperties.AUTO_RESET_CUSTOM_COLOR)
			ConsoleProperties.resetCustomColors();
		return msgprint;
	}
	
	/**
	 * Prints only to the current line. Does not jump to next line. Use only in text maps.
	 * @param msg
	 * @return msg with any modifications
	 */
	public static String consl(String msg)
	{
		Console.consoleWindow.lineAppend(msg);
		return msg;
	}
	
	
	/**
	 * 
	 * Prints to the console window ONLY. Uses default info prefix
	 * 
	 * @param msg to be printed
	 * @return msg with appendices
	 */
	public static String con(String msg)
	{
		return OUT(msg,0,true,false,false,"");
	}
	/**
	 * 
	 * @param msg msg to be prinded
	 * @param pre prefix number
	 * @param alert play the notification sound
	 * @return msg with appendices
	 */
	public static String con(String msg, int severity, boolean alert)
	{
		if(alert)
		{
			Toolkit.getDefaultToolkit().beep();
		}
		return OUT(msg,severity,true,false,false,"");	
	}
	/**
	 * Prints to the console window ONLY.
	 * 
	 * @param msg to be printed
	 * @param pre prefix number
	 * @return msg with appendices
	 */
	public static String con(String msg,int severity)
	{
		return OUT(msg,severity,true,false,false,"");	
	}
	
	/**
	 * 
	 * Prints to the system window ONLY. Uses default info prefix
	 * 
	 * @param msg to be printed
	 * @return msg with appendices
	 */
	public static String sys(String msg)
	{
		return OUT(msg,0,false,true,false,"");
	}
	
	/**
	 * Prints to the system window ONLY.
	 * 
	 * @param msg to be printed
	 * @param pre prefix number
	 * @return msg with appendices
	 */
	public static String sys(String msg, int severity)
	{
		return OUT(msg,severity,false,true,false,"");
	}
	
	
	/**
	 * Prints to everything and saved logs
	 * @param string
	 * @param pre
	 */
	public static void ExOUT(String msg,int severity)
	{
		System.out.println("Please note this is not completed yet. PL.ExOut");
		OUT(msg,severity,true,true,true,"/logs/console/out/"+PL.getFileNameAndExtInFormat(ConsoleProperties.LOG_CHARACTER_PREFIX));
	}
	
	
	
	private static String OUT(String msg, int severity,boolean console, boolean systemOut,boolean file, String fileLocation)
	{
		return OUT(msg,severity,console,systemOut,file,fileLocation,false,true);
	}
	/**
	 * Used as as the universal OUT method. The date and prefix are inserted here.
	 * 
	 * @param msg
	 * @param console
	 * @param systemOut
	 * @param file
	 * @param fileLocation
	 * @param overwriteFile
	 * @param createFile
	 * @return
	 */
	private static String OUT(String msg, int PRE, boolean console,boolean systemOut, boolean file, String fileLocation, boolean overwriteFile, boolean createFile)	{
		String pref = "";
		try	{
			if((PRE == PL.NONEC) || (PRE == PL.NONE)) {
				pref = " ";
			} else {
				pref = " " + prefixes[PRE] + " ";
			}
		}catch(Exception e) {
			pref = "[?]";
		}
		finally {
			msg = resolveDateTimeFormat() + pref + msg;
		}
		if(console) {
			try {
				msg.replaceAll(System.lineSeparator(), "\n\t");
				if(PRE == PL.INFO)
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.TEXT_COLOR, ConsoleProperties.BACKGROUND_COLOR);
				else if(PRE == PL.WARN)
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.WARN_MSG_COLOR, ConsoleProperties.WARN_BACKGROUND_COLOR);
				else if(PRE == PL.SEVERE)
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.SEVERE_MSG_COLOR, ConsoleProperties.SEVERE_BACKGROUND_COLOR);
				else if(PRE == PL.DEBUG)
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.DEBUG_MSG_COLOR, ConsoleProperties.DEBUG_BACKGROUND_COLOR);
				else if(PRE == PL.FATAL)
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.FATAL_MSG_COLOR, ConsoleProperties.FATAL_BACKGROUND_COLOR);
				else if(PRE == PL.PRIORITY_INFO)
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.PRIORITY_INFO_MSG_COLOR, ConsoleProperties.PRIORITY_INFO_BACKGROUND_COLOR);
				else if((PRE >= PL.INFOC)&&(PRE <= PL.NONEC)) {
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.MSG_CUSTOM_COLOR, ConsoleProperties.BACKGROUND_CUSTOM_COLOR);
					if(ConsoleProperties.AUTO_RESET_CUSTOM_COLOR) {
						ConsoleProperties.MSG_CUSTOM_COLOR = ConsoleProperties.TEXT_COLOR;
						ConsoleProperties.BACKGROUND_CUSTOM_COLOR = ConsoleProperties.BACKGROUND_COLOR;
					}
				}
				else
					Console.consoleWindow.insertOutput(msg, ConsoleProperties.TEXT_COLOR, ConsoleProperties.BACKGROUND_COLOR);
			}catch(Exception e)
			{
				msg.replaceAll("\n","");
				OUT(msg + " " + Errors.FAILED_TO_PRINT_TO_CONSOLE.getMessage(),-1,false,true,false,"",false,false);
			}
		}
		if(systemOut) {
			System.out.println(msg);
		}
		if(file) {
			//PL.con("<test message only, remove.>Writing to file following defined rules... " + fileLocation);
			if(overwriteFile)
			{
				PL.con("Write to file is not complete <File Overwrite == true>",PL.WARN);
			}
			else
			{
				PL.con("Write to file is not complete <File Overwrite == false>",PL.WARN);
			}
			if(createFile)
			{
				PL.con("Write to file is not complete <File Create == true>",PL.WARN);
			}
			else
			{
				PL.con("Write to file is not complete <File Create == false>",PL.WARN);
			}
		}
		return msg;
	}
	
	public static boolean createWriteNewFile(String path, String[] data) {
		try {
			List<String> lines = Arrays.asList(data);
			Path file = Paths.get(path);
			File f = new File(file.toString());
			f.mkdirs();
			Files.write(file, lines, Charset.forName("UTF-8"));
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static String resolveDateTimeFormat() {
		String dateTime = "";
		if(ConsoleProperties.DATE_TIME_PREFIX.equals(ConsoleProperties.FULL_DATE_TIME))
			return new Date().toString();

		Calendar c = new GregorianCalendar();
		dateTime = (ConsoleProperties.DATE_TIME_PREFIX);
		dateTime = dateTime.replaceAll(ConsoleProperties.YEAR_CODE, "" + c.get(Calendar.YEAR));
		dateTime = dateTime.replaceAll(ConsoleProperties.MONTH_CODE, String.format("%02d", (c.get(Calendar.MONTH)+1)));
		dateTime = dateTime.replaceAll(ConsoleProperties.DATE_CODE, String.format("%02d", (c.get(Calendar.DAY_OF_MONTH))));
		dateTime = dateTime.replaceAll(ConsoleProperties.HOUR_CODE, String.format("%02d", (c.get(Calendar.HOUR_OF_DAY))));
		dateTime = dateTime.replaceAll(ConsoleProperties.MINUTE_CODE, String.format("%02d", (c.get(Calendar.MINUTE))));
		dateTime = dateTime.replaceAll(ConsoleProperties.SECONDS_CODE, String.format("%02d", (c.get(Calendar.SECOND))));
		dateTime = dateTime.replaceAll(ConsoleProperties.MILLISECONDS_CODE, String.format("%04d", (c.get(Calendar.MILLISECOND))));
		dateTime = dateTime.replaceAll(ConsoleProperties.EPOCH_CODE, ""+System.currentTimeMillis());
		
		return dateTime;
	}


	public static String getFileNameAndExtInFormat(char preCharacter)
	{	
		Calendar c = new GregorianCalendar();
		try
		{
			return preCharacter+(c.get(GregorianCalendar.YEAR))+"-"+c.get(GregorianCalendar.MONTH)+"-"+c.get(GregorianCalendar.DAY_OF_MONTH)+"x"+c.get(GregorianCalendar.HOUR_OF_DAY)+"."+c.get(GregorianCalendar.MINUTE)+"."+c.get(GregorianCalendar.SECOND)+"."+c.get(GregorianCalendar.MILLISECOND)+".log";
		}catch(Exception e)
		{
			e.printStackTrace();
			return System.currentTimeMillis() + " Error " + e.getMessage();
		}
	}
	
	public static char TFYNflip(boolean bool)
	{
		if(bool)
			return 'Y';
		else if(!bool)
			return 'N';
		else
			return '?';
	}
	
	/**
	 * @param b
	 * @return true if y,Y false if n,N or any other.
	 */
	public static boolean getYNValue(char b) {
		if((b == 'Y')||(b == 'y')) {
			return true;
		} else if((b == 'N')||(b == 'n')) {
			return false;
		} else {
			return false;
		}
	}
	
	public static String getExceptionStackTrace(Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	public static String[] getThreadStackTrace(Thread t) {
		StackTraceElement[] stack = t.getStackTrace();
		String[] stackStrings = new String[stack.length];
		for(int n = 0; n < stack.length; n++) {
			stackStrings[n] = stack[n].toString();
		}
		return stackStrings;
	}
	
	public static String conPrintThreadStackTrace(Thread t, String description, int severity, boolean alert) {
		try {
			boolean temp = ConsoleProperties.AUTO_RESET_CUSTOM_COLOR;
			ConsoleProperties.AUTO_RESET_CUSTOM_COLOR = false;
			
			String report = con_encap(description, '*', severity, alert);
			String[] stack = getThreadStackTrace(t);
			for(int n = 2; n < stack.length; n++) {
				report += con(stack[n], severity, PL.NO_ALERT);
			}
			report += con_encap(description, '*', severity, alert);
			ConsoleProperties.AUTO_RESET_CUSTOM_COLOR = temp;
			return report;
		} catch (Exception e) { return e.getMessage(); }
	}
}