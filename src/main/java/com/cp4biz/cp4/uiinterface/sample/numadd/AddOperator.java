package com.cp4biz.cp4.uiinterface.sample.numadd;

import com.cp4biz.cp4.uiinterface.calculate.expression.Operator;
import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataTypeRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;

public class AddOperator extends Operator {


	@Override
	public DataValue run(DataValue leftValue, DataValue rightValue) {
		double left = ((ConstNumber)leftValue).getValue();
		double right = ((ConstNumber)rightValue).getValue();
		return new ConstNumber(left+right);
	}

	@Override
	public DataType getResultType() {
		return DataTypeRepo.getInstance().getType(NumberValueType.Key);
	}

	@Override
	public boolean checkMustRunFromServer() {
		return false;
	}

	@Override
	public String getSign() {
		return "+";
	}

	@Override
	public DataType getLeftType() {
		return DataTypeRepo.getInstance().getType(NumberValueType.Key);
	}

	@Override
	public DataType getRightType() {
		return DataTypeRepo.getInstance().getType(NumberValueType.Key);
	}

}
