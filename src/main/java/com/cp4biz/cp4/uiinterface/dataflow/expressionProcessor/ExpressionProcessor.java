package com.cp4biz.cp4.uiinterface.dataflow.expressionProcessor;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.calculate.expression.Expression;
import com.cp4biz.cp4.uiinterface.dataflow.*;

public class ExpressionProcessor extends DataProcessor {
	public ExpressionProcessor() {
		_outputTerminal.setKey(OutputKey);
		this.addOutputTerminal(_outputTerminal);
	}
	private Expression _expression;
	public void setExpression(Expression expression) {
		this._expression = expression;
		_outputTerminal.setValueType(_expression == null ? null :_expression.getValueType());
	}
	public Expression getExpression() {
		return this._expression;
	}
	public void setInputTerminals(ArrayList<InputTerminal> inputs) {
		this.removeAllInputTerminals();
		for (InputTerminal input : inputs) {
			this.addInputTerminal(input);
		}
	}
	
	@Override
	public void run() {
		this._outputTerminal.setValue(_expression==null?null:_expression.run());
	}
	private OutputTerminal _outputTerminal = new OutputTerminal();

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
