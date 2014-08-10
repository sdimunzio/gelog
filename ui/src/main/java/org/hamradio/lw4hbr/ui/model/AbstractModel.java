package org.hamradio.lw4hbr.ui.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class AbstractModel {
	private transient final List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.remove(listener);
	}

	protected void firePropertyChange(String property, Object oldValue,
			Object newValue) {

		if (oldValue == newValue || oldValue != null
				&& oldValue.equals(newValue)) {
			return;
		}

		PropertyChangeEvent evt = new PropertyChangeEvent(this, property,
				oldValue, newValue);
		for (PropertyChangeListener listener : new ArrayList<PropertyChangeListener>(
				listeners)) {
			listener.propertyChange(evt);
		}
	}
}
