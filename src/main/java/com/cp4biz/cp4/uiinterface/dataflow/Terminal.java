package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.DataInterfaceEvent.EventType;

public class Terminal implements IDataInterface {
	private String _key;
	private DataType _dataType;
	private DataValue _value;
	public DataValue getValue() {
		return this._value;
	}
	public void setValue(DataValue value) {
		this._value = value;
		//发送事件
		DataInterfaceEvent event = new DataInterfaceEvent(this);
		event.eventType = EventType.valueChanged;
		DataInterfaceEvent.fireEvent(this, event);
	}
	public DataType getValueType() {
		return this._dataType;
	}
	public void setValueType(DataType type) {
		this._dataType = type;
	}
	public String getKey() {
		return this._key;
	}
	public void setKey(String key) {
		this._key = key;
	}
}
