package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;
import com.cp4biz.cp4.uiinterface.dataflow.IDataInterface;

public class ConstExpression extends Expression {
	private DataValue _value;
	public ConstExpression(IConstValue value) {
		this._value = (DataValue)value;
	}
	@Override
	public boolean checkMustRunFromServer() {
		return false;
	}

	@Override
	public DataValue run() {
		return (DataValue)_value;
	}

	@Override
	public DataType getValueType() {
		return _value.getValueType();
	}

}
