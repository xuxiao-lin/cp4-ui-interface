package com.cp4biz.cp4.uiinterface.calculate.cube.expression;

import java.util.ArrayList;

public abstract class UserFunction {
	public abstract String getName();
	public abstract ExpressionValueType getResultType();
	protected ArrayList<ExpressionValueType> _inputTypes = new ArrayList<ExpressionValueType>();
	protected ArrayList<String> _inputNames = new ArrayList<String>();

	public abstract boolean checkIsServerFunction();
	public abstract ExpressionValue run(ArrayList<ExpressionValue> inputs);
	public boolean checkRun(ArrayList<ExpressionValue> inputs) {
		if (_inputTypes.size() != inputs.size())
			return false;
		for (int i=0;i<_inputTypes.size();i++) {
			ExpressionValue input = inputs.get(i);
			if (input.getValueType().isType(_inputTypes.get(i).getKey()))
				return false;
		}
		return true;
	}

}
