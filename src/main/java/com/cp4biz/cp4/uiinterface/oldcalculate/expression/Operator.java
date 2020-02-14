package com.cp4biz.cp4.uiinterface.oldcalculate.expression;

public abstract class Operator {
	public abstract ExpressionValue run(ExpressionValue leftValue, ExpressionValue rightValue);
	public abstract ExpressionValueType getResultValueType();
	public abstract boolean checkIsServerOperator();
	public abstract boolean checkInputTypes(ExpressionValue leftType, ExpressionValue rightType);
	protected boolean _leftRequired = true;
	public boolean getLeftRequired() {
		return this._leftRequired;
	}
	public abstract String getSign();
}
