package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;

public class OperatorRepo {
	private static OperatorRepo _instance;
	public static boolean init(OperatorRepo instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	public static OperatorRepo getInstance() {
		return _instance;
	}
	
	private ArrayList<Operator> _ops = new ArrayList<Operator>();
	public Operator getOp(String sign,DataType leftType,DataType rightType) {
		for (Operator op:_ops) {
			if (op.getSign().equals(sign) && op.getRightType().isType(rightType.getKey())) {
				DataType requiredLeftType = op.getLeftType();
				if (requiredLeftType == null && leftType == null)
					return op;
				if (requiredLeftType.isType(leftType.getKey()))
					return op;
			}
		}
		return null;
	}
	public void registerOp(Operator op) {
		this._ops.add(op);
	}
}
