package com.cp4biz.cp4.uiinterface.sample.numadd;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.calculate.expression.UserFunction;
import com.cp4biz.cp4.uiinterface.calculate.expression.UserFunctionPara;
import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataTypeRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;

public class MultiplyFunc extends UserFunction {
	@Override
	public String getKey() {
		return Key;
	}
	public MultiplyFunc() {
		UserFunctionPara p1 = new UserFunctionPara();
		p1.key ="first";
		p1.dataType = DataTypeRepo.getInstance().getType(NumberValueType.Key);
		this._inputParas.add(p1);
		UserFunctionPara p2 = new UserFunctionPara();
		p2.key ="second";
		p2.dataType = DataTypeRepo.getInstance().getType(NumberValueType.Key);
		this._inputParas.add(p2);
	}
	@Override
	public DataValue run(ArrayList<DataValue> inputs) {
		ConstNumber n1 = (ConstNumber)inputs.get(0);
		ConstNumber n2 = (ConstNumber)inputs.get(1);
		return new ConstNumber(n1.getValue()*n2.getValue());
	}

	private ArrayList<UserFunctionPara> _inputParas = new ArrayList<UserFunctionPara>();
	@Override
	public ArrayList<UserFunctionPara> getParas() {
		return _inputParas;
	}

	@Override
	public DataType getResultType() {
		return DataTypeRepo.getInstance().getType(NumberValueType.Key);
	}

	@Override
	public boolean checkMustRunFromServer() {
		return false;
	}
	public static String Key = "multiply";
}
