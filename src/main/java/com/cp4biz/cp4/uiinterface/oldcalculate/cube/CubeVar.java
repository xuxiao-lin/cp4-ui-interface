package com.cp4biz.cp4.uiinterface.oldcalculate.cube;

import com.cp4biz.cp4.uiinterface.oldcalculate.cube.CubeVarEvent.EventTypes;
import com.cp4biz.cp4.uiinterface.oldcalculate.expression.Expression;
import com.cp4biz.cp4.uiinterface.oldcalculate.expression.ExpressionValue;
import com.cp4biz.cp4.uiinterface.oldcalculate.expression.ExpressionValueType;

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
	private ExpressionValueType _valueType;
	public ExpressionValueType getValueType() {
		return this._valueType;
	}
	private CubeProxy _cubeProxy;
	public CubeProxy getCubeProxy() {
		return this._cubeProxy;
	}
	public void ini(CubeProxy cubeProxy,ExpressionValueType valueType) {
		this._cubeProxy = cubeProxy;
		this._valueType = valueType;
	}
	private String _key;
	public void setKey(String key) {
		this._key = key;
	}
	public String getKey() {
		return this._key;
	}
	public ExpressionValue frontendRun() {
		ExpressionValue result = null;
		if (this._isReadonly) {
			result = this.getCubeProxy().updateReadonlyVarFrontendRunValue(this);
		}
		else if (this._expression != null) {
			result = this._expression.frontendRun();
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
			ExpressionValue result = this.frontendRun();
			this.updateValue(result, EventTypes.changing);
		}
	}
}
