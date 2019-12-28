package org.zehret.console.data.sys;

import org.zehret.console.util.ConsoleConfiguration;

public class StdioWorker implements Runnable {
	
	boolean running = ConsoleConfiguration.CAPTURE_SYS_OUT;

	@Override
	public void run() {
		System.out.println("Starting system out capturer...");
		while(this.isRunning()) { 
			
		}
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
}
