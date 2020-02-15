package com.cp4biz.cp4.uiinterface.sample.numadd;

import java.text.NumberFormat;

import com.cp4biz.cp4.uiinterface.calculate.expression.IConstValue;
import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataTypeRepo;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;

public class ConstNumber extends DataValue implements IConstValue {
	private double _value;
	public ConstNumber(double value) {
		this._value = value;
	}
	public double getValue() {
		return this._value;
	}
	public DataType getValueType() {
		return DataTypeRepo.getInstance().getType(NumberValueType.Key);
	}
	private int _precision = -1;
	public void setPrecision(int precision) {this._precision = precision;}
	public int getPrecision() {return this._precision;}
	@Override
	public DataValue getAttrValue(String key) {
		if (key.equals(NumberValueType.Attr_precision)){
			ConstNumber cn = new ConstNumber(this._precision);
			cn.setPrecision(0);
			return cn;
		}
		return null;
	}
	@Override
	public String toString() {
		if (_precision == -1)
			return String.valueOf(_value);
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(_precision);
		return nf.format(_value);
	}
}
