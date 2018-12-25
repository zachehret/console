package org.zehret.console.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.zehret.console.Console;
import org.zehret.console.util.ConsoleProperties;

import java.awt.GridBagLayout;
import javax.swing.JProgressBar;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ProcessingPopup extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8422483191658331574L;
	private JPanel contentPane;
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessingPopup frame = new ProcessingPopup();
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
	public ProcessingPopup()
	{
		super(Console.consoleWindow);
		setLocationRelativeTo(Console.consoleWindow);
		setResizable(false);
		setTitle("Please wait..");
		this.setAlwaysOnTop(true);
		setBounds(100, 100, 400, 57);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{30, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(ConsoleProperties.PAINT_PROGRESS_PERCENTAGE);
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setIndeterminate(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 0;
		contentPane.add(progressBar, gbc_progressBar);
		
		setVisible(true);
	}
	
	public int setProgress(float percentage)
	{
		this.progressBar.setValue((int)(Math.floor(percentage)));
		return this.progressBar.getValue();
	}

	public int getProgressValue()
	{
		return this.progressBar.getValue();
	}
	
	public boolean setBarIndeterminate(boolean t)
	{
		this.progressBar.setIndeterminate(t);
		return this.progressBar.isIndeterminate();
	}
	

}
