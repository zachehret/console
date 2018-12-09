package org.zehret.console.data.version;

import org.zehret.console.data.error.Errors;
import org.zehret.console.util.PL;


public abstract class VersionFormat
{
	private String version;
	private char[] legalCharacters;
	
	public VersionFormat(String v,char[] legalCharacters)
	{
		this.version = v;
		this.legalCharacters = legalCharacters;
	}
	
	public VersionFormat(String v, String legalCharactersString)
	{
		this.version = v;
		this.legalCharacters = new char[legalCharactersString.length()];
		
		for(int n = 0; n < legalCharactersString.length(); n++)
		{
			try
			{
				legalCharacters[n] = legalCharactersString.charAt(n);
			}catch(Exception e)
			{
				PL.con(Errors.FAILED_TO_APPLY_VERSION_FORMAT.getMessage());
			}
		}
		/*
		try
		{
			this.version.getChars(0, legalCharactersString.length(), this.legalCharacters, 0);
		}catch(Exception e)
		{
			PL.con(Errors.FAILED_TO_APPLY_VERSION_FORMAT.getMessage());
		}
		*/
	}
	
	public String getVersion()
	{
		return this.version;
	}
	
	public boolean checkConditions()
	{
		try
		{
			boolean failure = true;
			for(int m = 0; m < this.version.length();m++)
			{
				failure = true;
				for(int n = 0; n < this.legalCharacters.length; n++)
				{
					//System.out.println(this.version.charAt(m) + " = " + this.legalCharacters[n] + " ? " + (this.version.charAt(m) == this.legalCharacters[n]));
					if(this.version.charAt(m) == this.legalCharacters[n])
					{
						failure = false;
						break;
					}
				}
				if(failure)
					return false;
			}
			return true;
		}catch(Exception e)
		{
			PL.con(Errors.FAILED_TO_CHECK_VERSION_FORMAT.getMessage());
			e.printStackTrace();
			return true;
		}
	}
	public boolean isLegal()
	{
		boolean legal = this.checkConditions();
		if(!legal)
		{
			PL.con("(" + this.version + ") "+Errors.INVALID_VERSION_FORMAT.getMessage());
			this.version = "<IVF?>";
		}
		return legal;
	}
}
