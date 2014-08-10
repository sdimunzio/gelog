package org.hamradio.lw4hbr.ui.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

import org.hamradio.lw4hbr.ui.model.AbstractModel;

public class AbstractController<V> implements PropertyChangeListener {

	public static String FILE_PATH_PROPERTY = "FILE_PATH_PROPERTY";
	public static String TAB_PANEL_INDEX = "TAB_PANEL_INDEX";
	protected V view;

	protected static ArrayList<AbstractController> controllers = new ArrayList<AbstractController>();
	protected static HashMap<String,AbstractModel> models = new HashMap<String, AbstractModel>();

	private static PropertyChangeListener singlePoint = new PropertyChangeListener() {
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			for (AbstractController c : controllers) {
				c.propertyChange(evt);
			}

		}
	};

	public AbstractController(V view) {
		this.view = view;
	}

	protected static void registerController(AbstractController c) {
		controllers.add(c);
	}

	protected static void registerModel(String key,AbstractModel m) {
		models.put(key,m);
		m.addPropertyChangeListener(singlePoint);
	}
	
	protected static AbstractModel getRegisteredModel(String key) {
		return models.get(key);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

}
