package org.hamradio.lw4hbr.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.log4j.Logger;
import javax.swing.JScrollPane;

public class GeoLogMain extends JFrame {
	private static Logger log = Logger.getLogger(GeoLogMain.class.getName());

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = null;
	private JTextField txtFileName = null;
	private JLabel step1titlePath;
	private JLabel step1Icon;
	private JButton btnBrowseFile = null;
	private JLabel lblIconStep2 = null;
	private JLabel lblTitleStep2 = null;
	private JPanel tabStep2 = null;
	private JComboBox cmbQsoOrgOptions = null;
	private JLabel lblQsoOrgTitle = null;
	private JLabel lblQsoTimeTitle = null;
	private JRadioButton radioTimeOption1 = null;
	private JRadioButton radioTimeOption2 = null;
	private JLabel timeOption2 = null;
	private JLabel lblTimeOption1 = null;
	private JLabel lnlPathTitle = null;
	private JRadioButton radioPathOption1 = null;
	private JRadioButton radioPathOption2 = null;
	private JLabel lblPathOption1 = null;
	private JLabel lblPathOption2 = null;
	private JPanel tabStep1 = null;
	private JLabel lblCallResolutionTitle = null;
	private JComboBox cmbResolutionOptions = null;
	private JPanel tabStep3 = null;
	private JLabel txtIcon3 = null;
	private JTabbedPane tabPanel = null;
	private JProgressBar proccessstatus = null;
	private JTextPane step1IntroText = null;
	private JLabel step1Heading;
	private JLabel lblTitleStep3 = null;
	private JTextPane txtIntroStep3 = null;
	private JButton btnStart = null;
	private JButton btnNext = null;
	private JEditorPane txtLocationsHelp = null;
	private JTextPane txtIntroStep2 = null;
	private JButton btnPrev = null;
	private JMenuBar menuBar;
	private JMenuItem menuViewCache;
	private Action action;
	private JMenu menuHelp;
	private JMenuItem mntmOpen;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem itemAbout;
	private JMenuItem itemHelp;
	private JMenuItem mnuMyStation;
	private JTextField txtCall;
	private JTextField txtLocator;
	private JLabel lblStatationInformation;
	private JButton btnUseMap;
	private JMenu menuFile;

	private JButton btnSaveStationData;
	private JButton btnCancel;
	private JMenuItem menuCleanCache;
	private JTextField txtQrzUser;
	private JPasswordField qrzPassword;
	private JCheckBox useCache;
	private JTextField txtLatitude;
	private JTextField txtLongitude;
	private JLabel lblNewLabel_2;
	private JEditorPane statusPane;
	private JEditorPane infoPane;
	private JLabel lblNewLabel_3;
	private JTextField textHamQthUser;
	private JPasswordField textHamQthPassword;

	public JEditorPane getStatusPane() {
		return statusPane;
	}

	public void setStatusPane(JEditorPane statusPane) {
		this.statusPane = statusPane;
	}

	/**
	 * This method initializes txtFileName
	 * 
	 * @return javax.swing.JTextField
	 */
	public JTextField getTxtFileName() {
		if (txtFileName == null) {
			txtFileName = new JTextField();
			txtFileName.setBounds(new Rectangle(16, 139, 303, 30));
		}
		return txtFileName;
	}

