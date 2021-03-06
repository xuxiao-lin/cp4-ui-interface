package com.cp4biz.cp4.uiinterface.dataflow;

import com.cp4biz.cp4.uiinterface.dataflow.DataInterfaceEvent.EventType;

public class DataFlow implements ISwitch,IDataInterfaceEventListener {
	private OutputTerminal _startTerminal;
	private InputTerminal _endTerminal;
	public void transferValue() {
		if (this._endTerminal != null && this._startTerminal != null)
			this._endTerminal.setValue(this._endTerminal == null?null:this._startTerminal.getValue());
	}
	private boolean _switchOn = true;
	public Terminal getStartTerminal() {
		return this._startTerminal;
	}
	public Terminal getEndTerminal() {
		return this._endTerminal;
	}
	public void connect(OutputTerminal start, InputTerminal end) {
		if (this._startTerminal != null) {
			this._startTerminal.removeConnection(this);
			DataInterfaceEvent.removeListener(_startTerminal, this);
		}
		if (this._endTerminal != null) {
			this._endTerminal.removeConnection(this);
		}
		this._startTerminal = start;
		this._endTerminal = end;
		start.addConnection(this);
		end.setConnection(this);
		if (this._startTerminal != null)
			DataInterfaceEvent.addListener(_startTerminal, this);
	}
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
	public void onDataInterfaceValueChanged(DataInterfaceEvent event) {
		if (event.source == this._startTerminal && event.eventType == EventType.valueChanged)
			this.onStartValueChanged();
	}
}
