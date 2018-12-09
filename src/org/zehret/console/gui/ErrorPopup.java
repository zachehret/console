package org.zehret.console.gui;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ErrorPopup {
	
	static JDialog dialog;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public ErrorPopup(Frame parent, String message, String title, boolean alert) {
		if(alert) {
			Toolkit.getDefaultToolkit().beep();
		}
		dialog = new JDialog(parent, true);
		dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(ErrorPopup.class.getResource("/org/zehret/console/gui/resources/exclamatory.png")));
		dialog.setTitle(title);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 0, 20, 0};
		gridBagLayout.rowHeights = new int[]{5, 32, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		dialog.getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ErrorPopup.dialog.dispose();
			}
		});
			
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		dialog.getContentPane().add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.menu);
		textArea.setText(message);
		
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createEtchedBorder());
		textArea.setSize(textArea.getPreferredSize());
		scrollPane.setViewportView(textArea);
		
		scrollPane.setPreferredSize(scrollPane.getPreferredSize());
		
		btnNewButton.setIcon(new ImageIcon(ErrorPopup.class.getResource("/org/zehret/console/gui/resources/terminate.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;

		
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setSize(new Dimension(200, 161));
		dialog.pack();
		dialog.getContentPane().add(btnNewButton, gbc_btnNewButton);
		dialog.validate();
		dialog.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ErrorPopup(null, "Message", "Test title",true);
	}
}