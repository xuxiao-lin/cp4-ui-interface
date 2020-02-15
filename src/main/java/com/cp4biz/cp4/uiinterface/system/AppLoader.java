package com.cp4biz.cp4.uiinterface.system;

import com.cp4biz.cp4.uiinterface.calculate.expression.Operator;
import com.cp4biz.cp4.uiinterface.calculate.expression.OperatorRepo;
import com.cp4biz.cp4.uiinterface.calculate.expression.UserFunctionRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataTypeRepo;
import com.cp4biz.cp4.uiinterface.sample.numadd.AddOperator;
import com.cp4biz.cp4.uiinterface.sample.numadd.MultiplyFunc;
import com.cp4biz.cp4.uiinterface.sample.numadd.NumberValueType;

public class AppLoader {
	public static void ini() {
		DataTypeRepo.init(new DataTypeRepo());
		DataTypeRepo dataTypeRepo = DataTypeRepo.getInstance();
		dataTypeRepo.registerType(new NumberValueType());
		
		OperatorRepo.init(new OperatorRepo());
		OperatorRepo opRepo = OperatorRepo.getInstance();
		opRepo.registerOp(new AddOperator());
		
		UserFunctionRepo.init(new UserFunctionRepo());
		UserFunctionRepo funcRepo = UserFunctionRepo.getInstance();
		funcRepo.registerFunc(new MultiplyFunc());
	}
}
