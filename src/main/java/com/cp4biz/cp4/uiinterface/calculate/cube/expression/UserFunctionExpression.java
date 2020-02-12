package com.cp4biz.cp4.uiinterface.calculate.cube.expression;

import java.util.ArrayList;
import java.util.HashSet;

import com.cp4biz.cp4.uiinterface.calculate.cube.CubeVar;

public class UserFunctionExpression extends Expression {
	private UserFunction _function;
	private ArrayList<Expression> _inputExpresssions = new ArrayList<Expression>();
	@Override
	public boolean checkMustRunFromServer() {
		if (this._function.checkIsServerFunction())
			return true;
		for (Expression exp : this._inputExpresssions) {
			if (exp.checkMustRunFromServer())
				return true;
		}
		return false;
	}

	@Override
	public ExpressionValue frontendRun() {
		ArrayList<ExpressionValue> vals = new ArrayList<ExpressionValue>();
		for (Expression exp : this._inputExpresssions) {
			vals.add(exp.frontendRun());
		}
		return this._function.run(vals);
	}

	@Override
	public ExpressionValueType getValueType() {
		return this.getValueType();
	}

	@Override
	public HashSet<CubeVar> getInputVars() {
		// TODO Auto-generated method stub
		return null;
	}

}
