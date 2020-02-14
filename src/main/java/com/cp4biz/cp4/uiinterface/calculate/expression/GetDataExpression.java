package com.cp4biz.cp4.uiinterface.calculate.expression;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;
import com.cp4biz.cp4.uiinterface.dataflow.IDataInterface;

public class GetDataExpression extends Expression {
	private IDataInterface _dataInterface;
	public IDataInterface getDataInterface() {return this._dataInterface;}
	public void setDataInterface(IDataInterface value) {this._dataInterface = value;}
	@Override
	public boolean checkMustRunFromServer() {
		return false;
	}

	@Override
	public DataValue run() {
		return this._dataInterface.getValue();
	}

	@Override
	public DataType getValueType() {
		return this._dataInterface.getValueType();
	}

}
