package org.hamradio.lw4hbr.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hamradio.lw4hbr.ui.GeoLogMain;
import org.hamradio.lw4hbr.ui.model.TabPaneModel;

public class TabPanelController extends AbstractController<GeoLogMain> {

	private TabPaneModel model = null;

	public TabPanelController(GeoLogMain view) {
		super(view);
		model = new TabPaneModel();
		registerModel(TabPaneModel.MODEL_KEY, model);
		initActions();
		// TODO Auto-generated constructor stub
	}

	private void initActions() {
		view.getTabPanel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				model.setPanelIndex(view.getTabPanel().getSelectedIndex());
			}
		});

		view.getBtnNext().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				model.setPanelIndex(view.getTabPanel().getSelectedIndex() + 1);

			}
		});
		view.getBtnPrev().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setPanelIndex(view.getTabPanel().getSelectedIndex() - 1);

			}
		});
	}

	public void propertyChange(PropertyChangeEvent evt) {

		if (evt.getPropertyName().equalsIgnoreCase(MainController.TAB_PANEL_INDEX)) {
			mainPanelIndexChange(evt);
		}
	}

	private void mainPanelIndexChange(PropertyChangeEvent evt) {
		Integer index = (Integer) evt.getNewValue();
		
		
		if (index < 0) {
			model.setPanelIndex(0);
		} else if (index > view.getTabPanel().getTabCount() - 1) {
			model.setPanelIndex(view.getTabPanel().getTabCount() - 1);
		} else {// valid options
			if (index == 0) {
				view.getBtnPrev().setEnabled(false);
			} else {
				view.getBtnPrev().setEnabled(true);
			}

			if (index > view.getTabPanel().getTabCount() - 3) {
				view.getBtnNext().setEnabled(false);
			} else {
				view.getBtnNext().setEnabled(true);
			}

			if (index == 3) {
				view.getBtnNext().setEnabled(false);
				view.getBtnPrev().setEnabled(false);
			}
			view.getTabPanel().setSelectedIndex(model.getPanelIndex());
		}

	}

}
