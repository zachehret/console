package org.zehret.console.gui;

import org.zehret.console.Console;
import org.zehret.console.data.ccl.ConsoleCommandLine;
import org.zehret.console.data.error.Errors;
import org.zehret.console.util.ConsoleProperties;
import org.zehret.console.util.FontSet;
import org.zehret.console.util.PL;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.event.MouseMotionAdapter;

public class NewConsoleWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField textField;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu FileMenuButton;
	private JMenu saveLogButton;
	private JMenuItem terminateButton;
	private JMenu mnView;
	private JMenuItem mntmHide;
	private JMenuItem mntmMaximize;
	private JMenuItem mntmMinimize;
	private JMenu mnData;
	private JLabel entriesCountLbl;
	
	private final long startTime;
	
	private long entries = 1;
	private JLabel uptimeLbl;
	private JMenuItem consoleCMD;
	public ConsoleCommandLine consoleCommandLine = new ConsoleCommandLine();
	
	public String lastOutputFieldName = "-1";
	
	private ArrayList<String> OUTPUT_ARCHIVE = new ArrayList<String>();
	
	//ArrayList<JTextField> OUTPUT_TEXT = new ArrayList<JTextField>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					NewConsoleWindow frame = new NewConsoleWindow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewConsoleWindow()
	{
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(ConsoleProperties.FULLSCREEN_MODE) {
					Console.consoleWindow.setLocation(0,0);
				}
			}
		});
		this.consoleCommandLine.setVisible(false);
		startTime = System.currentTimeMillis();
		String title = ConsoleProperties.CONSOLE_TITLE;
		if(!ConsoleProperties.HIDE_VERSION)
			title += " [Console ("+Console.version.getVersion()+")]";
		setTitle(title);
		try {
			this.setIconImage(ConsoleProperties.CONSOLE_ICON);
		}catch(Exception e) {
			PL.sys("Failed to set icon for console window. " + e.getMessage(), PL.WARN);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 478);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		FileMenuButton = new JMenu("File");
		FileMenuButton.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(FileMenuButton);
		
		saveLogButton = new JMenu("Save");
		saveLogButton.setSelectedIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/save1.png")));
		saveLogButton.setIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/save0.png")));
		saveLogButton.setFont(new Font("Arial", Font.PLAIN, 12));
		FileMenuButton.add(saveLogButton);
		
		mntmArchivedOutput = new JMenuItem("Archived Output");
		mntmArchivedOutput.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String[] out = new String[Console.consoleWindow.OUTPUT_ARCHIVE.size()];
				for(int n = 0; n < Console.consoleWindow.OUTPUT_ARCHIVE.size(); n++) {
					out[n] = Console.consoleWindow.OUTPUT_ARCHIVE.get(n);
				}
				PL.createWriteNewFile("logs\\console\\output_archives\\"+PL.getFileNameAndExtInFormat(ConsoleProperties.LOG_CHARACTER_PREFIX),out);
			}
		});
		saveLogButton.add(mntmArchivedOutput);
		
		mntmDisplayedOutput = new JMenuItem("Displayed Output");
		mntmDisplayedOutput.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PL.createWriteNewFile("logs\\console\\output\\"+PL.getFileNameAndExtInFormat(ConsoleProperties.LOG_CHARACTER_PREFIX),Console.consoleWindow.getOutputStringArray());
			}
		});
		saveLogButton.add(mntmDisplayedOutput);
		
		terminateButton = new JMenuItem("Terminate");
		terminateButton.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e)
			{
				PL.ExOUT(ConsoleProperties.MESSAGE$CONSOLE_TERMINATION_FROM_BUTTON,PL.WARN);
				System.exit(0);	
			}
		});
		
		consoleCMD = new JMenuItem("Command");
		consoleCMD.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				consoleCommandLine.setVisible(true);
			}
		});
		FileMenuButton.add(consoleCMD);
		consoleCMD.setIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/terminal_icon.png")));
		consoleCMD.setFont(new Font("Arial", Font.PLAIN, 12));
		terminateButton.setIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/terminate.png")));
		terminateButton.setFont(new Font("Arial", Font.PLAIN, 12));
		FileMenuButton.add(terminateButton);
		
		mnData = new JMenu("Data");
		mnData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				updateGUI();
			}
		});
		mnData.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mnData);
		
		entriesCountLbl = new JLabel("Data");
		entriesCountLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		mnData.add(entriesCountLbl);
		
		uptimeLbl = new JLabel("Data");
		uptimeLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		mnData.add(uptimeLbl);
		
		mnView = new JMenu("View");
		mnView.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(mnView);
		
		mntmMaximize = new JMenuItem("Maximize");
		mntmMaximize.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				Console.consoleWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Console.consoleWindow.updateGUI();
			}
		});
		mntmMaximize.setIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/maximize.png")));
		mntmMaximize.setFont(new Font("Arial", Font.PLAIN, 12));
		mnView.add(mntmMaximize);
		
		mntmMinimize = new JMenuItem("Minimize");
		mntmMinimize.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				Console.consoleWindow.setState(Frame.ICONIFIED);
				Console.consoleWindow.updateGUI();
			}
		});
		mntmMinimize.setIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/minimize.png")));
		mntmMinimize.setFont(new Font("Arial", Font.PLAIN, 12));
		mnView.add(mntmMinimize);
		
		mntmHide = new JMenuItem("Hide");
		mntmHide.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(mntmHide.isEnabled())
				{
					int conf = JOptionPane.showConfirmDialog(Console.consoleWindow, "Are you sure you want to hide the console? (Restart required to reappear).", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(conf == JOptionPane.YES_OPTION)
					{
						Console.consoleWindow.setVisible(false);
						Console.consoleWindow.updateGUI();
					}
					else
						return;
				}
				else
				{
					JOptionPane.showConfirmDialog(Console.consoleWindow, "Application type forbids hiding the console", "Denied", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mntmHide.setIcon(new ImageIcon(NewConsoleWindow.class.getResource("/org/zehret/console/gui/resources/hide.png")));
		mntmHide.setFont(new Font("Arial", Font.PLAIN, 12));
		mnView.add(mntmHide);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
	
		textPanel = new JPanel();
		textPanel.setBackground(ConsoleProperties.BACKGROUND_COLOR);
		scrollPane.setViewportView(textPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		
		if(ConsoleProperties.SHOW_CMD_LINE) {
			addCommandLine();
		} else {}
		
		if(ConsoleProperties.FULLSCREEN_MODE) {
			this.setUndecorated(true);
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();														 			
			int height = gd.getDisplayMode().getHeight();													
			this.setSize(width, height);
			this.setResizable(false);
			this.setAlwaysOnTop(false);
			this.setLocation(0, 0);
			PL.con_encap("FULLSCREEN ENABLED | PROGRAM SHOULD INCLUDE EXIT METHODS", '#', PL.INFO, PL.NO_ALERT);
		} else {
			this.setMinimumSize(new Dimension(775,475));
		}
	}
	
	long lastGUIUpdateTime = System.currentTimeMillis();
	
	/**
	 * This method will accept color as the defined code found in the PL class.
	 * @param text
	 * @param COLOR_CODE
	 * @param TEXT_FORMATS
	 * 
	 * Do not use this method.
	 */
	@Deprecated
	public void insertOutput(String text, int COLOR_CODE) {
		String colorCode = "ffffff";
		insertOutput("[deprecated]" + text,ConsoleProperties.TEXT_COLOR,ConsoleProperties.BACKGROUND_COLOR);
	}
	
	/**
	 * This method will accept color in a hexadecimal format.
	 * @param text
	 * @param COLOR_FORMAT Color HEX Code
	 */
	public void insertOutput(String text, String textColorHex, String backgroundColorHex) {
		insertOutput(text,Color.decode(textColorHex), Color.decode(backgroundColorHex));
	}
	
	public void updateFullscreen() {
	//	this.setUndecorated(ConsoleProperties.FULLSCREEN_MODE);
		if(ConsoleProperties.FULLSCREEN_MODE) {
			//**************************************************************************************************
			//https://stackoverflow.com/questions/3680221/how-can-i-get-screen-resolution-in-java			   *
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); // *
			int width = gd.getDisplayMode().getWidth();														// *
			int height = gd.getDisplayMode().getHeight();													// *
			//**************************************************************************************************
			this.setSize(width, height);
			this.setResizable(false);
			this.setAlwaysOnTop(false);
			this.setLocation(0, 0);
		} else {
			this.setResizable(true);
			this.setSize(this.getMinimumSize());
			this.setAlwaysOnTop(false);
		}
	}
	
	long lastUpdateTime = System.currentTimeMillis()-1000000;
	
	/**
	 * This method will accept color as a java.awt Color object.
	 * EACH CALL WILL PRODUCE A NEW LINE
	 * @param text
	 * @param textColor
	 * @param backgroundColor
	 */
	public void insertOutput(String text, Color textColor, Color backgroundColor) {
		String fieldID = UUID.randomUUID().toString();
		fieldID = fieldID.substring(fieldID.length()-12);
		//Create the new Textfield
		JTextArea newField = new JTextArea();
		if(ConsoleProperties.SHOW_ENTRY_IDENTIFIER)
			newField.setText("[" + fieldID + "] "+ text);
		else
			newField.setText(text);
		newField.setForeground(textColor);
		newField.setBackground(backgroundColor);
		//newField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		newField.setBorder(ConsoleProperties.OUTPUT_FIELD_BORDER);
		newField.setFont(ConsoleProperties.CONSOLE_FONT);
		newField.setVisible(true);
		newField.setEditable(false);
		newField.setEnabled(true);
		newField.setName(fieldID);
		newField.setMaximumSize(new Dimension(Integer.MAX_VALUE,(int)newField.getPreferredSize().getHeight()));
		newField.setLineWrap(ConsoleProperties.ENTRY_LINEWRAP);
		
		
		this.lastOutputFieldName = fieldID;
		
		GridBagConstraints gbc_newField = new GridBagConstraints();
		gbc_newField.fill = GridBagConstraints.NONE;
		
		this.textPanel.add(newField, gbc_newField);
	
		if(System.currentTimeMillis() - lastUpdateTime > ConsoleProperties.GUI_UPDATE_DELAY) {
			this.validate();
			lastUpdateTime = System.currentTimeMillis();
		}

		this.scrollPane.getVerticalScrollBar().setValue(Integer.MAX_VALUE);
		
		if(this.textPanel.getComponentCount() > ConsoleProperties.MAX_ENTRY_LIMIT) {
			this.textPanel.remove(0);
		}
	
		this.OUTPUT_ARCHIVE.add(text);
		updateGUI();
	}
	
	/**
	 * Use this method to set the text of the last output field
	 */
	public void setLastOutputFieldText(String text) {
		try {
			this.findJTextAreaComponent(this.lastOutputFieldName).setText(text);
		}catch(NullPointerException e) {
			PL.con(ConsoleProperties.MESSAGE$FAIL_OVERRIDE_SET_LAST_OUTPUT + this.lastOutputFieldName,PL.SEVERE);
			e.printStackTrace();
		}
	}
	
	/**
	 * Use this method to get the text of the last output field
	 * @return
	 */
	public String getLastOutputFieldText() { 
		try {
			return this.findJTextAreaComponent(this.lastOutputFieldName).getText();
		}catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Append to the previous line..
	 */
	public void lineAppend(String text) {
		JTextArea jta = this.findJTextAreaComponent(this.lastOutputFieldName);
		jta.setText(jta.getText() + text);	
		this.OUTPUT_ARCHIVE.add(jta.getText());
	}
	
	/**
	 * Small GUI Updates
	 */
	public void updateGUI()
	{
		mntmMaximize.setEnabled(this.getExtendedState() != JFrame.MAXIMIZED_BOTH);
		mntmMinimize.setEnabled(this.getState() != Frame.ICONIFIED);
		
		try {
			this.entries = this.textPanel.getComponentCount();
		}catch(Exception e) { this.entries = -1; }
		
		long timeElapsed = System.currentTimeMillis() - this.startTime;
		
		this.entriesCountLbl.setText("Line Entries: " + this.entries);
		this.uptimeLbl.setText("Uptime: " + String.format("%02dM %02dS",TimeUnit.MILLISECONDS.toMinutes(timeElapsed), TimeUnit.MILLISECONDS.toSeconds(timeElapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))));
		
		if(ConsoleProperties.PROMPTING_CONSOLE)
			this.mntmHide.setEnabled(false);
		else
			this.mntmHide.setEnabled(true);
	}
	
	public String getOutputString() {
		
		String[] o = getOutputStringArray();
		String output;
		try {
			output = o[0];
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return PL.con(Errors.FAILED_TO_FORMAT_OUTPUT_STRING.getMessage() + " " + e.getMessage() + ".",PL.WARN);
		}
		
		for(int n = 1; n < o.length; n++) {
			try {
				output = "\n" + o[n];
			}catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
				e.printStackTrace();
				PL.con(Errors.FAILED_TO_RETRIEVE_OUTPUT_ARRAY.getMessage() + " " + e.getMessage() + ". A partial output may be produced.",PL.WARN);
				if(ArrayIndexOutOfBoundsException.class.isInstance(e))
					return output;
				else
					return Errors.FAILED_TO_RETRIEVE_OUTPUT_ARRAY.getMessage();
			}
		}
		return output;
	}
	
	public String[] getOutputStringArray() {
		return this.buildOutputStringArray(0, -1);
	}
	
	private String[] buildOutputStringArray(int begin, int end) {
		if(end < 0)
			end = this.textPanel.getComponentCount();
		
		System.out.println("Building String Array from " + begin + " to " + end + ". Count: " + (end-begin-1));
		
		try {
			String[] output = new String[end - begin];
			for(int n = begin; n < end; n++) {
				System.out.println(n + " >> " + output.length);
				output[n] = JTextArea.class.cast(this.textPanel.getComponent(n)).getText();
			}
			return output;
		}catch(Exception e) {
			return new String[]{"ERROR", PL.con(Errors.FAILED_TO_BUILD_OUTPUT_ARRAY.getMessage()),"ERROR"};
		}
	}
	
	JButton sendCommandButton;
	private JPanel textPanel;
	private Component horizontalStrut;
	private JMenuItem mntmDisplayedOutput;
	private JMenuItem mntmArchivedOutput;
	
	public void addCommandLine()
	{
		sendCommandButton = new JButton("→");
		sendCommandButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				if(sendCommandButton.isEnabled())
				{
					if(!Console.consoleWindow.textField.getText().trim().equals(""))
					{
						Console.setLastCommandEntry(Console.consoleWindow.textField.getText());
						Console.consoleWindow.textField.setText("");
						Console.consoleWindow.textField.requestFocus();
						if(!Console.prompting)
						{
							try
							{
								Console.cmdHandler.processCommandSend(Console.getLastCommandEntry());
							}catch(NullPointerException e)
							{
								if(!ConsoleProperties.PROMPTING_CONSOLE)
									PL.con(Errors.UNDECLARED_CMD_HANDLER.getMessage());
							}catch(Exception e)
							{
								e.printStackTrace();
								PL.con(Errors.UNKOWN_CMD_HANDLER_ERROR.getMessage());
							}
						}
					}
					else
					{
						if(Console.getLastCommandEntry().equals(ConsoleProperties.ILLEGAL_COMMAND_PREFIX))
							return;
						Console.consoleWindow.textField.setText(Console.getLastCommandEntry());
					}
				}
			}
		});
	
		sendCommandButton.setToolTipText("Send command");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent ke) 
			{
				if(textField.isEnabled())
				{
					if(ke.getKeyCode() == KeyEvent.VK_ENTER)
					{
						if(!Console.consoleWindow.textField.getText().trim().equals(""))
						{
							Console.setLastCommandEntry(Console.consoleWindow.textField.getText());
							Console.consoleWindow.textField.setText("");
							Console.consoleWindow.textField.requestFocus();
							if(!Console.prompting)
							{
								try
								{
									Console.cmdHandler.processCommandSend(Console.getLastCommandEntry());
								}catch(NullPointerException e)
								{
									if(!ConsoleProperties.PROMPTING_CONSOLE)
										PL.con(Errors.UNDECLARED_CMD_HANDLER.getMessage());
								}catch(Exception e)
								{
									e.printStackTrace();
									PL.con(Errors.UNKOWN_CMD_HANDLER_ERROR.getMessage());
								}
							}
						}
						else
						{
							if(Console.getLastCommandEntry().equals(ConsoleProperties.ILLEGAL_COMMAND_PREFIX))
								return;
							Console.consoleWindow.textField.setText(Console.getLastCommandEntry());
						}
					}
				}
			}
		});
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		contentPane.add(horizontalStrut, gbc_horizontalStrut);
		textField.setToolTipText("Command");
		textField.setBackground(Color.white);
		textField.setForeground(Color.black);
		textField.setFont(new Font("Courier New", Font.PLAIN, 16));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		contentPane.add(sendCommandButton, gbc_btnNewButton);
		
	}
	
	public void setOutputColors(Color fg, Color bg)
	{
		//TODO - Set all current default messages to the new colors as passed. 
		// -- OLD CODE --
		//this.outputArea.setBackground(bg);
		//this.outputArea.setForeground(fg);
	}
	
	public boolean setCommandLineEnabled(boolean enabled)
	{
		try
		{
			this.textField.setEnabled(enabled);
			this.sendCommandButton.setEnabled(enabled);
			return this.textField.isEnabled();
		}catch(Exception e)
		{
			PL.con("Couldn't set command line to ->" + enabled);
			e.printStackTrace();
			return false;
		}
	}
	
	public void clearOutput() {
		this.textPanel.removeAll();
	}

	/**
	 * this method sets the output to the array parameter. PLEASE NOTE: ALL COLOR FORMATTING IS LOST.
	 * @param output
	 */
	public void setOutput(String[] output)
	{
		for(int n = 0; n < output.length; n++)
		{
			this.insertOutput(output[n], ConsoleProperties.TEXT_COLOR,ConsoleProperties.BACKGROUND_COLOR);
		}
	}

	public String getCommandLineText()
	{
		return this.textField.getText();
	}

	public void setFontSet(FontSet consoleFontSet)
	{
		
		//TODO - ITERATE THROUGH THE LIST AND CHANGE THE ONES USING THE DEFAULT FONTSET, CUSTOM ONES DO NOT CHANGE
		//--- OLD CODE ---
		//this.outputArea.setFont(consoleFontSet.getFont());
		//this.outputArea.setBackground(consoleFontSet.getBackground());
		//this.outputArea.setForeground(consoleFontSet.getForeground());
	}
	
	public JTextArea findJTextAreaComponent(String id) {
		Component[] coms = this.textPanel.getComponents();
		try {
			for(int n = 0; n < coms.length; n++) {
				try {
					if(coms[n].getName().equals(this.lastOutputFieldName)) {
						return (JTextArea)coms[n];
					}
				}catch(Exception e) { e.printStackTrace();}
			}
			throw new IllegalArgumentException();
		}catch(Exception e) {
			PL.con(ConsoleProperties.MESSAGE$FAIL_FIND_ENTRY_BY_ID + this.lastOutputFieldName, PL.SEVERE);
			e.printStackTrace();
			return null;
		}
	}

	public void removeLastOutputField() {
		Component[] coms = this.textPanel.getComponents();
		try {
			for(int n = 0; n < coms.length; n++) {
				try {
					if(coms[n].getName().equalsIgnoreCase(this.lastOutputFieldName)) {
						this.textPanel.remove(n);
					}
				}catch(Exception e) { e.printStackTrace();}
			}
		}catch(Exception e) { PL.con(ConsoleProperties.MESSAGE$FAIL_REMOVE_LAST_OUTPUT,PL.WARN); }
	}

	public void forceVerticalScroll() {
		this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar().getMaximum());
	}

	public void setWordWrap(boolean arg) {
		Console.setupProgressPopup(0, false);
		for(int n = 0; n < this.textPanel.getComponentCount(); n++) {
			try {
				(JTextArea.class.cast(this.textPanel.getComponent(n))).setLineWrap(arg);
				if(this.textPanel.getComponentCount() != 0)
					Console.setProgress(n/this.textPanel.getComponentCount());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		Console.closeProgressPopup();
		
	}
	
	public void setFieldColors(String fieldID, Color background, Color text) {
		for(int n = this.textPanel.getComponentCount()-1; n >= 0; n--) {
			try {
				if(this.textPanel.getComponents()[n].getName().equals(fieldID)) {
					(JTextArea.class.cast(this.textPanel.getComponent(n))).setBackground(background);
					(JTextArea.class.cast(this.textPanel.getComponent(n))).setForeground(text);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}