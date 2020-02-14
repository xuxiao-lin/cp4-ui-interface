package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;
import java.util.HashSet;
import com.cp4biz.cp4.uiinterface.dataflow.*;

public class OperatorExpression extends Expression {
	private Operator _operator;
	
	private Expression _leftExpression;
	private Expression _rightExpression;
	
	public void setOperator(Operator operator) {this._operator = operator;}
	public Operator getOperator() {return this._operator;}
	
	public void setLeftExpression(Expression leftExpression) {this._leftExpression = leftExpression;}
	public Expression getLeftExpression() {return this._leftExpression;}
	
	public void setRightExpression(Expression rightExpression) {this._rightExpression = rightExpression;}
	public Expression getRightExpression() {return this._rightExpression;}
	
	@Override
	public boolean checkMustRunFromServer() {
		return _operator.checkMustRunFromServer();
	}
	@Override
	public DataValue run() {
		DataValue leftValue = this._leftExpression==null?null:this._leftExpression.run();
		DataValue rightValue = this._rightExpression.run();
		return this._operator.run(leftValue, rightValue);
	}
	@Override
	public DataType getValueType() {
		return this._operator.getResultType();
	}
}
