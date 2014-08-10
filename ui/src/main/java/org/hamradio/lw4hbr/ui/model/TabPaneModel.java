package org.hamradio.lw4hbr.ui.model;

import org.hamradio.lw4hbr.ui.controllers.MainController;

public class TabPaneModel extends AbstractModel {

	public static String MODEL_KEY="TAB_MODEL";
	private Integer panelIndex = 0;

	public Integer getPanelIndex() {
		return panelIndex;
	}

	public void setPanelIndex(Integer panelIndex) {
		firePropertyChange(MainController.TAB_PANEL_INDEX, this.panelIndex,
				this.panelIndex = panelIndex);
	}

}
