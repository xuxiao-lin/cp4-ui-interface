package com.cp4biz.cp4.uiinterface.calculate.expression;

public abstract class ExpressionValue {
	public abstract ExpressionValueType getValueType();
	public abstract ExpressionValue getAttrValue(String key);
	public void ini(ExpressionValueType valueType) {
		this._valueType = valueType;
	}
	private ExpressionValueType _valueType;
}
