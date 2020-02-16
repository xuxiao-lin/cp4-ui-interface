package com.cp4biz.cp4.uiinterface.dataflow;

public class InputTerminal extends Terminal {

	private DataFlow _connection;
	public void setConnection(DataFlow flow) {
		this._connection = flow;
	}
	public void removeConnection(DataFlow flow) {
		this._connection = null;
	}
	public DataFlow getConnection(){
		return this._connection;
	}
}
