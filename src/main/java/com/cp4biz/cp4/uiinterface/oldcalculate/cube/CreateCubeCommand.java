package com.cp4biz.cp4.uiinterface.oldcalculate.cube;

import org.json.JSONObject;

import com.cp4biz.cp4.uiinterface.client.usercommand.UserCommand;

public class CreateCubeCommand extends UserCommand {
	public static String Key = "CreateCube";
	@Override
	public String getKey() {
		return Key;
	}
	@Override
	protected void keepOldValues() {}

	@Override
	protected void runOnFrontend() {}

	@Override
	public boolean mustRunOnServer() {
		return true;
	}

	@Override
	public JSONObject sendDataToRunOnServer() {
		return null;
	}

	@Override
	protected void afterRunOnServer(JSONObject response) {
		/*
		 * {id,type}
		 */

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

}
