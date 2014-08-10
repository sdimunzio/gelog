package org.hamradio.lw4hbr.ui.controllers;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.io.File;

import javax.swing.JFileChooser;

import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.model.FilePathModel;

public class FilePathController extends AbstractController<GeoLogMain> {

	private FilePathModel model;

	public FilePathController(GeoLogMain view) {
		super(view);
		model = new FilePathModel();
		registerModel(FilePathModel.MODEL_KEY, model);
		initActions();
	}

	private void initActions() {
		// setup events
		
		ActionListener listener=new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new ExtensionFileFilter("ADI and ADIF", new String[] { "ADI", "ADIF" }));
				int returnVal = chooser.showOpenDialog(view.getMainPanel());
				
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					model.setFilePath(chooser.getSelectedFile().getPath());

				}
			}
		};
		
		view.getTxtFileName().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				model.setFilePath(view.getTxtFileName().getText());
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		})
		;
		view.getBtnBrowseFile().addActionListener(listener);
		view.getMntmOpen().addActionListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase(FILE_PATH_PROPERTY)) {
			view.getTxtFileName().setText((String) evt.getNewValue());
			
			if ((view.getTxtFileName().getText()!=null)&&(new File(view.getTxtFileName().getText()).exists())){
					view.getBtnNext().setEnabled(true);
			}else{
				view.getBtnNext().setEnabled(false);
			}
		}

	}

}