	/**
	 * This method initializes btnBrowseFile
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getBtnBrowseFile() {
		if (btnBrowseFile == null) {
			btnBrowseFile = new JButton();
			btnBrowseFile.setText(Messages.getString("GeoLogMain.btnBrowseFile.text")); //$NON-NLS-1$
			btnBrowseFile.setBounds(new Rectangle(333, 139, 78, 30));

		}

		return btnBrowseFile;
	}

	/**
	 * This method initializes tabStep2
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getTabStep2() {
		if (tabStep2 == null) {
			lblCallResolutionTitle = new JLabel();
			lblCallResolutionTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCallResolutionTitle.setBounds(new Rectangle(-5, 206, 165, 27));
			lblCallResolutionTitle.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblCallResolutionTitle.setText(Messages.getString("GeoLogMain.lblCallResolutionTitle.text")); //$NON-NLS-1$
			lblPathOption2 = new JLabel();
			lblPathOption2.setBounds(new Rectangle(264, 177, 112, 21));
			lblPathOption2.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblPathOption2.setText(Messages.getString("GeoLogMain.lblPathOption2.text")); //$NON-NLS-1$
			lblPathOption1 = new JLabel();
			lblPathOption1.setBounds(new Rectangle(264, 148, 84, 21));
			lblPathOption1.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblPathOption1.setText(Messages.getString("GeoLogMain.lblPathOption1.text")); //$NON-NLS-1$
			lnlPathTitle = new JLabel();
			lnlPathTitle.setBounds(new Rectangle(232, 120, 189, 27));
			lnlPathTitle.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lnlPathTitle.setText(Messages.getString("GeoLogMain.lnlPathTitle.text")); //$NON-NLS-1$
			lblTimeOption1 = new JLabel();
			lblTimeOption1.setBounds(new Rectangle(56, 147, 84, 23));
			lblTimeOption1.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblTimeOption1.setText(Messages.getString("GeoLogMain.lblTimeOption1.text")); //$NON-NLS-1$
			timeOption2 = new JLabel();
			timeOption2.setBounds(new Rectangle(56, 176, 112, 23));
			timeOption2.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			timeOption2.setText(Messages.getString("GeoLogMain.timeOption2.text")); //$NON-NLS-1$
			lblQsoTimeTitle = new JLabel();
			lblQsoTimeTitle.setBounds(new Rectangle(18, 120, 177, 27));
			lblQsoTimeTitle.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblQsoTimeTitle.setText(Messages.getString("GeoLogMain.lblQsoTimeTitle.text")); //$NON-NLS-1$
			lblQsoOrgTitle = new JLabel();
			lblQsoOrgTitle.setBounds(new Rectangle(18, 82, 121, 27));
			lblQsoOrgTitle.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblQsoOrgTitle.setText(Messages.getString("GeoLogMain.lblQsoOrgTitle.text")); //$NON-NLS-1$
			tabStep2 = new JPanel();
			tabStep2.setLayout(null);
			tabStep2.setBackground(SystemColor.controlHighlight);
			tabStep2.add(getTxtIntroStep2(), null);
			tabStep2.add(lblIconStep2, null);
			tabStep2.add(lblTitleStep2, null);
			tabStep2.add(getCmbQsoOrgOptions(), null);
			tabStep2.add(lnlPathTitle, null);
			tabStep2.add(lblQsoOrgTitle, null);
			tabStep2.add(lblQsoTimeTitle, null);
			tabStep2.add(getRadioTimeOption1(), null);
			tabStep2.add(lblTimeOption1, null);
			tabStep2.add(getRadioTimeOption2(), null);
			tabStep2.add(timeOption2, null);
			tabStep2.add(getRadioPathOption1(), null);
			tabStep2.add(lblPathOption1, null);
			tabStep2.add(getRadioPathOption2(), null);
			tabStep2.add(lblPathOption2, null);
			tabStep2.add(lblCallResolutionTitle, null);
			tabStep2.add(getCmbResolutionOptions(), null);
			tabStep2.add(getTxtLocationsHelp(), null);

			useCache = new JCheckBox(""); //$NON-NLS-1$
			useCache.setBackground(SystemColor.controlHighlight);
			useCache.setBounds(170, 240, 21, 23);
			tabStep2.add(useCache);

			JLabel lblNewLabel_1 = new JLabel(Messages.getString("GeoLogMain.lblNewLabel_1.text")); //$NON-NLS-1$
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblNewLabel_1.setBounds(94, 242, 66, 18);
			tabStep2.add(lblNewLabel_1);

			JTextPane textPane = new JTextPane();
			textPane.setBackground(SystemColor.controlHighlight);
			textPane.setContentType(Messages.getString("GeoLogMain.textPane.contentType")); //$NON-NLS-1$
			textPane.setText(Messages.getString("GeoLogMain.textPane.text_1")); //$NON-NLS-1$
			textPane.setBounds(197, 240, 195, 22);
			tabStep2.add(textPane);
		}
		return tabStep2;
	}

	/**
	 * This method initializes cmbQsoOrgOptions
	 * 
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getCmbQsoOrgOptions() {
		if (cmbQsoOrgOptions == null) {
			cmbQsoOrgOptions = new JComboBox(
					new String[] {
							Messages.getString("GeoLogMain.none"), Messages.getString("GeoLogMain.bands"), Messages.getString("GeoLogMain.bandsOperator"), Messages.getString("GeoLogMain.operators"), Messages.getString("GeoLogMain.operatorsBand") }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			cmbQsoOrgOptions
					.setModel(new DefaultComboBoxModel(
							new String[] {
									Messages.getString("GeoLogMain.None"), Messages.getString("GeoLogMain.bands"), Messages.getString("GeoLogMain.bandsOperator"), Messages.getString("GeoLogMain.operators"), Messages.getString("GeoLogMain.operatorsBand") })); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

			cmbQsoOrgOptions.setBounds(new Rectangle(140, 82, 256, 27));
			cmbQsoOrgOptions.setFont(new Font("Tahoma", Font.PLAIN, 12)); //$NON-NLS-1$

		}
		return cmbQsoOrgOptions;
	}

	/**
	 * This method initializes radioTimeOption1
	 * 
	 * @return javax.swing.JRadioButton
	 */
	public JRadioButton getRadioTimeOption1() {
		if (radioTimeOption1 == null) {
			radioTimeOption1 = new JRadioButton();
			radioTimeOption1.setBounds(new Rectangle(22, 147, 21, 23));
			radioTimeOption1.setBackground(SystemColor.controlHighlight);
			radioTimeOption1.setMnemonic(KeyEvent.VK_1);
			radioTimeOption1.setEnabled(true);
			radioTimeOption1.setActionCommand("1"); //$NON-NLS-1$
			radioTimeOption1.setSelected(true);

		}
		return radioTimeOption1;
	}

