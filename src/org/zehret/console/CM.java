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
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CM
{
	private static final JTextField txtpnThisApplicationIs = new JTextField();
	private static final JButton btnNewButton = new JButton("                    Exit                    ");
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args)
	{
		JFrame jf = new JFrame();
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage(CM.class.getResource("/org/zehret/console/gui/resources/hide.png")));
		jf.setResizable(false);
		jf.setTitle("Launch Failed");
		jf.setSize(350, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setAlwaysOnTop(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{35, 35, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		jf.getContentPane().setLayout(gridBagLayout);
		GridBagConstraints gbc_txtpnThisApplicationIs = new GridBagConstraints();
		gbc_txtpnThisApplicationIs.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnThisApplicationIs.fill = GridBagConstraints.BOTH;
		gbc_txtpnThisApplicationIs.gridx = 0;
		gbc_txtpnThisApplicationIs.gridy = 0;
		txtpnThisApplicationIs.setHorizontalAlignment(SwingConstants.CENTER);
		txtpnThisApplicationIs.setEditable(false);
		txtpnThisApplicationIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnThisApplicationIs.setText("This application is not intended for stand-alone use.");
		txtpnThisApplicationIs.setBorder(null);
		jf.getContentPane().add(txtpnThisApplicationIs, gbc_txtpnThisApplicationIs);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				System.exit(0);
			}
		});
		jf.getContentPane().add(btnNewButton, gbc_btnNewButton);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		Toolkit.getDefaultToolkit().beep();			
	}
}
