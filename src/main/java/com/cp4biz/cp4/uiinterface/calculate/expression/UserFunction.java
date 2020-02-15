package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;

public abstract class UserFunction {
	public abstract String getKey();
	public abstract DataValue run(ArrayList<DataValue> inputs);
	public abstract ArrayList<UserFunctionPara> getParas();
	public abstract DataType getResultType();
	public abstract boolean checkMustRunFromServer();
}