	/**
	 * This method initializes radioTimeOption2
	 * 
	 * @return javax.swing.JRadioButton
	 */
	public JRadioButton getRadioTimeOption2() {
		if (radioTimeOption2 == null) {
			radioTimeOption2 = new JRadioButton();
			radioTimeOption2.setBounds(new Rectangle(22, 176, 21, 23));
			radioTimeOption2.setActionCommand("2"); //$NON-NLS-1$
			radioTimeOption2.setBackground(SystemColor.controlHighlight);
		}
		return radioTimeOption2;
	}

	/**
	 * This method initializes radioPathOption1
	 * 
	 * @return javax.swing.JRadioButton
	 */
	public JRadioButton getRadioPathOption1() {
		if (radioPathOption1 == null) {
			radioPathOption1 = new JRadioButton();
			radioPathOption1.setBounds(new Rectangle(232, 148, 21, 21));
			radioPathOption1.setBackground(SystemColor.controlHighlight);
			radioPathOption1.setActionCommand("1"); //$NON-NLS-1$
			radioPathOption1.setSelected(true);
		}
		return radioPathOption1;
	}

	/**
	 * This method initializes radioPathOption2
	 * 
	 * @return javax.swing.JRadioButton
	 */
	public JRadioButton getRadioPathOption2() {
		if (radioPathOption2 == null) {
			radioPathOption2 = new JRadioButton();
			radioPathOption2.setBounds(new Rectangle(232, 177, 21, 21));
			radioPathOption2.setActionCommand("2"); //$NON-NLS-1$
			radioPathOption2.setBackground(SystemColor.controlHighlight);
		}
		return radioPathOption2;
	}

