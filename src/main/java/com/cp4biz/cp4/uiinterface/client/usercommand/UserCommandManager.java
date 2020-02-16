package com.cp4biz.cp4.uiinterface.client.usercommand;

import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cp4biz.cp4.uiinterface.client.usercommand.UserCommand.CommandStatus;
import com.cp4biz.cp4.uiinterface.oldcalculate.cube.CubeManager;

import webservice.CommandService;

public class UserCommandManager {
	private static UserCommandManager _instance;
	public static boolean init(UserCommandManager instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	public static UserCommandManager getInstance() {
		return _instance;
	}
	private UserCommand _lastCommand;
	private Stack<UserCommand> _finishedCommands = new Stack<UserCommand>();
	private Stack<UserCommand> _undoedCommands = new Stack<UserCommand>();
	public UserCommand getLastCommand() {
		return this._lastCommand;
	}
	public void startNewCommand(UserCommand command) {
		if (this._lastCommand != null) {
			if (this._lastCommand.getStatus() != CommandStatus.end) {
				this.finishCurrentCommand();
			}
		}
		this._finishedCommands.clear();
		this._lastCommand = command;
		command.setStatus(CommandStatus.start);
		command.startCommand();
	}
	public void finishCurrentCommand() {
		this.runCurrentCommand();
	}
	public void runCurrentCommand() {
		if (this._lastCommand == null || this._lastCommand.getStatus() != CommandStatus.start)
			return;
		this._lastCommand.startRun();
		boolean toServer = this._lastCommand.mustRunOnServer();
		JSONObject response = null;
		if (toServer) {
			//cubemanager update
			CubeManager cubeManager = CubeManager.getInstance();
			JSONArray changes = cubeManager.getAllCubesChangesData();
			//run on server
			JSONObject data = this._lastCommand.sendDataToRunOnServer();
			JSONObject jso = new JSONObject();
			jso.put("cubeChanges", changes);
			jso.put("data", data);
			jso.put("key", this._lastCommand.getKey());
			response = CommandService.callServerRunCommand(data);
		}
		this._lastCommand.endRun(response);
		this._lastCommand.setStatus(CommandStatus.end);
	}
	public void commandWorkFailed(UserCommand command) {
		command.rollback();
		if (this._finishedCommands.size()>0)
			this._lastCommand = this._finishedCommands.pop();
		else
			this._lastCommand = null;
	}
	public void undo() {
		if (this._finishedCommands.size() > 0) {
			_lastCommand.undo();
			this._undoedCommands.push(_lastCommand);
			_lastCommand = this._finishedCommands.peek();
		}
	}
	public void redo() {
		if (this._undoedCommands.size() > 0) {
			_lastCommand = this._undoedCommands.pop();
			_lastCommand.redo();
			this._finishedCommands.push(_lastCommand);
		}
	}
}
