package com.cp4biz.cp4.uiinterface.calculate.cube.expression;

public abstract class Expression {
	public abstract boolean checkMustRunFromServer();
	public abstract ExpressionValue run();
}
