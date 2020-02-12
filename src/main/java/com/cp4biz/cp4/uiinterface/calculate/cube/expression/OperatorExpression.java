package com.cp4biz.cp4.uiinterface.calculate.cube.expression;

import java.util.ArrayList;
import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.calculate.cube.CubeVar;

public class OperatorExpression extends Expression {
	private Expression _leftExpression;
	private Expression _rightExpression;
	private Operator _operator;
	@Override
	public boolean checkMustRunFromServer() {
		if (_operator.checkIsServerOperator())
			return true;
		if (_leftExpression != null && _leftExpression.checkMustRunFromServer())
			return true;
		if (_rightExpression != null && _rightExpression.checkMustRunFromServer())
			return true;
		return false;
	}

	@Override
	public ExpressionValue frontendRun() {
		ExpressionValue leftValue = this._leftExpression == null? null:_leftExpression.frontendRun();
		ExpressionValue rightValue = this._rightExpression.frontendRun();
		return this._operator.run(leftValue, rightValue);
	}

	@Override
	public ExpressionValueType getValueType() {
		return this._operator==null?null:this._operator.getResultValueType();
	}

	@Override
	public HashSet<CubeVar> getInputVars() {
		HashSet<CubeVar> inputs = new HashSet<CubeVar>();
		if (_leftExpression != null)
			inputs.addAll(_leftExpression.getInputVars());
		if (_rightExpression != null)
			inputs.addAll(_rightExpression.getInputVars());
		return inputs;
	}

}
