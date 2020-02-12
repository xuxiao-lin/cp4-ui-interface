package com.cp4biz.cp4.uiinterface.calculate.cube;

import java.util.ArrayList;
import java.util.HashSet;

import org.json.JSONObject;

import com.cp4biz.cp4.uiinterface.calculate.cube.expression.Expression;
import com.cp4biz.cp4.uiinterface.calculate.cube.expression.ExpressionValue;


//TODO
public abstract class CubeProxy {
	public void ini(CubeType type, JSONObject jso) {
		this._type = type;
		this._id = jso.getString("id");
		JSONObject customData = jso.getJSONObject("customData");
		if (customData != null)
			this.iniCustomData(customData);
	}
	//virtual
	protected void iniCustomData(JSONObject customData) {}
	
	private String _id;
	public String getId() {
		return _id;
	}
	private CubeType _type;
	public CubeType getType() {
		return this._type;
	}
	
	private String _name;
	public void setName(String value) {
		this._name = value;
	}
	public String getName() {
		return this._name;
	}
	
	private ArrayList<CubeVar> _vars = new ArrayList<CubeVar>();
	
	public CubeVar getVar(String key) {
		CubeVar var = null;
		for (CubeVar childVar : this._vars) {
			if (childVar.getKey().equals(key)) {
				var = childVar;
				break;
			}
		}
		return var;
	}
	public void addVar(CubeVar var) {
		if (this.getVar(var.getKey()) == null) {
			var.iniCubeProxy(this);
			this._vars.add(var);
		}
	}
	
	public ExpressionValue getVarValue(String key) {
		CubeVar var = this.getVar(key);
		if (var == null)
			return null;
		return var.getValue();
	}
	public abstract boolean checkReadonlyVarMustRunFromServer(CubeVar var);
	public abstract ExpressionValue updateReadonlyVarValue(CubeVar var);
	
	public abstract JSONObject getChangesJsonData();
}
