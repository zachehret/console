package org.zehret.console;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Window.Type;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.zehret.console.data.error.Errors;
import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.PL;

import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class CM
{
	private static final JTextField txtpnThisApplicationIs = new JTextField();
	private static final JButton btnNewButton = new JButton("                    Exit                    ");
	private static final JLabel warningIcon = new JLabel("");
	private static final JLabel versionInformationLabel = new JLabel("?<version_info>");
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args)
	{
		JFrame jf = new JFrame();
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage(CM.class.getResource("/org/zehret/console/gui/resources/hide.png")));
		jf.setResizable(false);
		jf.setTitle("Console Launch Failed");
		jf.setSize(440, 160);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setAlwaysOnTop(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{35, 0, 0, 35, 0};
		gridBagLayout.rowHeights = new int[]{35, 35, 35, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		jf.getContentPane().setLayout(gridBagLayout);
		
		GridBagConstraints gbc_warningIcon = new GridBagConstraints();
		gbc_warningIcon.insets = new Insets(0, 0, 5, 5);
		gbc_warningIcon.anchor = GridBagConstraints.EAST;
		gbc_warningIcon.gridx = 1;
		gbc_warningIcon.gridy = 1;
		warningIcon.setIcon(new ImageIcon(CM.class.getResource("/org/zehret/console/gui/resources/warning.png")));
		jf.getContentPane().add(warningIcon, gbc_warningIcon);
		GridBagConstraints gbc_txtpnThisApplicationIs = new GridBagConstraints();
		gbc_txtpnThisApplicationIs.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnThisApplicationIs.fill = GridBagConstraints.BOTH;
		gbc_txtpnThisApplicationIs.gridx = 2;
		gbc_txtpnThisApplicationIs.gridy = 1;
		txtpnThisApplicationIs.setHorizontalAlignment(SwingConstants.CENTER);
		txtpnThisApplicationIs.setEditable(false);
		txtpnThisApplicationIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnThisApplicationIs.setText("This application is not intended for stand-alone use.");
		txtpnThisApplicationIs.setBorder(null);
		jf.getContentPane().add(txtpnThisApplicationIs, gbc_txtpnThisApplicationIs);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				CM.easterEgg();
				
			}
		});
		btnNewButton.setRequestFocusEnabled(true);
		btnNewButton.requestFocusInWindow();
		jf.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		GridBagConstraints gbc_versionInformationLabel = new GridBagConstraints();
		gbc_versionInformationLabel.gridwidth = 2;
		gbc_versionInformationLabel.fill = GridBagConstraints.BOTH;
		gbc_versionInformationLabel.insets = new Insets(0, 0, 0, 5);
		gbc_versionInformationLabel.gridx = 1;
		gbc_versionInformationLabel.gridy = 3;
		versionInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		versionInformationLabel.setVerticalAlignment(SwingConstants.TOP);
		versionInformationLabel.setForeground(Color.LIGHT_GRAY);
		versionInformationLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		try {
			//A16.2018.12.24-20:34:26
			//VVV YYYY.MO.DA-HH:MI:SE
				versionInformationLabel.setText("Console Version " + ConsoleConfiguration.version.getVersion() + " (" + PL.resolveDateTimeFormat((ConsoleConfiguration.YEAR_CODE + "." + ConsoleConfiguration.MONTH_CODE + "." + ConsoleConfiguration.DATE_CODE + "-" + ConsoleConfiguration.HOUR_CODE + ":" + ConsoleConfiguration.MINUTE_CODE + ":" + ConsoleConfiguration.SECONDS_CODE) + ")")); }catch(NullPointerException e) {}
		jf.getContentPane().add(versionInformationLabel, gbc_versionInformationLabel);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		Toolkit.getDefaultToolkit().beep();			
	}
	protected static void easterEgg() {
//		ConsoleConfiguration.BACKGROUND_COLOR = Color.blue;
//		ConsoleConfiguration.TEXT_COLOR = Color.white;
//		ConsoleConfiguration.CONSOLE_FONT = new Font("Consolas", Font.PLAIN, 14);
//		ConsoleConfiguration.FULLSCREEN_MODE = true;
//		ConsoleConfiguration.SHOW_ENTRY_IDENTIFIER = false;
//		ConsoleConfiguration.DATE_TIME_PREFIX = "";
//		ConsoleConfiguration.SHOW_CONSOLE = true;
//		new Console();
//		Console.consoleWindow.validate();
//		Console.consoleWindow.setAlwaysOnTop(true);
//		Random random = new Random();
//		String alphabetString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//		for(int j = 0; j < 5000; j++) {
//			String lineString = "";
//			int length = random.nextInt(200)+100;
//			for(int i = 0; i < length; i++) {
//				lineString +=(alphabetString.charAt(random.nextInt(alphabetString.length())) + "");
//			}
//			PL.con(lineString, PL.NONE);
//		}
		System.exit(0);
	}
}
