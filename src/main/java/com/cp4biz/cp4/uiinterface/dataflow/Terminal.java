package com.cp4biz.cp4.uiinterface.dataflow;

public class Terminal {
	private String _key;
	private DataType _dataType;
	private DataValue _value;
	public DataValue getValue() {
		return this._value;
	}
	public void setValue(DataValue value) {
		this._value = value;
		//发送事件
	}
	
}
