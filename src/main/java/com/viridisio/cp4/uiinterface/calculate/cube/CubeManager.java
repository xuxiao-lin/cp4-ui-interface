package com.viridisio.cp4.uiinterface.calculate.cube;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.viridisio.cp4.uiinterface.calculate.cube.expression.Expression;
import com.viridisio.cp4.uiinterface.calculate.cube.expression.ExpressionValue;

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
	public void onCubeVarChanging(CubeVar var) {
		HashSet<CubeVar> affectedVars = this.getAffectedCubeVars(var,true);
		HashSet<CubeProxy> cubes = new HashSet<CubeProxy>();
		for (CubeVar affected : affectedVars) {
			cubes.add(affected.getCubeProxy());
		}
		for (CubeProxy cube : cubes) {
			cube.onInputVarChanging(var);
		}
	}
	
	public ExpressionValue runCubeVarOnFrontend(CubeVar var) {
		var.run();
		HashSet<CubeVar> affectedVars = this.getAffectedCubeVars(var,false);
		HashSet<CubeProxy> cubes = new HashSet<CubeProxy>();
		for (CubeVar affected : affectedVars) {
			cubes.add(affected.getCubeProxy());
		}
		for (CubeProxy cube : cubes) {
			cube.inputVarChanged(var);
		}
		return var.getValue();
	}
	
	public HashSet<CubeVar> getAffectedCubeVars(CubeVar sourceVar,boolean frontendOnly) {
		return new HashSet<CubeVar>();
	}
	private boolean _lastAffectedSearchMustRunOnServer = false;
	public boolean getLastAffectedSearchMustRunOnServer() {
		return this._lastAffectedSearchMustRunOnServer;
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
	
	public CubeProxy createCubeProxy() {
		return null;
	}
}
