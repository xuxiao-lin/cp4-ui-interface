package com.cp4biz.cp4.uiinterface.oldcalculate.expression;

import java.util.ArrayList;
import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.oldcalculate.cube.CubeVar;

public abstract class Expression {
	public abstract boolean checkMustRunFromServer();
	public abstract ExpressionValue frontendRun();
	public abstract ExpressionValueType getValueType();
	public abstract HashSet<CubeVar> getInputVars();
}
