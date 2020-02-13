package com.cp4biz.cp4.uiinterface.plugin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class PluginManager {
	protected static PluginManager _instance;
	public static PluginManager getInstance() {
		return _instance;
	}
	public static boolean init(PluginManager instance) {
		if (_instance != null)
			return false;
		_instance = instance;
		return true;
	}
	
	private ArrayList<Plugin> _loadedPlugins;
	public void loadLocalPlugins() {
		JSONObject jso = this.loadInstalledJoPluginList();
		JSONArray jaPlugins = jso.getJSONArray("plugins");
		this._loadedPlugins = new ArrayList<Plugin>();
		for (int i=0;i<jaPlugins.length();i++) {
			JSONObject joPlugin = jaPlugins.getJSONObject(i);
			Plugin plugin = this.loadInstalledPluginFromJso(joPlugin);
			this._loadedPlugins.add(plugin);
		}
	}
	
	public ArrayList<Plugin> getLoadedPlugins(){
		return null;
	}
	
	public ArrayList<PluginInfo> getAvailableLastestPluginInfos(){
		return null;
	}
	
	private JSONObject loadInstalledJoPluginList() {
		return null;
	}
	private Plugin loadInstalledPluginFromJso(JSONObject jso) {
		return null;
	}
}
