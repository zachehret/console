package org.zehret.console.data;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.zehret.console.data.sys.ConsoleOutputCapturer;
import org.zehret.console.util.PL;

public class SysOutCapture implements Runnable {

	public SysOutCapture() {
		
	}

	@Override
	public void run() {
		try {
			while(true) {
			    ConsoleOutputCapturer COC = new ConsoleOutputCapturer();
			    COC.start();
			    String sysOut = COC.stop();
			    if(!sysOut.equals("")) {
			    	PL.con("[SYS] " + sysOut,PL.NONE);
			    }
				
			}
		}catch(Exception e) {
			
		}
	}

}
