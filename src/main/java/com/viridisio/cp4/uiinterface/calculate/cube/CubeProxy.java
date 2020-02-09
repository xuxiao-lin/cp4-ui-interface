package com.viridisio.cp4.uiinterface.calculate.cube;

import com.viridisio.cp4.uiinterface.calculate.cube.expression.ExpressionValue;

//TODO
public abstract class CubeProxy {
	public CubeVar getVar(String key) {
		return null;
	}
	
	public boolean getVarMustRunFromServer(CubeVar var) {
		return false;
	}
	
	public abstract ExpressionValue getReadonlyVarValue(CubeVar var);
	public abstract void commitChanges();
	public abstract void cancelChanges();
}
