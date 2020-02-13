package com.cp4biz.cp4.uiinterface.calculate.cube;

import java.util.EventObject;

import com.cp4biz.cp4.uiinterface.calculate.expression.ExpressionValue;

public class CubeVarEvent extends EventObject {
	public CubeVarEvent(Object source) {
		super(source);
	}

	public EventTypes type;
	public CubeVar sourceVar;
	public ExpressionValue value;
	
	public enum EventTypes {
		changing,changed
	}
}
