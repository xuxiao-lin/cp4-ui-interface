package com.cp4biz.cp4.uiinterface.plugin;

import org.json.JSONObject;

public class PluginInfo {
	private String _key;
	private int _mainVersion;
	private int _subversion;
	private String _name;
	private String _description;
	
	public boolean checkRequriedPluginIsInstalled() {
		return true;
	}
	
	public void loadFromLocal(JSONObject installInfo) {
		
	}
	public void install() {
		
	}
}
