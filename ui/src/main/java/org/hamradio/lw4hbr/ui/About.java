package org.hamradio.lw4hbr.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

public class About extends JDialog {

	private static Logger log = Logger.getLogger(About.class.getName());
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			log.error("Error",e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public About() {
		setTitle(Messages.getString("About.this.title")); //$NON-NLS-1$
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTextPane txtpnsas = new JTextPane();
			txtpnsas.setContentType("text/html");
			txtpnsas.setText(Messages.getString("About.txtpnsas.text")); //$NON-NLS-1$
			
			txtpnsas.setBackground(Color.WHITE);
			txtpnsas.setEditable(false);
			contentPanel.add(txtpnsas);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(Messages.getString("About.okButton.text")); //$NON-NLS-1$
				okButton.setActionCommand(Messages.getString("About.okButton.actionCommand")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					About.this.setVisible(false);
					
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
