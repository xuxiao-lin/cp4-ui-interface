package com.cp4biz.cp4.uiinterface.dataflow.expressionProcessor;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.calculate.expression.Expression;
import com.cp4biz.cp4.uiinterface.dataflow.*;

public class ExpressionProcessor extends DataProcessor {
	public ExpressionProcessor() {
		this._outputTerminals.put(OutputKey, this._outputTerminal);
	}
	private Expression _expression;
	public void setExpression(Expression expression) {
		this._expression = expression;
		_outputTerminal.setValueType(_expression == null ? null :_expression.getValueType());
	}
	public Expression getExpression() {
		return this._expression;
	}
	public void setInputTerminals(ArrayList<Terminal> inputs) {
		for (Terminal input:_inputTerminals.values()) {
			DataInterfaceEvent.removeListener(input, this);
		}
		this._inputTerminals.clear();
		for (Terminal input : inputs) {
			this._inputTerminals.put(input.getKey(), input);
			DataInterfaceEvent.addListener(input, this);
		}
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
	public static String OutputKey = "result";
	@Override
	public void onDataInterfaceValueChanged(DataInterfaceEvent event) {
		if (this.getSwitchOn())
			this.run();
	}
}
