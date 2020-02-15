package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.HashMap;

public class DataTypeRepo {
	private static DataTypeRepo _instance;
	public static boolean init(DataTypeRepo instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	public static DataTypeRepo getInstance() {
		return _instance;
	}
	
	private HashMap<String, DataType> _types = new HashMap<String, DataType>();
	public DataType getType(String key) {
		return this._types.get(key);
	}
	public void registerType(DataType type) {
		this._types.put(type.getKey(), type);
	}
}
