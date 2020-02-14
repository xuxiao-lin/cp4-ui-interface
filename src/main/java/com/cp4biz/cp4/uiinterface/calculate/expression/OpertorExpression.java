package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;
import java.util.HashSet;
import com.cp4biz.cp4.uiinterface.dataflow.*;

public class OpertorExpression extends Expression {
	private Operator _operator;
	
	private IDataInterface _leftData;
	private IDataInterface _rightData;
	@Override
	public boolean checkMustRunFromServer() {
		return _operator.checkMustRunFromServer();
	}
	@Override
	public DataValue run() {
		DataValue leftValue = this._leftData==null?null:this._leftData.getValue();
		DataValue rightValue = this._rightData.getValue();
		return this._operator.run(leftValue, rightValue);
	}
	@Override
	public DataType getValueType() {
		return this._operator.getResultType();
	}
	@Override
	public ArrayList<IDataInterface> getInputs() {
		ArrayList<IDataInterface> inputs = new ArrayList<IDataInterface>();
		if (this._leftData != null)
			inputs.add(this._leftData);
		if (this._rightData != null)
			inputs.add(this._rightData);
		return inputs;
	}

}
