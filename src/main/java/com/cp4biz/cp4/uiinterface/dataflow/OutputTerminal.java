package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.ArrayList;

public class OutputTerminal extends Terminal {
	private ArrayList<DataFlow> _connections = new ArrayList<DataFlow>();
	public void addConnection(DataFlow flow) {
		this._connections.add(flow);
	}
	public void removeConnection(DataFlow flow) {
		this._connections.remove(flow);
	}
	public ArrayList<DataFlow> getConnections(){
		return this._connections;
	}
}
