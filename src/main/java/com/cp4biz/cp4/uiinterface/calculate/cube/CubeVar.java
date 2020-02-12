package com.cp4biz.cp4.uiinterface.calculate.cube;

import com.cp4biz.cp4.uiinterface.calculate.cube.CubeVarEvent.EventTypes;
import com.cp4biz.cp4.uiinterface.calculate.cube.expression.Expression;
import com.cp4biz.cp4.uiinterface.calculate.cube.expression.ExpressionValue;

public class CubeVar implements ICubeVarEventListener {
	private Expression _expression;//for expression var only
	public void setExpression(Expression expression) {
		
	}
	public Expression getExpression() {
		return null;
	}
	private ExpressionValue _currentValue;
	public void updateValueWithoutDispatchEvent(ExpressionValue newValue) {
		this._currentValue = newValue;
	}
	public ExpressionValue getValue() {
		return this._currentValue;
	}
	public void updateValue(ExpressionValue newValue, EventTypes eventType) {
		CubeVarEvent evt = new CubeVarEvent(this);
		if (eventType != null) {
			evt.sourceVar = this;
			evt.type = eventType;
		}
		if (newValue == null) {
			if (this._currentValue != null) {
				this._currentValue = newValue;
				if (eventType != null)
					this._eventComponent.dispatchEvent(evt);
			}
		}
		else {
			if (!newValue.equals(_currentValue)) {
				this._currentValue = newValue;
				if (eventType != null)
					this._eventComponent.dispatchEvent(evt);
			}
		}
		
	}
	private CubeProxy _cubeProxy;
	public CubeProxy getCubeProxy() {
		return this._cubeProxy;
	}
	public void iniCubeProxy(CubeProxy cubeProxy) {
		this._cubeProxy = cubeProxy;
	}
	private String _key;
	public void setKey(String key) {
		this._key = key;
	}
	public String getKey() {
		return this._key;
	}
	public ExpressionValue run() {
		ExpressionValue result = null;
		if (this._isReadonly) {
			result = this.getCubeProxy().updateReadonlyVarValue(this);
		}
		else if (this._expression != null) {
			result = this._expression.run();
		}
		return result;
	}
	public boolean checkMustRunFromServer() {
		if (this._isReadonly)
			return this.getCubeProxy().checkReadonlyVarMustRunFromServer(this);
		else if (this._expression != null)
			return this._expression.checkMustRunFromServer();
		return false;
	}
	
	private boolean _isReadonly = false;
	public boolean getIsReadonly() {return this._isReadonly;}
	public void setIsReadonly(boolean isReadonly) {this._isReadonly = isReadonly;}
	
	private CubeVarEventComponent _eventComponent = new CubeVarEventComponent();
	public void addListener(ICubeVarEventListener listener, EventTypes eventType) {
		this._eventComponent.addListener(listener, eventType);
	}
	public void removeListener(ICubeVarEventListener listener, EventTypes eventType) {
		this._eventComponent.removeListener(listener, eventType);
	}
	public void onCubeVarChanging(CubeVarEvent event) {
		if (!this.checkMustRunFromServer()) {
			ExpressionValue result = this.run();
			this.updateValue(result, EventTypes.changing);
		}
	}
}
