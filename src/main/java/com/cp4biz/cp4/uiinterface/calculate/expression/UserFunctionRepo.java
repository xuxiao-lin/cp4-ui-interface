package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;

public class UserFunctionRepo {
	private static UserFunctionRepo _instance;
	public static boolean init(UserFunctionRepo instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	public static UserFunctionRepo getInstance() {
		return _instance;
	}
	
	private HashMap<String,UserFunction> _funcs = new HashMap<String,UserFunction>();
	public UserFunction getFunc(String key) {
		return _funcs.get(key);
	}
	public void registerFunc(UserFunction func) {
		this._funcs.put(func.getKey(),func);
	}
}
