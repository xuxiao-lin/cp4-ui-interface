package com.viridisio.cp4.uiinterface.usercommand;

import java.util.Stack;

import com.viridisio.cp4.uiinterface.usercommand.UserCommand.CommandStatus;

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
			switch (this._lastCommand.getCommandStatus()){
			case startRun:
				this.finishCurrentCommand();break;
				default:;
			}
		}
		for (UserCommand oldCommand : this._finishedCommands) {
			oldCommand.cancel();
		}
		this._finishedCommands.clear();
		this._lastCommand = command;
		command.changeStatus(CommandStatus.startRun);
		command.startRun();
	}
	public void lockCommands() {
		
	}
	public void unlockCommands() {
		
	}
	public void finishCurrentCommand() {
		if (this._lastCommand != null) {
			switch (this._lastCommand.getCommandStatus()) {
			case startRun:
				this._lastCommand.endRun();
				this._lastCommand.changeStatus(CommandStatus.endRun);
				this._finishedCommands.push(_lastCommand);
				break;
			default:;
			}
		}
	}
	public void commandWorkFailed(UserCommand command) {
		//TODO call command.rollback
	}
	public void undo() {
		_lastCommand = this._finishedCommands.pop();
		_lastCommand.undo();
	}
	public void redo() {
		_lastCommand = this._undoedCommands.pop();
		_lastCommand.redo();
	}
}
