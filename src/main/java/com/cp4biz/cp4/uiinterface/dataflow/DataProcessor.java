package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.HashMap;
import java.util.Set;

import javax.xml.transform.Templates;

public abstract class DataProcessor implements ISwitch,IDataInterfaceEventListener {
	public abstract void run();
	public abstract boolean checkRunFromServer();
	private HashMap<String, Terminal> _inputTerminals = new HashMap<String, Terminal>();
	private HashMap<String, Terminal> _outputTerminals = new HashMap<String, Terminal>();
	protected HashMap<String, InnerDataPoint> _innerOutputs = new HashMap<String, InnerDataPoint>();
	public Terminal getInputTerminal(String key) {
		return this._inputTerminals.get(key);
	}
	public Terminal getOutputTerminal(String key) {
		return this._outputTerminals.get(key);
	}
	public InnerDataPoint getInnerOutput(String key) {
		return this._innerOutputs.get(key);
	}
	public void onInputValueChanged(Terminal inputTerminal) {
		if (this._switchOn)
			this.run();
	}
	protected void addTerminal(Terminal terminal, boolean isOutput) {
		terminal.ini(this, isOutput);
		if (isOutput)
			this._outputTerminals.put(terminal.getKey(), terminal);
		else
			this._inputTerminals.put(terminal.getKey(), terminal);
		DataInterfaceEvent.addListener(terminal, this);
	}
	protected void removeTerminal(String key,boolean isOutput) {
		Terminal terminal = null;
		if (isOutput) {
			terminal = this._outputTerminals.get(key);
			this._outputTerminals.remove(key);
		}
		else {
			terminal = this._inputTerminals.get(key);
			this._inputTerminals.remove(key);
		}
		if (terminal != null)
			DataInterfaceEvent.removeListener(terminal, this);
	}
	public void removeAllInputTerminals() {
		Set<String> keyset = this._inputTerminals.keySet();
		for (String key:keyset) {
			this.removeTerminal(key, false);
		}
	}
	public void removeAllOutputTerminals() {
		Set<String> keyset = this._outputTerminals.keySet();
		for (String key:keyset) {
			this.removeTerminal(key, true);
		}
	}
	private boolean _switchOn = true;
	public void setSwitchOn(boolean value) {
		this._switchOn = value;
	}
	public boolean getSwitchOn() {
		return this._switchOn;
	}
}
