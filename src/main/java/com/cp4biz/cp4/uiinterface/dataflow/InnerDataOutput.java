package com.cp4biz.cp4.uiinterface.dataflow;

public class InnerDataOutput implements IDataInterface {
	private String _key;
	private DataType _dataType;
	private DataValue _value;
	public String getKey() {
		return this._key;
	}
	public DataValue getValue() {
		return this._value;
	}
	public void setValue(DataValue value) {
		this._value = value;
	}
	public DataType getValueType() {
		return this._dataType;
	}

}
