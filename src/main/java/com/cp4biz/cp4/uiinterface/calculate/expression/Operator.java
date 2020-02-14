package com.cp4biz.cp4.uiinterface.calculate.expression;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;
import com.cp4biz.cp4.uiinterface.dataflow.IDataInterface;

public abstract class Operator {
	public abstract String getSign();
	public abstract DataValue run(DataValue leftValue, DataValue rightValue);
	public abstract DataType getResultType();
	public abstract boolean checkMustRunFromServer();
	public abstract boolean needLeftValue();
}
