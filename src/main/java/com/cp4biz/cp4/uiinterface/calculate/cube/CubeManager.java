package com.cp4biz.cp4.uiinterface.calculate.cube;

import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cp4biz.cp4.uiinterface.calculate.cube.CubeVarEvent.EventTypes;
import com.cp4biz.cp4.uiinterface.calculate.cube.expression.Expression;
import com.cp4biz.cp4.uiinterface.calculate.cube.expression.ExpressionValue;

public class CubeManager {
	protected static CubeManager _instance;
	public static CubeManager getInstance() {
		return _instance;
	}
	public static boolean init(CubeManager instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	private HashSet<CubeProxy> _cubes = new HashSet<CubeProxy>();
	public void refreshAllCubesAfterSourceCubeVarChanged(CubeVar sourceVar) {
		boolean b = this.checkCubeVarRefreshMustRunOnServer(sourceVar);
		if (b) {
			//run on server
		}
		else {
			HashSet<CubeVar> vars = this.getMyAffectedCubeVars(sourceVar);
			this.updateFrontendRunVarsAfterSourceVarChanged(vars);
		}
	}
	private void updateFrontendRunVarsAfterSourceVarChanged(HashSet<CubeVar> vars) {
		if (vars.size() == 0)
			return;
		for (CubeVar var: vars) {
			var.updateValue(var.run(), EventTypes.changed);
			this.updateFrontendRunVarsAfterSourceVarChanged(this.getMyAffectedCubeVars(var));
		}		
	}
	
	public HashSet<CubeVar> getAllAffectedCubeVars(CubeVar sourceVar) {
		return new HashSet<CubeVar>();
	}
	public HashSet<CubeVar> getMyAffectedCubeVars(CubeVar sourceVar){
		return new HashSet<CubeVar>();
	}
	public boolean checkCubeVarRefreshMustRunOnServer(CubeVar sourceVar) {
		return false;
	}
	
	public JSONArray getAllCubesChangesData() {
		JSONArray jsa = new JSONArray();
		for (CubeProxy cube : this._cubes) {
			JSONObject jso = cube.getChangesJsonData();
			if (jso != null)
				jsa.put(jso);
		}
		return jsa;
	}
	public CubeProxy iniNewCubeFromJson(JSONObject jso) {
		String sType = jso.getString("type");
		CubeType type = CubeTypeRepo.getInstance().getCubeType(sType);
		if (type == null) {
			//throw no such type exception
		}
		CubeProxy cube = type.getNewCube(jso);
		return cube;
	}
}
