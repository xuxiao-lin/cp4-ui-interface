package com.cp4biz.cp4.uiinterface.usercommand;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

import org.json.JSONObject;

//run:start,callapi,end
//undo:
public abstract class UserCommand {
	private CommandStatus _status;
	public enum CommandStatus{
		start,end
	}
	public CommandStatus getStatus() {
		return this._status;
	}
	public void setStatus(CommandStatus status) {
		this._status = status;
	}
	public abstract String getKey();
	public void startCommand() {
		this._status = CommandStatus.start;
		this.keepOldValues();
	}
	protected abstract void keepOldValues();
	public void startRun() {
		this.runOnFrontend();
	}
	protected abstract void runOnFrontend();
	public abstract boolean mustRunOnServer();
	public abstract JSONObject sendDataToRunOnServer();
	public void endRun(JSONObject serverResponse) {
		this.afterRunOnServer(serverResponse);
		this._status = CommandStatus.end;
	}
	protected abstract void afterRunOnServer(JSONObject response);
	
	public abstract void undo();
	public abstract void redo();
	public abstract void rollback();
	
	
	public abstract String getText();
	@Override
	public String toString() {
		String text = this.getText();
		return text == null?super.toString():this.getText();
	}
}
