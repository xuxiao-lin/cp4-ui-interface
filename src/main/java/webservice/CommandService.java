package webservice;

import org.json.JSONObject;

public class CommandService extends Webservice {
	public static JSONObject callServerRunCommand(JSONObject data) {
		return callService("", "post", data);
	}
}
