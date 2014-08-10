package org.hamradio.lw4hbr.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

public class LocatorImport extends JDialog {
	private static Logger log = Logger.getLogger(LocatorImport.class.getName());
	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;
	private JButton okButton;
	private JButton cancelButton;
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LocatorImport dialog = new LocatorImport();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			log.error("Error",e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public LocatorImport() {
		setTitle(Messages.getString("LocatorImport.this.title")); //$NON-NLS-1$
		setBounds(100, 100, 450, 199);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			progressBar = new JProgressBar();
			progressBar.setBounds(36, 55, 357, 24);
			contentPanel.add(progressBar);
		}

		final JLabel lblImportigQthLocator = new JLabel(Messages.getString("LocatorImport.lblImportigQthLocator.text")); //$NON-NLS-1$
		lblImportigQthLocator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImportigQthLocator.setBounds(26, 11, 357, 33);
		contentPanel.add(lblImportigQthLocator);
		{
			
			final ImportRunnable r=new ImportRunnable();
			
			r.setProgresBar(getProgressBar());
			r.setLblImportigQthLocator(lblImportigQthLocator);
			
			
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				
				
				okButton = new JButton(Messages.getString("LocatorImport.okButton.text")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {
		
					public void actionPerformed(ActionEvent arg0) {
						r.setStopRequested(false);
						r.setFile(getFile());
						r.runAsThread();
						
						okButton.setEnabled(false);
					}
				});
				
				okButton.setActionCommand(Messages.getString("LocatorImport.okButton.actionCommand")); //$NON-NLS-1$
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton(Messages.getString("LocatorImport.cancelButton.text")); //$NON-NLS-1$
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						r.setStopRequested(true);
						okButton.setEnabled(true);
						
					}
				}); //$NON-NLS-1$
				buttonPane.add(cancelButton);
			}
		}
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
}
