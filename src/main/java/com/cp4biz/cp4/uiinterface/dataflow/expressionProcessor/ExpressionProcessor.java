package com.cp4biz.cp4.uiinterface.dataflow.expressionProcessor;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.calculate.expression.Expression;
import com.cp4biz.cp4.uiinterface.dataflow.*;

public abstract class ExpressionProcessor extends DataProcessor {
	private Expression _expression;
	public void setExpression(Expression expression) {
		this._expression = expression;
		_outputTerminal.setValueType(_expression == null ? null :_expression.getValueType());
	}
	public Expression getExpression() {
		return this._expression;
	}
	@Override
	public void run() {
		this._outputTerminal.setValue(_expression==null?null:_expression.run());
	}
	private Terminal _outputTerminal = new Terminal();

	@Override
	public boolean checkRunFromServer() {
		if (_expression == null)
			return false;
		return this._expression.checkMustRunFromServer();
	}

}
