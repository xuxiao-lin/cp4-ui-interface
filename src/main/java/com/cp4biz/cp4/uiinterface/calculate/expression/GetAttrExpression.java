package com.cp4biz.cp4.uiinterface.calculate.expression;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;

public class GetAttrExpression extends Expression {
	private Expression _sourceExpression;
	private String _attrKey;
	@Override
	public boolean checkMustRunFromServer() {
		return this._sourceExpression.getValueType().checkAttrMustGetFromServer(_attrKey);
	}

	@Override
	public DataValue run() {
		return this._sourceExpression.run().getAttrValue(_attrKey);
	}

	@Override
	public DataType getValueType() {
		return this._sourceExpression.getValueType().getAttrType(_attrKey);
	}
}
