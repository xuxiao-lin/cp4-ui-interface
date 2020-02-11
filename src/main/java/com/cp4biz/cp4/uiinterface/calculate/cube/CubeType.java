package com.cp4biz.cp4.uiinterface.calculate.cube;

import java.lang.reflect.Method;

import org.json.JSONObject;

public abstract class CubeType {
	public void ini(JSONObject jso) {
		this._key = jso.getString("key");
		this._cubeClass = jso.getString("cubeClass");
		JSONObject customData = jso.getJSONObject("customData");
		if (customData != null)
			this.iniCustomData(customData);
	}
	//virtual
	protected void iniCustomData(JSONObject customData) {}
	
	private String _key;
	public String getKey() {
		return _key;
	}
	private String _cubeClass;
	public CubeProxy getNewCube(JSONObject jso) {
		ClassLoader loader = this.getClass().getClassLoader();
		try {
			Class<?> cls = Class.forName(_cubeClass,true,loader);
			CubeProxy cube = (CubeProxy)(cls.newInstance());
			Method method = cls.getMethod("ini", this.getClass(), JSONObject.class);
			method.invoke(cube, this, jso);						
			return cube;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
