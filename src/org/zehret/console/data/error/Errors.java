package org.zehret.console.data.error;

public enum Errors
{
	/*
	 * 0-99 All load issues
	 * 100 - 199 Command Handler errors
	 * 200 - 299 Log saving errors
	 * 300 - 399 Console errors (Mainly printing)
	 * 400 - 499 Version issues
	 * 500 - 599 Application command errors
	 * 600 - 699 Console errors
	 * 700 - 799 Configuration Errors
	 * 800 - 899
	 * 900 - 999
	 * 1000 - 9999 Misc. errors
	 * 10000 - 99999 Let's never reach this okay?
	 */
	
	//0  ------------------------------------ Everything is okay more or less?
	
	EXPECTED_EXECUTION("0x0","EXPECTED_EXECUTION","The operation executed as expected."),
	EXPECTED_SUCCESS("0x1","EXPECTED_SUCCESS","The operation executed successfully as expected"),
	EXPECTED_FAILURE("0x2","EXPECTED_FAILURE","The operation failed as expected"),
	UNEXPECTED_EXECUTION("0x3","UNEXPECTED_EXECUTION","The operation executed not as expected."),
	UNEXPECTED_SUCCESS("0x4","UNEXPECTED_SUCCESS","The operation executed successfully as not expected."),
	UNEXPECTED_FAILURE("0x5","UNEXPECTED_FAILURE","The operation failed as not expected"),
	
	//00 ------------------------------------
	
	
	
	
	//100------------------------------------Command handler issues
	
	/**
	 * Command handler is null, generally.
	 */
	INVALID_CMD_HANDLER("0x64", "INVALID_CMD_HANDLER","Could not identify command handler."),
	/**
	 * Trying to access a generally null command handler. Either through a command or other means.
	 */ 
	UNKOWN_CMD_HANDLER_ERROR("0x65" ,"UNKOWN_CMD_HANDLER_ERROR","An unkown command handler error has occurred."),
	/**
	 * Trying to run a command through a null command handler.
	 */
	UNDECLARED_CMD_HANDLER("0x66","UNDECLARED_CMD_HANDLER","Unable to process command, undeclared command handler."),
	/**
	 * Failed to add a command to the handler list. 
	 */
	FAILED_TO_ADD_COMMAND("0x67", "FAILED_TO_ADD_COMMAND","Failed to add command to list."),
	
	/**
	 * The command failed to add because the command already exists
	 */
	FAILED_TO_ADD_COMMAND_$_DUPLICATE_ENTRY("0x68","FAILED_TO_ADD_COMMAND_$_DUPLICATE_ENTRY","Failed to add command to list, conflicting entries."),
	
	/**
	 * The index is not initialized or not set in the command list. 
	 */
	INVALID_COMMAND_INDEX("0x6", "INVALID_COMMAND_INDEX", "Invalid command index requested."),
	/**
	 * The command string could not be found in the list.
	 */
	UNKOWN_COMMAND_INDEX("0x6A", "UNKOWN_COMMAND_INDEX", "Failed to find index for command search."),
	/**
	 * The command could not be found
	 */
	UNKOWN_COMMAND("0x6B", "UNKOWN_COMMAND","Unkown command requested."),

	
	
	//200------------------------------------Logging Issues
	
	
	
	//300------------------------------------Console Errors
	
	/**
	 * Failed to print to the console window, may either be not initialized or not in use.
	 */
	FAILED_TO_PRINT_TO_CONSOLE("0x12C","FAILED_TO_PRINT_TO_CONSOLE","Failed to print to the console window."),
	/**
	 * The user requested an application exit.
	 */
	PLANNED_EXIT("0x12D","PLANNED_EXIT","The program will be terminated as directed."),
	
	
	//400------------------------------------Version issues
	
