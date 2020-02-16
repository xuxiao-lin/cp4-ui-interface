package com.cp4biz.cp4.uiinterface.oldcalculate.cube;

import java.util.ArrayList;
import java.util.HashSet;

import org.json.JSONObject;

import com.cp4biz.cp4.uiinterface.client.usercommand.UserCommand;
import com.cp4biz.cp4.uiinterface.oldcalculate.expression.Expression;
import com.cp4biz.cp4.uiinterface.oldcalculate.expression.ExpressionValue;

public class ChangeCubeVarCommand extends UserCommand {
	public CubeVar sourceVar;
	public Expression oldExpression;
	public Expression newExpression;
	public ArrayList<CubeVar> changingVars = new ArrayList<CubeVar>();
	public ArrayList<ExpressionValue> oldValues;
	public ArrayList<ExpressionValue> newValues;

	@Override
	public void undo() {
//		sourceVar.setExpression(oldExpression);
//		for (int i=0;i<changingVars.size();i++) {
//			CubeVar var = changingVars.get(i);
//			var.setValue(oldValues.get(i));
//		}
	}

	@Override
	public void redo() {
//		sourceVar.setExpression(newExpression);
//		for (int i=0;i<changingVars.size();i++) {
//			CubeVar var = changingVars.get(i);
//			var.setValue(newValues.get(i));
//		}
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return "change var";
	}

	@Override
	protected void keepOldValues() {
		this.oldExpression = this.sourceVar.getExpression();
		HashSet<CubeVar> affectedVars = CubeManager.getInstance().getAllAffectedCubeVars(sourceVar);
		this.changingVars.add(this.sourceVar);
		this.changingVars.addAll(affectedVars);
		for (CubeVar var : this.changingVars) {
			this.oldValues.add(var.getValue());
		}
		this._mustRunOnServer = CubeManager.getInstance().checkCubeVarRefreshMustRunOnServer(sourceVar);
	}
	private boolean _mustRunOnServer = false;
	public boolean mustRunOnServer() {
		return this._mustRunOnServer;
	}

	@Override
	public JSONObject sendDataToRunOnServer() {
		JSONObject jso = new JSONObject();
		jso.put("sourceCube", this.sourceVar.getCubeProxy().getId());
		jso.put("sourceVar", this.sourceVar.getKey());
		return jso;
	}

	@Override
	protected void afterRunOnServer(JSONObject response) {
		// TODO Auto-generated method stub
		//更新var
		//记录新的值到command
		for (int i=0;i<changingVars.size();i++) {
			this.newValues.add(changingVars.get(i).getValue());
		}
	}

	@Override
	public String getKey() {
		return Key;
	}

	@Override
	protected void runOnFrontend() {
	}
	public static String Key = "ChangeCubeVar";
}
