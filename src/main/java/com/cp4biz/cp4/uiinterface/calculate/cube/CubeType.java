package com.cp4biz.cp4.uiinterface.calculate.cube;

import org.json.JSONObject;

public abstract class CubeType {
	public void ini(JSONObject jso) {
		this._key = jso.getString("key");
		JSONObject customData = jso.getJSONObject("customData");
		if (customData != null)
			this.iniCustomData(customData);
	}
	//virtual
	protected void iniCustomData(JSONObject customData) {}
	
	private String _key;
	public String getKey() {
		return null;
	}
}
