package org.hamradio.lw4hbr.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.model.ConfigOptionsModel;

public class ConfigurationController extends AbstractController<GeoLogMain> {

	ConfigOptionsModel model;

	public ConfigurationController(GeoLogMain view) {
		super(view);
		model = new ConfigOptionsModel();
		registerModel(ConfigOptionsModel.MODEL_KEY,model);
		initActions();
	}

	private void initActions() {
		view.getRadioTimeOption1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.getRadioTimeOption1().setSelected(true);
				model.setTimeType(Integer.parseInt(view.getRadioTimeOption1()
						.getActionCommand()));

			}
		});

		view.getRadioTimeOption2().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				view.getRadioTimeOption2().setSelected(true);
				model.setTimeType(Integer.parseInt(view.getRadioTimeOption2()
						.getActionCommand()));
			}
		});
		
		
		
		view.getRadioPathOption1().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.getRadioPathOption1().setSelected(true);
				model.setPathType(Integer.parseInt(view.getRadioPathOption1().getActionCommand()));

			}
		});

		view.getRadioPathOption2().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.getRadioPathOption2().setSelected(true);
				model.setPathType(Integer.parseInt(view.getRadioPathOption2().getActionCommand()));
			}
		});
		
		
		view.getCmbQsoOrgOptions().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//System.out.println(view.getCmbQsoOrgOptions().getSelectedIndex());
				model.setQsoOrganizationType(view.getCmbQsoOrgOptions().getSelectedIndex());
				
			}
		});
		
		view.getCmbResolutionOptions().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//System.out.println(view.getCmbQsoOrgOptions().getSelectedIndex());
				model.setLocationResolutionType(view.getCmbResolutionOptions().getSelectedIndex());
				if ((view.getCmbResolutionOptions().getSelectedIndex()==0)||(view.getCmbResolutionOptions().getSelectedIndex()==1)){
					view.getUseCache().setEnabled(true);
				}else{
					view.getUseCache().setEnabled(false);
				}
			}
		});
		
		
		
		view.getUseCache().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setUseCache(view.getUseCache().isSelected());
				
			}
		});
		
		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase(
				ConfigOptionsModel.TIME_TYPE_PROPERTY)) {
			if (model.getTimeType() == 1) {
				view.getRadioTimeOption1().setSelected(true);
				view.getRadioTimeOption2().setSelected(false);
			} else {
				view.getRadioTimeOption1().setSelected(false);
				view.getRadioTimeOption2().setSelected(true);
			}

		}
		
		
		if (evt.getPropertyName().equalsIgnoreCase(
				ConfigOptionsModel.PATH_TIME_PROPERTY)) {
			if (model.getPathType() == 1) {
				view.getRadioPathOption1().setSelected(true);
				view.getRadioPathOption2().setSelected(false);
			} else {
				view.getRadioPathOption1().setSelected(false);
				view.getRadioPathOption2().setSelected(true);
			}

		}

	}
}
