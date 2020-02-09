package com.viridisio.cp4.uiinterface.calculate.cube;

import org.json.JSONObject;

import com.viridisio.cp4.uiinterface.calculate.cube.expression.ExpressionValue;

import webservice.CubeService;

//TODO
public abstract class CubeProxy {
	public String getId() {
		return null;
	}
	public CubeVar getVar(String key) {
		return null;
	}
	
	public boolean getVarMustRunFromServer(CubeVar var) {
		return false;
	}
	
	public abstract ExpressionValue getReadonlyVarValue(CubeVar var);
	public void commitChanges() {
		CubeService.commitCubeChanges(this, getCommitChangesJsonData());
	}
	protected abstract JSONObject getCommitChangesJsonData();
	public void cancelChanges() {
		CubeService.cancelCubeChanges(this);
	}
	
	public abstract void onInputVarChanging(CubeVar var);
	public void inputVarChanged(CubeVar var) {
		
	}
}
