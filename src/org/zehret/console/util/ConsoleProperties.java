package org.zehret.console.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

import org.zehret.console.Console;
import org.zehret.console.data.CommandHandler;
import org.zehret.console.data.error.Errors;

public class ConsoleProperties {
	
	/**
	 * Whether or not special behaviors are enabled for console prompts. 
	 * Use with true/false.
	 */
	public static boolean PROMPTING_CONSOLE = false;
	
	/**
	 * Use with any string to set the title of the console window.
	 */
	public static String CONSOLE_TITLE = "[No Title]";
	
	/**
	 * Hide the console version in the title. Default false
	 */
	public static boolean HIDE_VERSION = false;
	
	
	public static Image CONSOLE_ICON = null;
	
	/**
	 * Whether or not the console will be visible.
	 * Use with true/false.
	 */
	public static boolean SHOW_CONSOLE = true;
	
	/**
	 * Whether or not the command line is shown.
	 * Use with true/false.
	 */
	public static boolean SHOW_CMD_LINE = false;
	
	/**
	 * The console font face to use, the style, and size. Not to be used for color setting, see BACKGROUND_COLOR and TEXT_COLOR.
	 */
	public static Font CONSOLE_FONT = new Font("Courier New", Font.PLAIN, 12);
	
	/**
	 * The background color of the console window.
	 */
	public static Color BACKGROUND_COLOR = Color.BLACK;
	
	/**
	 * The color of the text in the output window. Also associated with info outputs.
	 */
	public static Color TEXT_COLOR = Color.LIGHT_GRAY;
	
	public static Color WARN_MSG_COLOR = Color.decode("#FF8C00");
	public static Color SEVERE_MSG_COLOR = Color.red;
	public static Color DEBUG_MSG_COLOR = Color.blue;
	public static Color FATAL_MSG_COLOR = Color.decode("#f442e5");
	public static Color PRIORITY_INFO_MSG_COLOR = Color.yellow;
	
	public static Color WARN_BACKGROUND_COLOR = Color.black;
	public static Color SEVERE_BACKGROUND_COLOR = Color.black;
	public static Color DEBUG_BACKGROUND_COLOR = Color.black;
	public static Color FATAL_BACKGROUND_COLOR = Color.black;
	public static Color PRIORITY_INFO_BACKGROUND_COLOR = Color.black;
	public static Color BACKGROUND_CUSTOM_COLOR = Color.black;
	
	/**
	 * Used with infoc through fatalc PL codes. Set the color before calling print method. Default is white. If autoreset is enabled, this color will be set to white after each print.
	 */
	public static Color MSG_CUSTOM_COLOR = Color.white;
	
	/**
	 * Should the ALL_MSG_CUSTOM_COLOR be reset after each print. If true, custom color will be set to white after each print to the console.
	 */
	public static boolean AUTO_RESET_CUSTOM_COLOR = true;
	
	/**
	 * The character to be placed in front of the names of log files.
	 */
	public static char LOG_CHARACTER_PREFIX = '0';
	
	public static char PROMPT_INPUT_CENSOR = '*';
	
	/**
	 * The command handler to use for the console. Commands are entered in the command line.
	 * By default is non-existent.
	 */
	public static CommandHandler COMMAND_HANDLER = null;
	
	/**
	 * Whether the id of the output entry will be included.
	 */
	public static boolean SHOW_ENTRY_IDENTIFIER = true;
	
	/**
	 * The minimum time between each GUI Update. Lower settings cause performance and display problems.
	 * RECOMMENDED VALUE: 2ms/default
	 */
	public static long GUI_UPDATE_DELAY = 2;
	
	/**
	 * The maximum number of output entries to save in the console. Higher settings cause significant performance problems.
	 * RECOMMENDED VALUE: 1000/default.
	 */
	public static long MAX_ENTRY_LIMIT = 1000;
	
	/**
	 * This is the string that no command should be or contain. This string is used to detect an input when the console is waiting for input during a prompt.
	 * The default value is recommended. 512 characters long uuid.
	 */
	public static String ILLEGAL_COMMAND_PREFIX = "4cee82b992804adc";
	
	public static final String YEAR_CODE = new String("YYYY");
	public static final String MONTH_CODE = new String("MO");
	public static final String DATE_CODE = new String("DD");
	public static final String HOUR_CODE = new String("HH");
	public static final String MINUTE_CODE = new String("MN");
	public static final String SECONDS_CODE = new String("SS");
	public static final String MILLISECONDS_CODE = new String("ms");
	
	public static final String EPOCH_CODE = new String("epoch");
	
	public static final String STANDARD_DATE_DELIMINATOR = new String(".");
	public static final String STANDARD_TIME_DELIMINATOR = new String(":");
	public static final String STANDARD_SPACE_DELIMINATOR = new String(" ");
	public static final String STANDARD_UNITOF_DELIMINATOR = new String(".");
	
