package com.cp4biz.cp4.uiinterface.calculate.expression;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.DataType;
import com.cp4biz.cp4.uiinterface.dataflow.DataValue;
import com.cp4biz.cp4.uiinterface.dataflow.IDataInterface;

public class UserFunctionExpression extends Expression {
	private UserFunction _func;
	public void setFunc(UserFunction func) {this._func = func;}
	public UserFunction getFunc() {return this._func;}
	
	private ArrayList<Expression> _inputs;
	public void setInputs(ArrayList<Expression> inputs) {this._inputs = inputs;}
	public ArrayList<Expression> getInputs(){return this._inputs;}

	@Override
	public boolean checkMustRunFromServer() {
		if (_func == null)
			return false;
		return _func.checkMustRunFromServer();
	}

	@Override
	public DataValue run() {
		ArrayList<DataValue> inputs = new ArrayList<DataValue>();
		for (Expression exp : this._inputs) {
			inputs.add(exp.run());
		}
		return this._func.run(inputs);
	}

	@Override
	public DataType getValueType() {
		return this._func.getResultType();
	}

}
