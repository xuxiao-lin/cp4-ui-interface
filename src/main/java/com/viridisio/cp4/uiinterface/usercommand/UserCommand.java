package com.viridisio.cp4.uiinterface.usercommand;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;


public abstract class UserCommand {
	private CommandStatus _status;
	public enum CommandStatus{
		startRun,endRun,undoed,redoed
	}
	public CommandStatus getCommandStatus() {
		return this._status;
	}
	//以下全部由command manager调用
	public void changeStatus(CommandStatus status) {
		this._status = status;
	}
	//如异步执行，需通知manager.lock/unlockCommnds
	//执行失败时，通知manager.commandWorkFailed
	public abstract void startRun();
	public abstract void endRun();
	public abstract void undo();
	public abstract void redo();
	public abstract void commit();
	public abstract void cancel();
	public abstract void rollback();
	
	
	public abstract String getText();
	@Override
	public String toString() {
		String text = this.getText();
		return text == null?super.toString():this.getText();
	}
}
