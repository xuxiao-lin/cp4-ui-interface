package com.cp4biz.cp4.uiinterface.dataflow;

import java.util.HashMap;

public abstract class DataProcessor implements ISwitch {
	public abstract void run();
	public abstract boolean checkRunFromServer();
	protected HashMap<String, Terminal> _inputTerminals = new HashMap<String, Terminal>();
	protected HashMap<String, Terminal> _outputTerminals = new HashMap<String, Terminal>();
	public Terminal getInputTerminal(String key) {
		return this._inputTerminals.get(key);
	}
	public Terminal getOutputTerminal(String key) {
		return this._outputTerminals.get(key);
	}
	public void onInputValueChanged(Terminal inputTerminal) {
		if (this._switchOn)
			this.run();
	}
	
	private boolean _switchOn = true;
	public void setSwitchOn(boolean value) {
		this._switchOn = value;
	}
	public boolean getSwitchOn() {
		return this._switchOn;
	}
}