	/**
	 * This method initializes tabStep1
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getTabStep1() {
		if (tabStep1 == null) {
			step1Heading = new JLabel();
			step1Heading.setBounds(new Rectangle(40, 15, 292, 27));
			step1Heading.setFont(new Font("Tahoma", Font.BOLD, 18)); //$NON-NLS-1$
			step1Heading.setText(Messages.getString("GeoLogMain.lblTitleStep1.text")); //$NON-NLS-1$
			tabStep1 = new JPanel();
			tabStep1.setLayout(null);
			tabStep1.setBackground(SystemColor.controlHighlight);
			tabStep1.setBorder(BorderFactory.createLineBorder(Color.gray, 0));
			tabStep1.add(getBtnBrowseFile(), null);
			tabStep1.add(step1Icon, null);
			tabStep1.add(step1titlePath, null);
			tabStep1.add(getTxtFileName(), null);
			tabStep1.add(getStep1IntroText(), null);
			tabStep1.add(step1Heading, null);

			JEditorPane txtIntro2Step1 = new JEditorPane();
			txtIntro2Step1.setEditable(false);
			txtIntro2Step1.setBackground(SystemColor.controlHighlight);
			txtIntro2Step1.setContentType("text/html"); //$NON-NLS-1$
			txtIntro2Step1.setText(Messages.getString("GeoLogMain.textPane.text")); //$NON-NLS-1$
			txtIntro2Step1.setBounds(16, 248, 395, 117);
			txtIntro2Step1.addHyperlinkListener(new HyperlinkListener() {

				@Override
				public void hyperlinkUpdate(HyperlinkEvent hle) {
					if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {

						try {

							if (java.awt.Desktop.isDesktopSupported()) {
								java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
								desktop.browse(hle.getURL().toURI());

							}

						} catch (Exception e) {

							System.err.println(e.getMessage());
						}

					}

				}
			});

			tabStep1.add(txtIntro2Step1);

			JLabel step1TitleAdif = new JLabel(Messages.getString("GeoLogMain.lblAdif.text")); //$NON-NLS-1$
			step1TitleAdif.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			step1TitleAdif.setBounds(10, 216, 385, 30);
			tabStep1.add(step1TitleAdif);
			tabStep1.add(getLblNewLabel_3());
		}
		return tabStep1;
	}

	/**
	 * This method initializes cmbResolutionOptions
	 * 
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getCmbResolutionOptions() {
		if (cmbResolutionOptions == null) {
			cmbResolutionOptions = new JComboBox();

			// GeoLogMain.options.QRZ
			// GeoLogMain.options.FIELD
			// GeoLogMain.options.CACHE
			cmbResolutionOptions.setModel(new DefaultComboBoxModel(new String[] 
					{ 
					Messages.getString("GeoLogMain.options.QRZ"), 
					Messages.getString("GeoLogMain.options.HAMQTH"),
					Messages.getString("GeoLogMain.options.FIELD"), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("GeoLogMain.options.CACHE") //$NON-NLS-1$

					}));
			cmbResolutionOptions.setBounds(new Rectangle(170, 206, 218, 27));
			cmbResolutionOptions.setFont(new Font("Tahoma", Font.PLAIN, 12)); //$NON-NLS-1$
		}
		return cmbResolutionOptions;
	}

	/**
	 * This method initializes tabStep3
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getTabStep3() {
		if (tabStep3 == null) {
			lblTitleStep3 = new JLabel();
			lblTitleStep3.setText(Messages.getString("GeoLogMain.lblTitleStep3.text")); //$NON-NLS-1$
			lblTitleStep3.setLocation(new Point(40, 15));
			lblTitleStep3.setFont(new Font("Tahoma", Font.BOLD, 18)); //$NON-NLS-1$
			lblTitleStep3.setSize(new Dimension(213, 26));
			txtIcon3 = new JLabel();
			txtIcon3.setIcon(new ImageIcon(getClass().getResource("/resources/orange03.png"))); //$NON-NLS-1$
			txtIcon3.setSize(new Dimension(27, 26));
			txtIcon3.setLocation(new Point(6, 15));
			txtIcon3.setText(""); //$NON-NLS-1$
			tabStep3 = new JPanel();
			tabStep3.setLayout(null);
			tabStep3.setBackground(SystemColor.controlHighlight);
			tabStep3.add(txtIcon3, null);
			tabStep3.add(getProccessstatus(), null);
			tabStep3.add(lblTitleStep3, null);
			tabStep3.add(getTxtIntroStep3(), null);
			tabStep3.add(getBtnStart(), null);

			infoPane = new JEditorPane();
			infoPane.setBackground(SystemColor.info);
			infoPane.setForeground(Color.BLACK);
			infoPane.setEditable(false);
			infoPane.setContentType(Messages.getString("GeoLogMain.editorPane.contentType")); //$NON-NLS-1$
			infoPane.setText(Messages.getString("GeoLogMain.editorPane.text")); //$NON-NLS-1$
			infoPane.setBounds(28, 208, 370, 105);
			tabStep3.add(infoPane);

			statusPane = new JEditorPane();
			statusPane.setContentType(Messages.getString("GeoLogMain.editorPane_1.contentType")); //$NON-NLS-1$
			statusPane.setBackground(SystemColor.controlHighlight);
			statusPane.setEditable(false);
			statusPane.setText(Messages.getString("GeoLogMain.editorPane_1.text")); //$NON-NLS-1$
			statusPane.setBounds(28, 160, 370, 37);
			tabStep3.add(statusPane);

		}
		return tabStep3;
	}

	/**
	 * This method initializes tabPanel
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	public JTabbedPane getTabPanel() {
		if (tabPanel == null) {
			tabPanel = new JTabbedPane();
			tabPanel.setTabPlacement(JTabbedPane.TOP);
			tabPanel.setName(""); //$NON-NLS-1$
			tabPanel.setBounds(new Rectangle(0, 0, 426, 506));
			tabPanel.addTab(Messages.getString("GeoLogMain.file"), null, getTabStep1(), null); //$NON-NLS-1$
			tabPanel.addTab(Messages.getString("GeoLogMain.options"), null, getTabStep2(), null); //$NON-NLS-1$
			tabPanel.addTab(Messages.getString("GeoLogMain.process"), null, getTabStep3(), null); //$NON-NLS-1$

			JPanel panel = new JPanel();
			tabPanel.addTab("New tab", null, panel, null); //$NON-NLS-1$
			panel.setLayout(null);

			JPanel panel_1 = new JPanel();
			panel_1.setBounds(24, 55, 376, 139);
			panel.add(panel_1);
			panel_1.setLayout(null);

			txtCall = new JTextField();
			txtCall.setBounds(93, 14, 154, 20);
			panel_1.add(txtCall);
			txtCall.setText(Messages.getString("GeoLogMain.textField.text")); //$NON-NLS-1$
			txtCall.setColumns(10);

			JLabel lblCall = new JLabel(Messages.getString("GeoLogMain.lblCall.text")); //$NON-NLS-1$
			lblCall.setBounds(10, 14, 114, 14);
			panel_1.add(lblCall);
			lblCall.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$

			JLabel lblLocator = new JLabel(Messages.getString("GeoLogMain.lblLocator.text")); //$NON-NLS-1$
			lblLocator.setBounds(10, 44, 73, 14);
			panel_1.add(lblLocator);
			lblLocator.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$

			JLabel lblLatitude = new JLabel(Messages.getString("GeoLogMain.lblLatitude.text")); //$NON-NLS-1$
			lblLatitude.setBounds(10, 77, 73, 14);
			panel_1.add(lblLatitude);
			lblLatitude.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$

			JLabel lblLongitude = new JLabel(Messages.getString("GeoLogMain.lblLongitude.text")); //$NON-NLS-1$
			lblLongitude.setBounds(10, 108, 73, 14);
			panel_1.add(lblLongitude);
			lblLongitude.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$

			txtLocator = new JTextField();
			txtLocator.setBounds(93, 45, 154, 20);
			panel_1.add(txtLocator);
			txtLocator.setText(Messages.getString("GeoLogMain.textField_1.text")); //$NON-NLS-1$
			txtLocator.setColumns(10);

			btnUseMap = new JButton(Messages.getString("GeoLogMain.btnUseMap.text")); //$NON-NLS-1$
			btnUseMap.setBounds(257, 50, 86, 23);
			panel_1.add(btnUseMap);
			panel_1.add(getTxtLatitude());
			panel_1.add(getTxtLongitude());
			panel_1.add(getLblNewLabel_2());
			panel.add(getLblStatationInformation());

			btnCancel = new JButton(Messages.getString("GeoLogMain.btnCancel.text")); //$NON-NLS-1$
			btnCancel.setBounds(215, 444, 86, 23);
			panel.add(btnCancel);

			btnSaveStationData = new JButton(Messages.getString("GeoLogMain.btnSave.text")); //$NON-NLS-1$
			btnSaveStationData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnSaveStationData.setBounds(314, 444, 86, 23);
			panel.add(btnSaveStationData);

			JPanel panel_2 = new JPanel();
			panel_2.setBounds(24, 205, 376, 102);
			panel.add(panel_2);
			panel_2.setLayout(null);

			JLabel lblNewLabel = new JLabel(Messages.getString("GeoLogMain.lblNewLabel.text_2")); //$NON-NLS-1$
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblNewLabel.setBounds(10, 0, 243, 22);
			panel_2.add(lblNewLabel);

			txtQrzUser = new JTextField();

			txtQrzUser.setBounds(107, 23, 144, 20);
			panel_2.add(txtQrzUser);
			txtQrzUser.setColumns(10);

			JLabel lblUser = new JLabel(Messages.getString("GeoLogMain.lblUser.text")); //$NON-NLS-1$
			lblUser.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblUser.setBounds(20, 26, 77, 14);
			panel_2.add(lblUser);

			JLabel lblPassword = new JLabel(Messages.getString("GeoLogMain.lblPassword.text")); //$NON-NLS-1$
			lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			lblPassword.setBounds(20, 56, 77, 14);
			panel_2.add(lblPassword);

			qrzPassword = new JPasswordField();
			qrzPassword.setText(""); //$NON-NLS-1$
			qrzPassword.setBounds(107, 51, 144, 20);
			panel_2.add(qrzPassword);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(377, 11, 2, 2);
			panel.add(scrollPane);
			
			JPanel panel_3 = new JPanel();
			panel_3.setLayout(null);
			panel_3.setBounds(24, 318, 376, 96);
			panel.add(panel_3);
			
			JLabel lblHamQthAccount = new JLabel(Messages.getString("GeoLogMain.lblHamQthAccount.text")); //$NON-NLS-1$
			lblHamQthAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblHamQthAccount.setBounds(10, 0, 243, 22);
			panel_3.add(lblHamQthAccount);
			
			textHamQthUser = new JTextField();
			textHamQthUser.setColumns(10);
			textHamQthUser.setBounds(107, 33, 144, 20);
			panel_3.add(textHamQthUser);
			
			JLabel label_1 = new JLabel("User");
			label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_1.setBounds(20, 36, 77, 14);
			panel_3.add(label_1);
			
			JLabel label_2 = new JLabel("Password");
			label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
			label_2.setBounds(20, 66, 77, 14);
			panel_3.add(label_2);
			
			textHamQthPassword = new JPasswordField();
			textHamQthPassword.setText("");
			textHamQthPassword.setBounds(107, 61, 144, 20);
			panel_3.add(textHamQthPassword);
		}
		return tabPanel;
	}

	/**
	 * This method initializes proccessstatus
	 * 
	 * @return javax.swing.JProgressBar
	 */
	public JProgressBar getProccessstatus() {
		if (proccessstatus == null) {
			proccessstatus = new JProgressBar();
			proccessstatus.setBounds(new Rectangle(28, 114, 287, 37));
			proccessstatus.setValue(0);
			proccessstatus.setString("80%"); //$NON-NLS-1$
		}
		return proccessstatus;
	}

