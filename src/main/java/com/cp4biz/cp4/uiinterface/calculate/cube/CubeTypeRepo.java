package com.cp4biz.cp4.uiinterface.calculate.cube;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.json.JSONObject;


public class CubeTypeRepo {
	protected static CubeTypeRepo _instance;
	public static CubeTypeRepo getInstance() {
		return _instance;
	}
	public static boolean init(CubeTypeRepo instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	private HashMap<String, CubeType> _cubeTypes = new HashMap<String, CubeType>();
	public CubeType getCubeType(String key) {
		return this._cubeTypes.get(key);
	}
	public void registerCubeType(CubeType cubeType) {
		if (_cubeTypes.containsKey(cubeType.getKey())){
			//throw exception
		}
		this._cubeTypes.put(cubeType.getKey(), cubeType);
	}
	public CubeType iniCubeTypeFromJson(JSONObject jso,ClassLoader loader) {
		try {
			Class<?> cls = Class.forName(jso.getString("class"),true,loader);
			CubeType type = (CubeType)(cls.newInstance());
			Method method = cls.getMethod("ini", JSONObject.class);
			method.invoke(type, jso);			
			return type;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
