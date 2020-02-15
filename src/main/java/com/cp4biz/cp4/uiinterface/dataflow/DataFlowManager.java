package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

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
	public boolean checkRunFromServer(Terminal source) {
		HashSet<DataProcessor> affects = this.getAffectedProcessors(source);
		for (DataProcessor affected : affects) {
			if (affected.checkRunFromServer())
				return true;
		}
		return false;
	}
	public HashSet<DataProcessor> getAffectedProcessors(Terminal source){
		HashSet<DataProcessor> checkedProcessors = new HashSet<DataProcessor>();
		Stack<DataProcessor> nextProcessors = new Stack<DataProcessor>();
		HashSet<DataProcessor> directedAffects = new HashSet<DataProcessor>();
		nextProcessors.push(source.getProcessor());
		while (nextProcessors.size()>0) {
			DataProcessor pro = nextProcessors.pop();
			checkedProcessors.add(pro);
			Collection<Terminal> outputs = pro.getOutputTerminals();
			directedAffects.clear();
			for (Terminal output : outputs) {
				directedAffects.addAll(this.getAffectedProcessors(output));
			}
			for (DataProcessor affected:directedAffects) {
				if (!checkedProcessors.contains(affected))
					nextProcessors.push(affected);
			}
		}
		return checkedProcessors;
	}
	public HashSet<DataProcessor> getDirectAffectedProcessors(Terminal source){
		HashSet<DataProcessor> affects = new HashSet<DataProcessor>();
		for (DataFlow flow:this._flows) {
			if (flow.getStartTerminal() == source && flow.getEndTerminal() != null) {
				affects.add(flow.getEndTerminal().getProcessor()); 
			}
		}
		return affects;
	}
	
	private ArrayList<DataProcessor> _processors = new ArrayList<DataProcessor>();
	private ArrayList<DataFlow> _flows = new ArrayList<DataFlow>();
}
