package org.zehret.console.data;

import java.awt.Color;

public class ConsolePrintData {
	
	private String printMsg;
	private int prefix;
	private boolean alert;
	private Color backgroundColor, textColor;

	public ConsolePrintData(String printMsg, int prefix, boolean alert, Color backgroundColor, Color textColor) {
		this.printMsg = printMsg;
		this.prefix = prefix;
		this.alert = alert;
		this.backgroundColor = backgroundColor;
		this.textColor = textColor;
	}
	
	public String getPrintMessage() {
		return this.printMsg;
	}
	
	public int getPrefix() {
		return this.prefix;
	}
	
	public boolean getAlertStatus() {
		return this.alert;
	}
	
	public Color getBackgroundColor() {
		return this.backgroundColor;
	}
	
	public Color getTextColor() {
		return this.textColor;
	}
}
