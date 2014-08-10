package org.hamradio.lw4hbr.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.hamradio.lw4hbr.derby.DerbyManager;
import org.hamradio.lw4hbr.model.StatusChangeListener;
import org.hamradio.lw4hbr.model.StatusEvent;
import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.LocatorEditor;
import org.hamradio.lw4hbr.ui.model.TabPaneModel;

public class MenuController extends AbstractController<GeoLogMain> implements StatusChangeListener {

	private static Logger log = Logger.getLogger(MenuController.class.getName());
	
	public MenuController(GeoLogMain view) {
		super(view);
		initComponets();
	}

	private void initComponets() {

		view.getMnuMyStation().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TabPaneModel tabPaneModel = (TabPaneModel) getRegisteredModel(TabPaneModel.MODEL_KEY);
				tabPaneModel.setPanelIndex(3);

			}
		});
		view.getMenuViewCache().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				LocatorEditor editor = new LocatorEditor();
				editor.setVisible(true);

			}
		});
		
		
	
/*
		view.getMntmLocatorImport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(view.getMainPanel());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// model.setFilePath(chooser.getSelectedFile().getPath());
					LocatorImport imp = new LocatorImport();
					imp.setFile(chooser.getSelectedFile());
					imp.setVisible(true);
					


				}
			}

		});
		*/
		
		view.getMenuCleanCache().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response;
				   response = JOptionPane.showConfirmDialog(view.getMainPanel(), Messages.getString("MenuController.sureQuestion")); //$NON-NLS-1$
				   	if(response==0){
				   		try {
							DerbyManager.getInstance().cleanDatabase();
							 JOptionPane.showInternalMessageDialog(view.getMainPanel(), Messages.getString("MenuController.cacheRemoved")); //$NON-NLS-1$
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							log.error("Error",e1);
						}
				   	}
			
			}
		});

	}

	@Override
	public void fireEvent(StatusEvent event) {
		// TODO Auto-generated method stub

	}

}
