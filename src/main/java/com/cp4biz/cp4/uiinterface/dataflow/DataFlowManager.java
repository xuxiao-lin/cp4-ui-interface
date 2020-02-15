package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.ArrayList;

public class DataFlowManager {
	private static DataFlowManager _instance;
	public static boolean init(DataFlowManager instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	public static DataFlowManager getInstance() {
		return _instance;
	}
	
	public void closeAllSwitches() {
		for (DataProcessor processor: this._processors) {
			processor.setSwitchOn(false);
		}
		for (DataFlow flow:this._flows) {
			flow.setSwitchOn(false);
		}
	}
	public void openAllSwitches() {
		for (DataProcessor processor: this._processors) {
			processor.setSwitchOn(true);
		}
		for (DataFlow flow:this._flows) {
			flow.setSwitchOn(true);
		}
	}
	public boolean checkRunFromServer(IDataInterface source) {
		return false;
	}
	private ArrayList<DataProcessor> _processors = new ArrayList<DataProcessor>();
	private ArrayList<DataFlow> _flows = new ArrayList<DataFlow>();
}
