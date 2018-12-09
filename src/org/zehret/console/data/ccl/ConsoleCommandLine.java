package org.zehret.console.data.ccl;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.zehret.console.Console;
import org.zehret.console.data.CommandHandler;
import org.zehret.console.data.ccl.ch.CCL_CommandHandler;
import org.zehret.console.data.error.Errors;
import org.zehret.console.gui.CmdHelp;
import org.zehret.console.util.FontSet;
import org.zehret.console.util.PL;

import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsoleCommandLine extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField cmdSpace;
	
	public String lastConsoleCommand = "";
	
	public CommandHandler cmdHandler = new CCL_CommandHandler();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsoleCommandLine frame = new ConsoleCommandLine();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
					PL.con(Errors.FAILED_TO_CREATE_COMMAND_WINDOW.getMessage());
				}
			} 
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsoleCommandLine()
	{
		super(Console.consoleWindow,true);
		cmdHandler = new CCL_CommandHandler();
		setResizable(false);
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsoleCommandLine.class.getResource("/org/zehret/console/gui/resources/terminal_icon.png")));
		setTitle("Console Command Line");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 80);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{418, 0, 0};
		gridBagLayout.rowHeights = new int[]{25, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		cmdSpace = new JTextField();
		cmdSpace.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) 
			{
				if(ke.getKeyCode() == KeyEvent.VK_ENTER)
				{
					commands((cmdSpace.getText()));
					cmdSpace.setText("");
				}
			}
		});
		cmdSpace.setForeground(Color.WHITE);
		cmdSpace.setBackground(Color.BLACK);
		cmdSpace.setCaretColor(Color.WHITE);
		cmdSpace.setToolTipText("cmd...");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		getContentPane().add(cmdSpace, gbc_textField);
		cmdSpace.setColumns(10);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem commandHelp = new JMenuItem("Commands");
		commandHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				new CmdHelp();
			}
		});
		commandHelp.setIcon(new ImageIcon(ConsoleCommandLine.class.getResource("/org/zehret/console/gui/resources/help.png")));
		commandHelp.setFont(new Font("Arial", Font.PLAIN, 12));
		mnHelp.add(commandHelp);
		
		setVisible(false);
	}
	public void commands(String s)
	{
		//System.out.println("Processing internal command " + s);
		
//		System.out.println(Console.class.toString());
//		System.out.println(Console.consoleWindow.getClass().toString());
//		System.out.println(Console.consoleWindow.consoleCommandLine.getClass().toString());
//		System.out.println(Console.consoleWindow.consoleCommandLine.cmdHandler.getClass().toString());
//		System.out.println(s);
		
		Console.consoleWindow.consoleCommandLine.cmdHandler.processCommandSend(s);
	}
}
