package com.viridisio.cp4.uiinterface.calculate.cube;

import com.viridisio.cp4.uiinterface.calculate.cube.expression.Expression;
import com.viridisio.cp4.uiinterface.calculate.cube.expression.ExpressionValue;
import com.viridisio.cp4.uiinterface.usercommand.UserCommand;

public class ChangeCubeVarCommand extends UserCommand {
	public CubeVar changingVar;
	public ExpressionValue oldValue;
	public Expression oldExpression;
	public ExpressionValue newValue;
	public Expression newExpression;
	@Override
	public void startRun() {
		CubeManager.getInstance().onCubeVarChanging(changingVar);
	}

	@Override
	public void endRun() {
		CubeManager.getInstance().cubeVarChanged(changingVar);
	}

	@Override
	public void undo() {
		changingVar.setExpression(oldExpression);
		changingVar.setValue(oldValue);
		CubeManager.getInstance().cubeVarChanged(changingVar);
	}

	@Override
	public void redo() {
		changingVar.setExpression(newExpression);
		changingVar.setValue(newValue);
		CubeManager.getInstance().cubeVarChanged(changingVar);
	}

	@Override
	public void commit() {
		changingVar.getCubeProxy().commitChanges();
	}

	@Override
	public void cancel() {
		changingVar.getCubeProxy().cancelChanges();
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return "change var";
	}

}
