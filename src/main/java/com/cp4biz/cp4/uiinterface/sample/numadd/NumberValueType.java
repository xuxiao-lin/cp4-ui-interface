package com.cp4biz.cp4.uiinterface.sample.numadd;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;

public class NumberValueType extends DataType {
	public NumberValueType() {
		
	}
	@Override
	public DataType getAttrType(String key) {
		if (key.equals(Attr_precision)) {
			return this;
		}
		return null;
	}

	@Override
	public boolean checkAttrMustGetFromServer(String key) {
		return false;
	}
	public static String Attr_precision = "precision";
	public static String Key;
}
