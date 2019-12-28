package org.zehret.console.gui;

import java.awt.Color;

import org.zehret.console.util.ConsoleConfiguration;
import org.zehret.console.util.PL;

public class ConsoleWindow {
	public static void help() {
		ConsoleConfiguration.AUTO_RESET_CUSTOM_COLOR = false;
		ConsoleConfiguration.BACKGROUND_CUSTOM_COLOR = Color.gray;
		ConsoleConfiguration.MSG_CUSTOM_COLOR = ConsoleConfiguration.WARN_MSG_COLOR;
		PL.con_encap("DO NOT USE", '*', PL.WARNC, PL.NO_ALERT);
		ConsoleConfiguration.AUTO_RESET_CUSTOM_COLOR = false;
	}
}

//
//import org.zehret.console.Console;
//import org.zehret.console.data.ccl.ConsoleCommandLine;
//import org.zehret.console.data.error.Errors;
//import org.zehret.console.util.FontSet;
//import org.zehret.console.util.PL;
//
//import java.awt.GridBagLayout;
//import java.awt.GridBagConstraints;
//import java.awt.Insets;
//import java.awt.Font;
//import java.awt.Frame;
//import java.awt.Color;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollBar;
//import javax.swing.JTextField;
//import javax.swing.JTextPane;
//import javax.swing.JTextArea;
//import javax.swing.JScrollPane;
//import javax.swing.JMenuBar;
//import javax.swing.JMenu;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.border.EmptyBorder;
//
//import java.util.concurrent.TimeUnit;
//
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.EventQueue;
//
//@Deprecated
//public class ConsoleWindow extends JFrame
//{
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	JTextField textField;
//	private JScrollPane scrollPane;
//	private JMenuBar menuBar;
//	private JMenu FileMenuButton;
//	private JMenuItem saveLogButton;
//	private JMenuItem terminateButton;
//	private JMenu mnView;
//	private JMenuItem mntmHide;
//	private JMenuItem mntmMaximize;
//	private JMenuItem mntmMinimize;
//	private JTextPane outputArea;
//	private JMenu mnData;
//	private JLabel entriesCountLbl;
//	
//	private final long startTime;
//	
//	private long entries = 1;
//	private JLabel uptimeLbl;
//	private JMenuItem consoleCMD;
//	public ConsoleCommandLine consoleCommandLine = new ConsoleCommandLine();
//
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run() {
//				try {
//					ConsoleWindow frame = new ConsoleWindow();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
// 
//	/**
//	 * Create the frame.
//	 */
//	public ConsoleWindow()
//	{
//		this.consoleCommandLine.setVisible(false);
//		startTime = System.currentTimeMillis();
//		setTitle(Console.getTitle() +   " [Console ("+Console.version.getVersion()+")]");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 766, 476);
//		
//		menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		FileMenuButton = new JMenu("File");
//		FileMenuButton.setFont(new Font("Arial", Font.PLAIN, 12));
//		menuBar.add(FileMenuButton);
//		
//		saveLogButton = new JMenuItem("Save Output");
//		saveLogButton.setSelectedIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/save1.png")));
//		saveLogButton.setIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/save0.png")));
//		saveLogButton.setFont(new Font("Arial", Font.PLAIN, 12));
//		FileMenuButton.add(saveLogButton);
//		
//		terminateButton = new JMenuItem("Terminate");
//		terminateButton.addMouseListener(new MouseAdapter() {
//		
//			@Override
//			public void mousePressed(MouseEvent e)
//			{
//				PL.ExOUT("Terminating program via button in console. Goodbye :)",PL.WARN);
//				System.exit(0);	
//			}
//		});
//		
//		consoleCMD = new JMenuItem("Command");
//		consoleCMD.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) 
//			{
//				consoleCommandLine.setVisible(true);
//			}
//		});
//		FileMenuButton.add(consoleCMD);
//		consoleCMD.setIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/terminal_icon.png")));
//		consoleCMD.setFont(new Font("Arial", Font.PLAIN, 12));
//		terminateButton.setIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/terminate.png")));
//		terminateButton.setFont(new Font("Arial", Font.PLAIN, 12));
//		FileMenuButton.add(terminateButton);
//		
//		mnData = new JMenu("Data");
//		mnData.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent arg0)
//			{
//				updateGUI();
//			}
//		});
//		mnData.setFont(new Font("Arial", Font.PLAIN, 12));
//		menuBar.add(mnData);
//		
//		entriesCountLbl = new JLabel("Data");
//		entriesCountLbl.setFont(new Font("Arial", Font.PLAIN, 12));
//		mnData.add(entriesCountLbl);
//		
//		uptimeLbl = new JLabel("Data");
//		uptimeLbl.setFont(new Font("Arial", Font.PLAIN, 12));
//		mnData.add(uptimeLbl);
//		
//		mnView = new JMenu("View");
//		mnView.setFont(new Font("Arial", Font.PLAIN, 12));
//		menuBar.add(mnView);
//		
//		mntmMaximize = new JMenuItem("Maximize");
//		mntmMaximize.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent arg0)
//			{
//				Console.consoleWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				Console.consoleWindow.updateGUI();
//			}
//		});
//		mntmMaximize.setIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/maximize.png")));
//		mntmMaximize.setFont(new Font("Arial", Font.PLAIN, 12));
//		mnView.add(mntmMaximize);
//		
//		mntmMinimize = new JMenuItem("Minimize");
//		mntmMinimize.addMouseListener(new MouseAdapter()
//		{
//			@Override
//			public void mousePressed(MouseEvent e)
//			{
//				Console.consoleWindow.setState(Frame.ICONIFIED);
//				Console.consoleWindow.updateGUI();
//			}
//		});
//		mntmMinimize.setIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/minimize.png")));
//		mntmMinimize.setFont(new Font("Arial", Font.PLAIN, 12));
//		mnView.add(mntmMinimize);
//		
//		mntmHide = new JMenuItem("Hide");
//		mntmHide.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e)
//			{
//				if(mntmHide.isEnabled())
//				{
//					int conf = JOptionPane.showConfirmDialog(Console.consoleWindow, "Are you sure you want to hide the console? (Restart required to reappear).", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//					if(conf == JOptionPane.YES_OPTION)
//					{
//						Console.consoleWindow.setVisible(false);
//						Console.consoleWindow.updateGUI();
//					}
//					else
//						return;
//				}
//				else
//				{
//					JOptionPane.showConfirmDialog(Console.consoleWindow, "Application type forbids hiding the console", "Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
//		mntmHide.setIcon(new ImageIcon(ConsoleWindow.class.getResource("/org/zehret/console/gui/resources/hide.png")));
//		mntmHide.setFont(new Font("Arial", Font.PLAIN, 12));
//		mnView.add(mntmHide);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		GridBagLayout gbl_contentPane = new GridBagLayout();
//		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
//		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
//		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
//		contentPane.setLayout(gbl_contentPane);
//		
//		scrollPane = new JScrollPane();
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
//		gbc_scrollPane.gridwidth = 2;
//		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
//		gbc_scrollPane.fill = GridBagConstraints.BOTH;
//		gbc_scrollPane.gridx = 0;
//		gbc_scrollPane.gridy = 0;
//		contentPane.add(scrollPane, gbc_scrollPane);
//		
//		outputArea = new JTextPane();
//		outputArea.setBackground(Console.consoleFontSet.getBackground());
//		outputArea.setToolTipText("Output Area");
//		outputArea.setForeground(Console.consoleFontSet.getForeground());
//		outputArea.setFont(new Font("Courier New", Font.PLAIN, 14));
//		outputArea.setEditable(false);
//		scrollPane.setViewportView(outputArea);
//	
//	}
//	
//	long lastGUIUpdateTime = System.currentTimeMillis();
//	long GUI_UpdateDelay = 1000;
//	
//	/**
//	 * Use this method to append text. Miniscule updates to GUI will apply in this call.
//	 */
//	public void appendText(String text)
//	{
//		this.outputArea.setText(this.outputArea.getText() + text);
//		outputArea.setCaretPosition(outputArea.getDocument().getLength() - 1);
//		this.scrollPane.getHorizontalScrollBar().setValue(0);
//		this.scrollPane.getVerticalScrollBar().setValue(Integer.MAX_VALUE);
//		
//		if(System.currentTimeMillis() - lastGUIUpdateTime > GUI_UpdateDelay)
//		{
//			lastGUIUpdateTime = System.currentTimeMillis();
//			this.validate();
//			this.updateGUI();
//		}
//	}
//	
//	/**
//	 * Small GUI Updates
//	 */
//	public void updateGUI()
//	{
//		mntmMaximize.setEnabled(this.getExtendedState() != JFrame.MAXIMIZED_BOTH);
//		mntmMinimize.setEnabled(this.getState() != Frame.ICONIFIED);
//		
//		try {
//			this.entries = outputArea.getText().split("\r\n|\r|\n").length;
//		}catch(Exception e) { this.entries = -1; }
//		
//		long timeElapsed = System.currentTimeMillis() - this.startTime;
//		
//		this.entriesCountLbl.setText("Line Entries: " + this.entries);
//		this.uptimeLbl.setText("Uptime: " + String.format("%02dM %02dS",TimeUnit.MILLISECONDS.toMinutes(timeElapsed), TimeUnit.MILLISECONDS.toSeconds(timeElapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))));
//		
//	}
//
//	public String getOutput()
//	{
//		return this.outputArea.getText();
//	}
//	
//	JButton sendCommandButton;
//	
//	public void addCommandLine()
//	{
//		sendCommandButton = new JButton("→");
//		sendCommandButton.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent arg0)
//			{
//				if(sendCommandButton.isEnabled())
//				{
//					if(!Console.consoleWindow.textField.getText().trim().equals(""))
//					{
//						Console.setLastCommandEntry(Console.consoleWindow.textField.getText());
//						Console.consoleWindow.textField.setText("");
//					//	if(!Console.prompting)
//						{
//							try
//							{
//								Console.cmdHandler.processCommandSend(Console.getLastCommandEntry());
//							}catch(NullPointerException e)
//							{
//								PL.con(Errors.UNDECLARED_CMD_HANDLER.getMessage());
//							}catch(Exception e)
//							{
//								e.printStackTrace();
//								PL.con(Errors.UNKOWN_CMD_HANDLER_ERROR.getMessage());
//							}
//						}
//					}
//					else
//					{
//						if(Console.getLastCommandEntry().equals("[~~~{}~~~]"))
//							return;
//						Console.consoleWindow.textField.setText(Console.getLastCommandEntry());
//					}
//				}
//			}
//		});
//	
//		sendCommandButton.setToolTipText("Send command");
//		
//		textField = new JTextField();
//		textField.addKeyListener(new KeyAdapter()
//		{
//			@Override
//			public void keyPressed(KeyEvent ke) 
//			{
//				if(textField.isEnabled())
//				{
//					if(ke.getKeyCode() == KeyEvent.VK_ENTER)
//					{
//						if(!Console.consoleWindow.textField.getText().trim().equals(""))
//						{
//							Console.setLastCommandEntry(Console.consoleWindow.textField.getText());
//							Console.consoleWindow.textField.setText("");
//						//	if(!Console.prompting)
//							{
//								try
//								{
//									Console.cmdHandler.processCommandSend(Console.getLastCommandEntry());
//								}catch(NullPointerException e)
//								{
//									PL.con(Errors.UNDECLARED_CMD_HANDLER.getMessage());
//								}catch(Exception e)
//								{
//									e.printStackTrace();
//									PL.con(Errors.UNKOWN_CMD_HANDLER_ERROR.getMessage());
//								}
//							}
//						}
//						else
//						{
//							if(Console.getLastCommandEntry().equals("[~~~{}~~~]"))
//								return;
//							Console.consoleWindow.textField.setText(Console.getLastCommandEntry());
//						}
//					}
//				}
//			}
//		});
//		textField.setToolTipText("Command");
//		textField.setBackground(Color.white);
//		textField.setForeground(Color.black);
//		textField.setFont(new Font("Courier New", Font.PLAIN, 14));
//		GridBagConstraints gbc_textField = new GridBagConstraints();
//		gbc_textField.insets = new Insets(0, 0, 0, 5);
//		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField.gridx = 0;
//		gbc_textField.gridy = 1;
//		contentPane.add(textField, gbc_textField);
//		textField.setColumns(10);
//		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
//		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
//		gbc_btnNewButton.gridx = 1;
//		gbc_btnNewButton.gridy = 1;
//		contentPane.add(sendCommandButton, gbc_btnNewButton);
//	}
//	
//	public void setOutputColors(Color fg, Color bg)
//	{
//		this.outputArea.setBackground(bg);
//		this.outputArea.setForeground(fg);
//	}
//
//	public void applyFontSet()
//	{
//		this.setOutputColors(Console.consoleFontSet.getForeground(), Console.consoleFontSet.getBackground());
//		this.outputArea.setFont(Console.consoleFontSet.getFont());
//	}
//	
//	public boolean setCommandLineEnabled(boolean enabled)
//	{
//		try
//		{
//			this.textField.setEnabled(enabled);
//			this.sendCommandButton.setEnabled(enabled);
//			return this.textField.isEnabled();
//		}catch(Exception e)
//		{
//			PL.con("Couldn't set command line to ->" + enabled);
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	public void setOutput(String prePrompt)
//	{
//		this.outputArea.setText(prePrompt);
//	}
//
//	public String getCommandLineText()
//	{
//		return this.textField.getText();
//	}
//
//	public void setFontSet(FontSet consoleFontSet)
//	{
//		this.outputArea.setFont(consoleFontSet.getFont());
//		this.outputArea.setBackground(consoleFontSet.getBackground());
//		this.outputArea.setForeground(consoleFontSet.getForeground());
//	}
//}
