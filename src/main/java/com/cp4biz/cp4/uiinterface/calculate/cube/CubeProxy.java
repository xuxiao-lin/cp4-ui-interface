package com.cp4biz.cp4.uiinterface.calculate.cube;

import org.json.JSONObject;

import com.cp4biz.cp4.uiinterface.calculate.cube.expression.ExpressionValue;


//TODO
public abstract class CubeProxy {
	private String _id;
	public String getId() {
		return _id;
	}
	public CubeVar getVar(String key) {
		return null;
	}
	
	public boolean getVarMustRunFromServer(CubeVar var) {
		return false;
	}
	
	public abstract ExpressionValue getReadonlyVarValue(CubeVar var);
	public abstract JSONObject getChangesJsonData();
	
	public abstract void onInputVarChanging(CubeVar var);
	public void inputVarChanged(CubeVar var) {
		
	}
}
