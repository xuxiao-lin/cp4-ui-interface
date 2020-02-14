package com.cp4biz.cp4.uiinterface.oldcalculate.expression;

import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.oldcalculate.cube.CubeVar;

public class GetAttrExprssion extends Expression {
	private Expression _targetExpression;
	private String _attrName;
	@Override
	public boolean checkMustRunFromServer() {
		if (this._targetExpression.checkMustRunFromServer())
			return true;
		if (this._targetExpression.getValueType().checkAttrValueMustGetFromServer())
			return true;
		return false;
	}

	@Override
	public ExpressionValue frontendRun() {
		return this._targetExpression.frontendRun().getAttrValue(this._attrName);
	}

	@Override
	public ExpressionValueType getValueType() {
		return this._targetExpression.getValueType().getAttrType(_attrName);
	}

	@Override
	public HashSet<CubeVar> getInputVars() {
		return this._targetExpression.getInputVars();
	}

}