	public static final String YYYYMMDD_HHMMSS = new String(YEAR_CODE + STANDARD_DATE_DELIMINATOR + MONTH_CODE + STANDARD_DATE_DELIMINATOR + DATE_CODE + STANDARD_SPACE_DELIMINATOR + HOUR_CODE + STANDARD_DATE_DELIMINATOR + MINUTE_CODE + STANDARD_TIME_DELIMINATOR + SECONDS_CODE);
	public static final String YYYYMMDD_HHMMSSms = new String(YEAR_CODE + STANDARD_DATE_DELIMINATOR + MONTH_CODE + STANDARD_DATE_DELIMINATOR + DATE_CODE + STANDARD_SPACE_DELIMINATOR + HOUR_CODE + STANDARD_TIME_DELIMINATOR + MINUTE_CODE + STANDARD_TIME_DELIMINATOR + SECONDS_CODE + STANDARD_UNITOF_DELIMINATOR + MILLISECONDS_CODE);
	public static final String HHMM = new String(HOUR_CODE + STANDARD_TIME_DELIMINATOR + MINUTE_CODE);
	public static final String HHMMSS = new String(HOUR_CODE + STANDARD_TIME_DELIMINATOR + MINUTE_CODE + STANDARD_TIME_DELIMINATOR + SECONDS_CODE);
	public static final String HHMMSSms = new String(HOUR_CODE + STANDARD_TIME_DELIMINATOR + MINUTE_CODE + STANDARD_TIME_DELIMINATOR + SECONDS_CODE + STANDARD_UNITOF_DELIMINATOR + MILLISECONDS_CODE);
	public static final String YYYYMMDD = new String(YEAR_CODE + STANDARD_DATE_DELIMINATOR + MONTH_CODE + STANDARD_DATE_DELIMINATOR + DATE_CODE);
	public static final String FULL_DATE_TIME = new String("fdt");
	
	/** 
	 * CUSTOM FORMATS MAY BE USED BUT MUST FOLLOW STRICT FORMATS. ALL CHARACTERS ARE LEGAL AND ONLY THE FOLLOWING WILL BE REPLACED::
	 * 
	 * YYYY = YEAR,
	 * MO = MONTH,
	 * DD = DAY,
	 * HH = HOUR,
	 * MN = MINUTE,
	 * SS = SECOND,
	 * MS = MILLISECOND. 
	 * 
	 * FOR EXAMPLE, THE FORMAT OF THE INCLUDED OPTION OF YYYYMMDD_HHMMSSms IS THE FOLLOWING "YYYY.MO.DD HH:MN:SS.ms"
	 * 
	 */
	public static String DATE_TIME_PREFIX = YYYYMMDD_HHMMSS;
	
	/**
	 * 
	 */
	public static boolean FULLSCREEN_MODE = false;
	
	/**
	 * 
	 */
	public static boolean AUTOSTART_EVENT_HANDLER = true;
	
	public static Border OUTPUT_FIELD_BORDER = BorderFactory.createEmptyBorder();
	
	//messages
	
	/**
	 * Message for the system console to read when clicking the button in the console to exit the console.
	 */
	public static String MESSAGE$CONSOLE_TERMINATION_FROM_BUTTON = "Terminating program via button in console. Goodbye :)";
	
	/**
	 * Message for if an error occurs when setting the last output entry to a string parameter in the method Console.consoleWindow.setLastOutputFieldText(String arg)
	 * A space on the end of the message is recommended. The parameter is printed following the message.
	 */
	public static String MESSAGE$FAIL_OVERRIDE_SET_LAST_OUTPUT = "Failed to setout field text to ";
	
	/**
	 * Message for failing to find an output entry when searching with its id. Used in method Console.consoleWindow.findJTextAreaComponent
	 * A space on the end of the message is recommended. The parameter is printed following the message.
	 */
	public static String MESSAGE$FAIL_FIND_ENTRY_BY_ID = "Failed to find output field ";
	
	/**
	 * Message for failing to remove the last output entry. Used in the Console.consoleWindow.removeLastOutputField()
	 */
	public static String MESSAGE$FAIL_REMOVE_LAST_OUTPUT = "Failed to remove the last output field";
	
	/**
	 * Message for conflicting configuration of prompting_console and show_cmd_line.
	 */
	public static String MESSAGE$CONFLICT_CFG_PROMPTING_CMDLINE = Errors.CONFLICTING_CONFIGURATION.getMessage() + "<PROMPTING_CONSOLE[boolean]> & <SHOW_CMD_LINE[boolean]>";
			
	/**
	 * Message for conflicting configuration of prompting_console and show_cmd_line.
	 */	
	public static String MESSAGE$CONFLICT_CFG_SHOWCONSOLE_CMDLINE = Errors.CONFLICTING_CONFIGURATION.getMessage() + "<SHOW_CONSOLE[boolean]> & <SHOW_CMD_LINE[boolean]>";

	public static boolean ENTRY_LINEWRAP = false;

	public static boolean PAINT_PROGRESS_PERCENTAGE = true;
	