	/**
	 * This method initializes txtIntroStep1
	 * 
	 * @return javax.swing.JTextPane
	 */
	public JTextPane getStep1IntroText() {
		if (step1IntroText == null) {
			step1IntroText = new JTextPane();
			step1IntroText.setContentType("text/html"); //$NON-NLS-1$
			step1IntroText.setFont(new Font("Arial", Font.PLAIN, 12)); //$NON-NLS-1$
			step1IntroText.setBackground(SystemColor.controlHighlight);
			step1IntroText.setSize(new Dimension(379, 45));
			step1IntroText.setLocation(new Point(16, 53));
			step1IntroText.setText(Messages.getString("GeoLogMain.txtIntroStep1.text")); //$NON-NLS-1$
		}
		return step1IntroText;
	}

	/**
	 * This method initializes txtIntroStep3
	 * 
	 * @return javax.swing.JTextPane
	 */
	public JTextPane getTxtIntroStep3() {
		if (txtIntroStep3 == null) {
			txtIntroStep3 = new JTextPane();
			txtIntroStep3.setBackground(SystemColor.controlHighlight);
			txtIntroStep3.setSize(new Dimension(364, 54));
			txtIntroStep3.setLocation(new Point(28, 49));
			txtIntroStep3.setText(Messages.getString("GeoLogMain.txtIntroStep3.text")); //$NON-NLS-1$
		}
		return txtIntroStep3;
	}

