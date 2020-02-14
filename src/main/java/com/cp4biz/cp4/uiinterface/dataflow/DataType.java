package com.cp4biz.cp4.uiinterface.dataflow;

public abstract class DataType {
	protected String _key;
	public String getKey() {return this._key;}
	public boolean isType(String key) {
		return _key.equals(key);
	}
	
	public abstract DataType getAttrType(String key);
	public abstract boolean checkAttrMustGetFromServer(String key);
}
