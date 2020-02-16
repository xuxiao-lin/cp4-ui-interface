package com.cp4biz.cp4.uiinterface.dfdmanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

import com.cp4biz.cp4.uiinterface.dataflow.*;
import com.cp4biz.cp4.uiinterface.dataflow.composedProcessor.ComposedProcessor;

public class DFDMananger {
	public HashSet<DataProcessor> getNextProcessors(DataProcessor source){
		HashSet<DataProcessor> nexts = new HashSet<DataProcessor>();
		Collection<OutputTerminal> outputs = source.getOutputTerminals();
		for (OutputTerminal terminal:outputs) {
			ArrayList<DataFlow> flows = terminal.getConnections();
			for (DataFlow flow:flows) {
				nexts.add(flow.getEndTerminal().getProcessor());
			}
		}
		return nexts;
	}
	public HashSet<DataProcessor> getPreviousProcessors(DataProcessor source){
		HashSet<DataProcessor> previous = new HashSet<DataProcessor>();
		Collection<InputTerminal> inputs = source.getInputTerminals();
		for (InputTerminal terminal:inputs) {
			DataFlow flow = terminal.getConnection();
			previous.add(flow.getStartTerminal().getProcessor());
		}
		return previous;
	}
	public DataProcessor getParent(DataProcessor source) {
		return source.getParent();
	}
	public ArrayList<DataProcessor> getChildren(ComposedProcessor source){
		return source.getChildren();
	}

	
	private ArrayList<DataProcessor> _processors = new ArrayList<DataProcessor>();
	private ArrayList<DataFlow> _flows = new ArrayList<DataFlow>();
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

	public HashSet<DataProcessor> getAffectedProcessors(Terminal source){
		HashSet<DataProcessor> checkedProcessors = new HashSet<DataProcessor>();
		Stack<DataProcessor> waitForCheckProcessors = new Stack<DataProcessor>();
		HashSet<DataProcessor> nextProcessors = new HashSet<DataProcessor>();
		waitForCheckProcessors.push(source.getProcessor());
		while (waitForCheckProcessors.size()>0) {
			DataProcessor pro = waitForCheckProcessors.pop();
			checkedProcessors.add(pro);
			nextProcessors = this.getNextProcessors(pro);
			for (DataProcessor affected:nextProcessors) {
				if (!checkedProcessors.contains(affected))
					waitForCheckProcessors.push(affected);
			}
		}
		if (source instanceof OutputTerminal) {
			checkedProcessors.remove(source.getProcessor());
		}
		return checkedProcessors;
	}
}