	/**
	 * This method initializes btnStart
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getBtnStart() {
		if (btnStart == null) {
			btnStart = new JButton();
			btnStart.setBounds(new Rectangle(329, 114, 69, 37));
			btnStart.setText(Messages.getString("GeoLogMain.btnStart.text")); //$NON-NLS-1$

		}
		return btnStart;
	}

	/**
	 * This method initializes btnNext
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton();
			btnNext.setEnabled(false);
			btnNext.setText(Messages.getString("GeoLogMain.btnNext.text")); //$NON-NLS-1$
			btnNext.setBounds(new Rectangle(312, 512, 96, 26));
			btnNext.setPreferredSize(new Dimension(64, 26));

		}
		return btnNext;
	}

	/**
	 * This method initializes txtLocationsHelp
	 * 
	 * @return javax.swing.JTextPane
	 */
	public JEditorPane getTxtLocationsHelp() {
		if (txtLocationsHelp == null) {
			txtLocationsHelp = new JEditorPane();
			txtLocationsHelp.setEditable(false);
			txtLocationsHelp.setBounds(new Rectangle(10, 270, 395, 197));
			txtLocationsHelp.setContentType("text/html"); //$NON-NLS-1$
			txtLocationsHelp.setBackground(SystemColor.info);
			txtLocationsHelp.setFont(new Font("Tahoma", Font.PLAIN, 13)); //$NON-NLS-1$
			txtLocationsHelp.setText(Messages.getString("GeoLogMain.txtLocationsHelp.text")); //$NON-NLS-1$
			txtLocationsHelp.addHyperlinkListener(new HyperlinkListener() {

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
		}
		return txtLocationsHelp;
	}

	/**
	 * This method initializes txtIntroStep2
	 * 
	 * @return javax.swing.JTextPane
	 */
	public JTextPane getTxtIntroStep2() {
		if (txtIntroStep2 == null) {
			txtIntroStep2 = new JTextPane();
			txtIntroStep2.setBounds(new Rectangle(29, 49, 363, 22));
			txtIntroStep2.setBackground(SystemColor.controlHighlight);
			txtIntroStep2.setContentType("text/html"); //$NON-NLS-1$
			txtIntroStep2.setFont(new Font("Tahoma", Font.PLAIN, 12)); //$NON-NLS-1$
			txtIntroStep2.setText(Messages.getString("GeoLogMain.txtIntroStep2.text")); //$NON-NLS-1$
		}
		return txtIntroStep2;
	}

	/**
	 * This method initializes btnPrev
	 * 
	 * @return javax.swing.JButton
	 */
	public JButton getBtnPrev() {
		if (btnPrev == null) {
			btnPrev = new JButton();
			btnPrev.setText(Messages.getString("GeoLogMain.btnPrev.text")); //$NON-NLS-1$
			btnPrev.setEnabled(false);
			btnPrev.setBounds(new Rectangle(10, 512, 97, 26));

		}
		return btnPrev;
	}

	/**
	 * This is the default constructor
	 */

	public GeoLogMain() {
		super();

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //$NON-NLS-1$
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			log.error("Error", e);
		}
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
		this.setSize(432, 577);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getMainPanel());
		this.setTitle(Messages.getString("GeoLogMain.this.title")); //$NON-NLS-1$

	}

