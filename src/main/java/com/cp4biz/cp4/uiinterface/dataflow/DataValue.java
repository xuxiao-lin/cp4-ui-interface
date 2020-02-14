package com.cp4biz.cp4.uiinterface.dataflow;

public abstract class DataValue {
	public abstract DataType getValueType();
	public abstract DataValue getAttrValue(String key);
}
