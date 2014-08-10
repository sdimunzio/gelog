package org.hamradio.lw4hbr.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.adif.ADIFReader;

public class ErrorDialog extends JDialog {
	private static Logger log = Logger.getLogger(ADIFReader.class.getName());
	private final JPanel contentPanel = new JPanel();
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog dialog = new ErrorDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			log.error("Error", e);
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorDialog() {
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				log.info("windowOpened");

			}

			@Override
			public void windowIconified(WindowEvent e) {
				log.info("windowIconified");

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				log.info("windowDeiconified");

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				log.info("windowDeactivated");

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(ABORT);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				log.info("windowClosed");

			}

			@Override
			public void windowActivated(WindowEvent e) {
				log.info("windowActivated");

			}
		});

		setTitle(Messages.getString("ErrorDialog.this.title")); //$NON-NLS-1$
		setBounds(100, 100, 450, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTextPane txtpnAntesDeEjecutar = new JTextPane();
		txtpnAntesDeEjecutar.setContentType("text/html");
		txtpnAntesDeEjecutar.setBackground(SystemColor.control);
		txtpnAntesDeEjecutar.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtpnAntesDeEjecutar.setText(Messages.getString("ErrorDialog.txtpnAntesDeEjecutar.text").replaceAll("URL", System.getProperty("gelog.home").replace("\\", "\\\\"))); //$NON-NLS-1$
		txtpnAntesDeEjecutar.setBounds(39, 24, 355, 76);
		contentPanel.add(txtpnAntesDeEjecutar);

		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(SystemColor.control);

		editorPane.addHyperlinkListener(new HyperlinkListener() {

			@Override
			public void hyperlinkUpdate(HyperlinkEvent hle) {
				if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {

					try {

						if (java.awt.Desktop.isDesktopSupported()) {
							java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
							desktop.browse(hle.getURL().toURI());

						}

					} catch (Exception e) {

						log.error("Error", e);
					}

				}

			}
		});

		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setText(Messages.getString("ErrorDialog.ctyDonwload.text")); //$NON-NLS-1$
		editorPane.setBounds(39, 107, 355, 59);
		contentPanel.add(editorPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(Messages.getString("ErrorDialog.okButton.text")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						ErrorDialog.this.setVisible(false);
						System.exit(ABORT);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