	/**
	 * This method initializes mainPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getMainPanel() {
		if (mainPanel == null) {
			lblTitleStep2 = new JLabel();
			lblTitleStep2.setText(Messages.getString("GeoLogMain.lblTitleStep2.text")); //$NON-NLS-1$
			lblTitleStep2.setSize(new Dimension(176, 27));
			lblTitleStep2.setLocation(new Point(40, 15));
			lblTitleStep2.setFont(new Font("Tahoma", Font.BOLD, 18)); //$NON-NLS-1$
			lblIconStep2 = new JLabel();
			lblIconStep2.setIcon(new ImageIcon(getClass().getResource("/resources/orange02.png"))); //$NON-NLS-1$
			lblIconStep2.setBounds(new Rectangle(6, 15, 27, 27));
			lblIconStep2.setText(""); //$NON-NLS-1$
			step1Icon = new JLabel();
			step1Icon.setFont(new Font("Arial", Font.BOLD, 12)); //$NON-NLS-1$
			step1Icon.setIcon(new ImageIcon(getClass().getResource("/resources/orange01.png"))); //$NON-NLS-1$
			step1Icon.setSize(new Dimension(27, 27));
			step1Icon.setLocation(new Point(6, 15));
			step1Icon.setText(""); //$NON-NLS-1$
			step1titlePath = new JLabel();
			step1titlePath.setFont(new Font("Tahoma", Font.BOLD, 12)); //$NON-NLS-1$
			step1titlePath.setBounds(new Rectangle(10, 103, 297, 30));
			step1titlePath.setText(Messages.getString("GeoLogMain.lblFileTitle.text")); //$NON-NLS-1$
			mainPanel = new JPanel();
			mainPanel.setLayout(null);
			mainPanel.setBackground(SystemColor.controlHighlight);
			mainPanel.add(getMenuBar_1());
			mainPanel.add(getTabPanel(), null);
			mainPanel.add(getBtnNext(), null);
			mainPanel.add(getBtnPrev(), null);
		}
		return mainPanel;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 426, 32);

			menuFile = new JMenu(Messages.getString("GeoLogMain.mnNewMenu.text")); //$NON-NLS-1$
			menuBar.add(menuFile);

			mnuMyStation = new JMenuItem(Messages.getString("GeoLogMain.mntmNewMenuItem_1.text_2")); //$NON-NLS-1$
			menuFile.add(mnuMyStation);

			mntmOpen = new JMenuItem(Messages.getString("GeoLogMain.mntmOpen.text")); //$NON-NLS-1$
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
			menuFile.add(mntmOpen);

			mntmNewMenuItem = new JMenuItem(Messages.getString("GeoLogMain.mntmNewMenuItem.text")); //$NON-NLS-1$
			mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
			menuFile.add(mntmNewMenuItem);

			JMenu menuTools = new JMenu(Messages.getString("GeoLogMain.menuTools.text")); //$NON-NLS-1$
			menuBar.add(menuTools);

			menuViewCache = new JMenuItem(Messages.getString("GeoLogMain.mnuMyDb.text")); //$NON-NLS-1$
			menuTools.add(menuViewCache);

			menuCleanCache = new JMenuItem(Messages.getString("GeoLogMain.mntmNewMenuItem_1.text_3")); //$NON-NLS-1$
			menuTools.add(menuCleanCache);

			menuHelp = new JMenu(Messages.getString("GeoLogMain.mnNewMenu_1.text")); //$NON-NLS-1$
			menuBar.add(menuHelp);

			itemAbout = new JMenuItem(Messages.getString("GeoLogMain.itemAbout.text")); //$NON-NLS-1$
			itemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
			itemAbout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					About a = new About();
					a.setModal(true);
					a.setVisible(true);

				}
			});
			menuHelp.add(itemAbout);

			itemHelp = new JMenuItem(Messages.getString("GeoLogMain.itemHelp.text")); //$NON-NLS-1$
			itemHelp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

					if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {

						log.info("Desktop doesn't support the browse action (fatal)"); //$NON-NLS-1$
						System.exit(1);
					} else {
						try {
							desktop.browse(new URL(Messages.getString("GeoLogMain.siteURL")).toURI()); //$NON-NLS-1$
						} catch (Exception e) {
							// TODO: handle exception
						}
					}

				}
			});
			itemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			menuHelp.add(itemHelp);
		}
		return menuBar;
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction"); //$NON-NLS-1$
			putValue(SHORT_DESCRIPTION, "Some short description"); //$NON-NLS-1$
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private Action getAction() {
		if (action == null) {
			action = new SwingAction();
		}
		return action;
	}

	public JMenuItem getMntmOpen() {
		return mntmOpen;
	}

	public JMenuItem getMntmNewMenuItem() {
		return mntmNewMenuItem;
	}

	protected JMenuItem getItemAbout() {
		return itemAbout;
	}

	public JMenuItem getItemHelp() {
		return itemHelp;
	}

	public JMenuItem getMnuMyStation() {
		return mnuMyStation;
	}

	private JLabel getLblStatationInformation() {
		if (lblStatationInformation == null) {
			lblStatationInformation = new JLabel(Messages.getString("GeoLogMain.lblStatationInformation.text")); //$NON-NLS-1$
			lblStatationInformation.setFont(new Font("Tahoma", Font.BOLD, 18)); //$NON-NLS-1$
			lblStatationInformation.setBounds(24, 11, 283, 35);
		}
		return lblStatationInformation;
	}

	public JButton getBtnUseMap() {
		return btnUseMap;
	}

	public JMenu getMenuFile() {
		return menuFile;
	}

	public JTextField getTxtCall() {
		return txtCall;
	}

	public JTextField getTxtLocator() {
		return txtLocator;
	}

	public JButton getBtnSaveStationData() {
		return btnSaveStationData;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JTextField getTxtQrzUser() {
		return txtQrzUser;
	}

	public JPasswordField getQrzPassword() {
		return qrzPassword;
	}

	public JCheckBox getUseCache() {
		return useCache;
	}

	private JTextField getTxtLatitude() {
		if (txtLatitude == null) {
			txtLatitude = new JTextField();
			txtLatitude.setText(""); //$NON-NLS-1$
			txtLatitude.setBounds(93, 77, 154, 20);
			txtLatitude.setColumns(10);
		}
		return txtLatitude;
	}

	private JTextField getTxtLongitude() {
		if (txtLongitude == null) {
			txtLongitude = new JTextField();
			txtLongitude.setText(""); //$NON-NLS-1$
			txtLongitude.setBounds(93, 108, 154, 20);
			txtLongitude.setColumns(10);
		}
		return txtLongitude;
	}

	public JTextField getFieldLatitude() {
		return getTxtLatitude();
	}

	public JTextField getFieldLongitude() {
		return getTxtLongitude();
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel(""); //$NON-NLS-1$
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11)); //$NON-NLS-1$
			lblNewLabel_2.setBounds(10, 75, 254, 23);
		}
		return lblNewLabel_2;
	}

	public JMenuItem getMenuCleanCache() {
		return menuCleanCache;
	}

	public JMenuItem getMenuViewCache() {
		return menuViewCache;
	}

	public JMenu getMenuHelp() {
		return menuHelp;
	}

	public JEditorPane getEditorStatus() {
		return statusPane;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel(); //$NON-NLS-1$
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setIcon(new ImageIcon(GeoLogMain.class.getResource("/resources/paypal-donate-button.png")));
			lblNewLabel_3.setBounds(6, 350, 405, 117);
			lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblNewLabel_3.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					log.info("");
					// TODO Auto-generated method stub
					
					try {

						URL  url= new URL("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=U9T5UH88R22MW&lc=AR&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donate_LG%2egif%3aNonHosted");
						java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
						desktop.browse(url.toURI());
					} catch (Exception e) {

						System.err.println(e.getMessage());
					}

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent arg) {
					// TODO Auto-generated method stub
					if (!java.awt.Desktop.isDesktopSupported()) {

						System.err.println("Desktop is not supported (fatal)");
						System.exit(1);
					}

					java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

					if (!desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {

						System.err.println("Desktop doesn't support the browse action (fatal)");
						System.exit(1);
					}

					try {

						//java.net.URI uri = new java.net.URI("https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=sdimunzio@gmail.com&lc=US&item_name=Sebastian Dimunzio&item_number=0001&no_note=0&currency_code=USD&bn=PP-DonationsBF:btn_donateCC_LG.gif:NonHostedGuest".endsWith(suffix));
						///desktop.browse(uri);
					} catch (Exception e) {

						System.err.println(e.getMessage());
					}

				}
			});
		}
		return lblNewLabel_3;
	}
	public JTextField getTextHamQthUser() {
		return textHamQthUser;
	}
	public JPasswordField getTextHamQthPassword() {
		return textHamQthPassword;
	}
} // @jve:decl-index=0:visual-constraint="18,-316"
