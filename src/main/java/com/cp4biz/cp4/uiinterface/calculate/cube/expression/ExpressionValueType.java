package com.cp4biz.cp4.uiinterface.calculate.cube.expression;

public abstract class ExpressionValueType {
	protected String _key;
	public String getKey() {return this._key;}
	public boolean isType(String key) {
		return _key.equals(key);
	}
	
	public abstract ExpressionValueType getAttrType(String key);
	public abstract boolean checkAttrValueMustGetFromServer();
}