	/**
	 * Print the configuration in the system console or console application.
	 * @param inConsole Pring the configuration in the console application.
	 * This print uses the custom color setting set in PL; Uses PL.INFOC
	 */
	public static void PRINT_CONFIGURATION(boolean inConsole) {
		printcfg("CONSOLE " + Console.version.getVersion() + " CONFIGURATION", new Date().toString(),inConsole);
		printcfg("PROMPTING_CONSOLE",""+PROMPTING_CONSOLE,inConsole);
		printcfg("SHOW_CONSOLE",""+SHOW_CONSOLE,inConsole);
		printcfg("SHOW_CMD_LINE",""+SHOW_CMD_LINE,inConsole);
		printcfg("CONSOLE_FONT",""+CONSOLE_FONT.getName(),inConsole);
		printcfg("AUTO_RESET_CUSTOM_COLOR",""+AUTO_RESET_CUSTOM_COLOR,inConsole);
		printcfg("FULLSCREEN_MODE",""+FULLSCREEN_MODE,inConsole);
		printcfg("AUTOSTART_EVENT_HANDLER",""+AUTOSTART_EVENT_HANDLER,inConsole);
		printcfg("ENTRY_LINEWRAP",""+ENTRY_LINEWRAP,inConsole);
		printcfg("SHOW_ENTRY_IDENTIFIER",SHOW_ENTRY_IDENTIFIER+"",inConsole);
		printcfg("PAINT_PROGRESS_PERCENTAGE",PAINT_PROGRESS_PERCENTAGE+"",inConsole);
		
		printcfg("LOG_CHARACTER_PREFIX",LOG_CHARACTER_PREFIX+"",inConsole);
		printcfg("PROMPT_INPUT_CENSOR",PROMPT_INPUT_CENSOR+"",inConsole);
		
		printcfg("DATE_TIME_PREFIX",String.valueOf(DATE_TIME_PREFIX),inConsole);
		printcfg("GUI_UPDATE_DELAY",GUI_UPDATE_DELAY+"",inConsole);
		printcfg("MAX_ENTRY_LIMIT", MAX_ENTRY_LIMIT+"",inConsole);
		printcfg("ILLEGAL_COMMAND_PREFIX",ILLEGAL_COMMAND_PREFIX,inConsole);
		printcfg("CONSOLE_TITLE",""+CONSOLE_TITLE,inConsole);
		
		try { printcfg("COMMAND_HANDLER",COMMAND_HANDLER.toString(),inConsole);
		}catch(Exception e) { printcfg("COMMAND_HANDLER","null",inConsole); }
		
		printcfg("BACKGROUND_COLOR",""+BACKGROUND_COLOR.getRed() + "x" + BACKGROUND_COLOR.getGreen() + "x" + BACKGROUND_COLOR.getBlue(),inConsole);
		printcfg("TEXT_COLOR",getColorRGB(TEXT_COLOR),inConsole);
		printcfg("WARN_MSG_COLOR",getColorRGB(WARN_MSG_COLOR),inConsole);
		printcfg("SEVERE_MSG_COLOR",getColorRGB(SEVERE_MSG_COLOR),inConsole);
		printcfg("DEBUG_MSG_COLOR",getColorRGB(DEBUG_MSG_COLOR),inConsole);
		printcfg("FATAL_MSG_COLOR",getColorRGB(FATAL_MSG_COLOR),inConsole);
		printcfg("PRIORITY_INFO_MSG_COLOR",getColorRGB(PRIORITY_INFO_MSG_COLOR),inConsole);
		printcfg("WARN_BACKGROUND_COLOR",getColorRGB(WARN_BACKGROUND_COLOR),inConsole);
		printcfg("SEVERE_BACKGROUND_COLOR",getColorRGB(SEVERE_BACKGROUND_COLOR),inConsole);
		printcfg("DEBUG_BACKGROUND_COLOR",getColorRGB(DEBUG_BACKGROUND_COLOR),inConsole);
		printcfg("FATAL_BACKGROUND_COLOR",getColorRGB(FATAL_BACKGROUND_COLOR),inConsole);
		printcfg("PRIORITY_INFO_BACKGROUND_COLOR",getColorRGB(PRIORITY_INFO_BACKGROUND_COLOR),inConsole);
		printcfg("BACKGROUND_CUSTOM_COLOR",getColorRGB(BACKGROUND_CUSTOM_COLOR),inConsole);
		printcfg("MSG_CUSTOM_COLOR",getColorRGB(MSG_CUSTOM_COLOR),inConsole);
	}
	private static void printcfg(String name, String value,boolean inConsole) {
		if(inConsole) {
			PL.con(name + ": <" + value + ">", PL.INFOC);
		} else {
			PL.sys(name + ": <" + value + ">", PL.INFO);
		}
	}
	public static String getColorRGB(Color c) {
		return new String(c.getRed() + "x" + c.getGreen() + "x" + c.getBlue());
	}
	public static void resetCustomColors() {
		MSG_CUSTOM_COLOR = ConsoleProperties.TEXT_COLOR;
		BACKGROUND_CUSTOM_COLOR = ConsoleProperties.BACKGROUND_COLOR;
	}
	
}
