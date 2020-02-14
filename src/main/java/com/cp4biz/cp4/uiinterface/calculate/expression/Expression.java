package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.*;

public abstract class Expression {
	public abstract boolean checkMustRunFromServer();
	public abstract DataValue run();
	public abstract DataType getValueType();
}
