package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;

public class DataInterfaceEvent extends EventObject {
	public DataInterfaceEvent(Object arg0) {
		super(arg0);
	}

	public IDataInterface source;
	public EventType eventType;
	public enum EventType {valueChanged};
	
	private static HashMap<IDataInterface, HashSet<IDataInterfaceEventListener>> _eventListeners = new HashMap<IDataInterface, HashSet<IDataInterfaceEventListener>>();
	public static void addListener(IDataInterface source, IDataInterfaceEventListener target) {
		HashSet<IDataInterfaceEventListener> listeners = _eventListeners.get(source);
		if (listeners == null) {
			listeners = new HashSet<IDataInterfaceEventListener>();
			_eventListeners.put(source, listeners);
		}
		listeners.add(target);
	}
	public static void removeListener(IDataInterface source, IDataInterfaceEventListener target) {
		HashSet<IDataInterfaceEventListener> listeners = _eventListeners.get(source);
		if (listeners != null)
			listeners.remove(target);
	}
	public static void fireEvent(IDataInterface source, DataInterfaceEvent event) {
		HashSet<IDataInterfaceEventListener> listeners = _eventListeners.get(source);
		if (listeners != null) {
			event.source = source;
			for (IDataInterfaceEventListener listener:listeners) {
				listener.onDataInterfaceValueChanged(event);
			}
		}
	}
}
