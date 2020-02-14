package com.cp4biz.cp4.uiinterface.oldcalculate.expression;

import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.oldcalculate.cube.CubeVar;

public class GetVarValueExpression extends Expression {
	private CubeVar _targetVar;
	public GetVarValueExpression(CubeVar var) {
		this._targetVar = var;
	}
	@Override
	public boolean checkMustRunFromServer() {
		return this._targetVar.checkMustRunFromServer();
	}

	@Override
	public ExpressionValue frontendRun() {
		return this._targetVar.frontendRun();
	}

	@Override
	public ExpressionValueType getValueType() {
		return this._targetVar.getValueType();
	}

	@Override
	public HashSet<CubeVar> getInputVars() {
		HashSet<CubeVar> inputs = new HashSet<CubeVar>();
		inputs.add(_targetVar);
		return inputs;
	}

}
