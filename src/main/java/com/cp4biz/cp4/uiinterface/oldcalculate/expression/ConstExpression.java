package com.cp4biz.cp4.uiinterface.oldcalculate.expression;

import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.oldcalculate.cube.CubeVar;

public class ConstExpression extends Expression {
	private ExpressionValue _value;
	
	public ConstExpression(ExpressionValue value) {
		this._value = value;
	}
	@Override
	public boolean checkMustRunFromServer() {
		return false;
	}

	@Override
	public ExpressionValue frontendRun() {
		return this._value;
	}

	@Override
	public ExpressionValueType getValueType() {
		return this._value.getValueType();
	}

	@Override
	public HashSet<CubeVar> getInputVars() {
		return null;
	}

}
