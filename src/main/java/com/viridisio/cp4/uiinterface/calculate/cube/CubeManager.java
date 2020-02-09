package com.viridisio.cp4.uiinterface.calculate.cube;

import java.util.HashSet;

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
	public void onCubeVarChanging(CubeVar var) {
		HashSet<CubeVar> affectedVars = this.getAffectedCubeVars(var);
		HashSet<CubeProxy> cubes = new HashSet<CubeProxy>();
		for (CubeVar affected : affectedVars) {
			cubes.add(affected.getCubeProxy());
		}
		for (CubeProxy cube : cubes) {
			cube.onInputVarChanging(var);
		}
	}
	public void cubeVarChanged(CubeVar var) {
		HashSet<CubeVar> affectedVars = this.getAffectedCubeVars(var);
		HashSet<CubeProxy> cubes = new HashSet<CubeProxy>();
		for (CubeVar affected : affectedVars) {
			cubes.add(affected.getCubeProxy());
		}
		for (CubeProxy cube : cubes) {
			cube.inputVarChanged(var);
		}
	}
	
	public HashSet<CubeVar> getAffectedCubeVars(CubeVar sourceVar) {
		return new HashSet<CubeVar>();
	}
	
	public CubeProxy createCubeProxy() {
		return null;
	}
}
