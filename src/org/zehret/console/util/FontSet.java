package org.zehret.console.util;

import java.awt.Color;
import java.awt.Font;

@Deprecated
public class FontSet
{
	Color FG, BG;
	Font font;

	/**
	 * Will create a font set with a very basic layout.
	 */
	public FontSet()
	{
		this.FG = Color.LIGHT_GRAY;
		this.BG = Color.BLACK;
		this.font = new Font("Courier New", Font.PLAIN, 12);
	}
	
	
	/**
	 * 
	 * @param FG Foreground Color
	 * @param BG Background Color
	 * @param font Text Font
	 */
	public FontSet(Color FG, Color BG, Font font)
	{
		this.FG = FG;
		this.BG = BG;
		this.font = font;
	}
	
	public void setForeground(Color FG)
	{
		this.FG = FG;
	}
	public void setBackground(Color BG)
	{
		this.BG = BG;
	}
	public void setFont(Font f)
	{
		this.font = f;
	}
	
	
	public Color getForeground()
	{
		//System.out.println(this.FG.toString());
		return this.FG;
	}
	public Color getBackground()
	{
		//System.out.println(this.BG.toString());
		return this.BG;
	}
	public Font getFont()
	{
		//System.out.println(this.font.toString());
		return this.font;
	}
}
