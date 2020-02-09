package com.viridisio.cp4.uiinterface.calculate.cube;

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
		
	}
	public void cubeVarChanged(CubeVar var) {
		
	}
}
