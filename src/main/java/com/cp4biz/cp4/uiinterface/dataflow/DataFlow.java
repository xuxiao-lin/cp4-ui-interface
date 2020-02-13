package com.cp4biz.cp4.uiinterface.dataflow;

public class DataFlow implements ISwitch {
	private Terminal _startTerminal;
	private Terminal _endTerminal;
	public void transferValue() {
		this._endTerminal.setValue(this._endTerminal == null?null:this._startTerminal.getValue());
	}
	private boolean _switchOn = true;
	public void setSwitchOn(boolean value) {
		this._switchOn = value;
	}
	public boolean getSwitchOn() {
		return this._switchOn;
	}
	public void onStartValueChanged() {
		if (this._switchOn)
			this.transferValue();
	}
}