	/**
	 * The version format does not meet the specifications
	 */
	INVALID_VERSION_FORMAT("0x190","INVALID_VERSION_FORMAT","Version format is illegal."),
	/**
	 * Failed to apply the version format in the string to char array conversion
	 */
	FAILED_TO_APPLY_VERSION_FORMAT("0x191","FAILED_TO_APPLY_VERSION_FORMAT","Failed to apply the required version format. This will not be enforced."),
	/**
	 * Failed to check the format of the version number, most likely from error 401.
	 */
	FAILED_TO_CHECK_VERSION_FORMAT("0x192","FAILED_TO_CHECK_VERSION_FORMAT","Failed to check version format."),
	
	
	
	
	//500------------------------------------Application Command Errors
	
	/**
	 * Failure to add a flag to a command, as of writing this, there is no support for flags..
	 */
	FAILED_TO_ADD_FLAG("0x1F4","FAILED_TO_ADD_FLAG","Failed to add command flag, FEATURE NOT SUPPORTED!"),
	INCORRECT_COMMAND_SYNTAX("0x1F5","INCORRECT_COMMAND_SYNTAX","Incorrect command syntax or arguments"),
	FAILED_TO_IDENTIFY_ARGUMENTS("0x1F6","FAILED_TO_IDENTIFY_ARGUMENTS","Failed to separate the arguments for command"),
	/**
	 * May be used to notify of any command error when dealing with added commands.
	 */
	GENERIC_COMMAND_ERROR("0x1F7","GENERIC_COMMAND_ERROR", "An error has occurred with a command, see system for details."),
	
	INCORRECT_COMMAND_FORMAT("0x1F8","INCORRECT_COMMAND_FORMAT","Command format is not correct."),
	INCORRECT_COMMAND_ARGUMENT_FORMAT("0x1F9","INCORRECT_COMMAND_ARGUMENT_FORMAT","Command argument format is not correct."),

	

	
	
	//600------------------------------------Console Errors

	
	
	UNKOWN_INTERNAL_COMMAND("0x258","UNKOWN_INTERNAL_COMMAND","The entered command is not known."),
	
	
	FAILED_TO_CREATE_COMMAND_WINDOW("0x259","FAILED_TO_CREATE_COMMAND_WINDOW","Failed to create command window."),
	
	FAILED_TO_BUILD_OUTPUT_ARRAY("0x25A","FAILED_TO_BUILD_OUTPUT_ARRAY","Failed to build output array."),
	
	FAILED_TO_RETRIEVE_OUTPUT_ARRAY("0x25B","FAILED_TO_RETRIEVE_OUTPUT_ARRAY","Failed to get the output array."),
	
	FAILED_TO_FORMAT_OUTPUT_STRING("0x25C","FAILED_TO_FORMAT_OUTPUT_STRING","Failed to format the output as a string."),
	

	
	
	/**
	 * Internal use only. For external commands use 501
	 */
	INT_INCORRECT_COMMAND_SYNTAX("0x25D","INT_INCORRECT_COMMAND_SYNTAX","Incorrect internal command syntax or arguments."),
	/**
	 * Internal use only. For external commands use 502
	 */
	INT_FAILED_TO_IDENTIFY_ARGUMENTS("0x25E","INT_FAILED_TO_IDENTIFY_ARGUMENTS","Failed to separate the arguments for internal command."),
	
	
	//700------------------------------------Console Configuration Errors
	
	CONFLICTING_CONFIGURATION("0x2BC","CONFLICTING_CONFIGURATION","A conflict has been detected in the console configuration. See console for details."),
	DEFAULT_CONFIGURATION("0x2BD","DEFAULT_CONFIGURATION","The console has been configured according to the default values, no changes have been made."),
	
	;
	
	private final String code;
	private final String description;
	private final String name;
	
	/**
	 * 
	 * @param code Numerical code in accordance to above table
	 * @param name Should resemble the variable name in format.
	 * @param desc Error description
	 */
	private Errors(String code,String name, String desc)
	{
		this.code = code;
		this.name = name;
		this.description = desc;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getCode()
	{
		return this.code;
	}
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String toString()
	{
		return this.getCode() + "/" + this.getName() + ": " + this.getDescription();
	}
	
	public String getMessage()
	{
		return this.getDescription() + " " + this.getName() + "(" + this.getCode() + ").";
	}
}
