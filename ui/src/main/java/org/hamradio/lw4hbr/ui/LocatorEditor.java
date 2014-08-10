package org.hamradio.lw4hbr.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;

public class LocatorEditor extends JFrame {
	private static Logger log = Logger.getLogger(LocatorEditor.class.getName());
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocatorEditor frame = new LocatorEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					log.error("Error",e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LocatorEditor() {
		setTitle(Messages.getString("LocatorEditor.this.title")); //$NON-NLS-1$

		try {

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 473, 525);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 628, 21);
			contentPane.add(menuBar);
			
			JMenu mnNewMenu = new JMenu(Messages.getString("LocatorEditor.mnNewMenu.text")); //$NON-NLS-1$
			menuBar.add(mnNewMenu);
			
			JMenuItem itemViewInQRZ = new JMenuItem(Messages.getString("LocatorEditor.itemViewInQRZ.text")); //$NON-NLS-1$
			mnNewMenu.add(itemViewInQRZ);
			
			JMenu mnNewMenu_1 = new JMenu(Messages.getString("LocatorEditor.mnNewMenu_1.text")); //$NON-NLS-1$
			menuBar.add(mnNewMenu_1);
			
			JMenuItem mntmDeleteSelected = new JMenuItem(Messages.getString("LocatorEditor.mntmDeleteSelected.text")); //$NON-NLS-1$
			mnNewMenu_1.add(mntmDeleteSelected);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 63, 452, 427);
			contentPane.add(scrollPane);

			table = new JTable();
			scrollPane.setViewportView(table);

			Connection con = DerbyManager.getInstance().getDbConnection();
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			
			ResultSet rs = stm.executeQuery("select CALL_ID , LOCATOR,LATITUDE,LONGITUDE from CALL_LOCATOR order by CALL_ID");

			LocatorTableModel model = new LocatorTableModel(rs,new String[]{"Call","Locator","Latitude","Longitude"});

			
			final TableRowSorter<LocatorTableModel> sorter = new TableRowSorter<LocatorTableModel>(model);
			
			
		    table.setRowSorter(sorter);
		    table.setModel(model);
			textField = new JTextField();
			
			textField.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent arg0) {

					
					
				}
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					sorter.setRowFilter(new RowFilter<Object, Object>(){
						@Override
						public boolean include(javax.swing.RowFilter.Entry<? extends Object, ? extends Object> entry) {
							// TODO Auto-generated method stub
							for (int i = entry.getValueCount() - 1; i >= 0; i--) {
							       if (entry.getStringValue(i).toUpperCase().startsWith(textField.getText().toUpperCase())) {
							         // The value starts with "a", include it
							         return true;
							       }
							     }
							return false;
						}
						
					});
					
				}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			textField.setBounds(71, 32, 269, 20);
			contentPane.add(textField);
			textField.setColumns(10);
			
			JLabel lblFind = new JLabel(Messages.getString("LocatorEditor.lblFind.text")); //$NON-NLS-1$
			lblFind.setBounds(10, 32, 51, 20);
			contentPane.add(lblFind);
			
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error",e);
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
