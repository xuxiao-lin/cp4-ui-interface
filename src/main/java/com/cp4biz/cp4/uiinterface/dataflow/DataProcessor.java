package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public abstract class DataProcessor implements ISwitch,IDataInterfaceEventListener {
	public abstract boolean checkRunFromServer();
	private HashMap<String, InputTerminal> _inputTerminals = new HashMap<String, InputTerminal>();
	private HashMap<String, OutputTerminal> _outputTerminals = new HashMap<String, OutputTerminal>();
	protected HashMap<String, InnerDataPoint> _innerOutputs = new HashMap<String, InnerDataPoint>();
	public InputTerminal getInputTerminal(String key) {
		return this._inputTerminals.get(key);
	}
	public OutputTerminal getOutputTerminal(String key) {
		return this._outputTerminals.get(key);
	}
	public InnerDataPoint getInnerOutput(String key) {
		return this._innerOutputs.get(key);
	}
	public Collection<OutputTerminal> getOutputTerminals(){
		return this._outputTerminals.values();
	}
	public Collection<InputTerminal> getInputTerminals(){
		return this._inputTerminals.values();
	}

	protected void addInputTerminal(InputTerminal terminal) {
		terminal.ini(this);
		this._inputTerminals.put(terminal.getKey(), terminal);
		DataInterfaceEvent.addListener(terminal,this);
	}
	protected void addOutputTerminal(OutputTerminal terminal) {
		terminal.ini(this);
		this._outputTerminals.put(terminal.getKey(), terminal);
	}
	protected void removeInputTerminal(String key) {
		Terminal terminal = this._inputTerminals.get(key);
		if (terminal != null) {
			this._inputTerminals.remove(key);
			DataInterfaceEvent.removeListener(terminal, this);
		}
	}
	protected void removeOutputTerminal(String key) {
		this._outputTerminals.remove(key);
	}
	protected void removeAllInputTerminals() {
		Set<String> keyset = this._inputTerminals.keySet();
		for (String key:keyset) {
			this.removeInputTerminal(key);
		}
	}
	protected void removeAllOutputTerminals() {
		Set<String> keyset = this._outputTerminals.keySet();
		for (String key:keyset) {
			this.removeOutputTerminal(key);
		}
	}
	protected abstract void handleInputTerminalValueChanged(InputTerminal inputTerminal);
	public void onInputValueChanged(InputTerminal inputTerminal) {
		if (this._switchOn)
			this.handleInputTerminalValueChanged(inputTerminal);
	}
	private boolean _switchOn = true;
	public void setSwitchOn(boolean value) {
		this._switchOn = value;
	}
	public boolean getSwitchOn() {
		return this._switchOn;
	}
}
