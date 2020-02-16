package com.cp4biz.cp4.uiinterface.dataflow.composedProcessor;

import java.util.ArrayList;

import com.cp4biz.cp4.uiinterface.dataflow.DataInterfaceEvent;
import com.cp4biz.cp4.uiinterface.dataflow.DataProcessor;

public class ComposedProcessor extends DataProcessor {

	@Override
	public void onDataInterfaceValueChanged(DataInterfaceEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkRunFromServer() {
		for (DataProcessor child:this._children) {
			if (child.checkRunFromServer())
				return true;
		}
		return false;
	}
	private ArrayList<DataProcessor> _children = new ArrayList<DataProcessor>();
	public ArrayList<DataProcessor> getChildren() {
		return _children;
	}

}
