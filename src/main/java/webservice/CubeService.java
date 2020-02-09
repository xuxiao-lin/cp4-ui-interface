package webservice;

import org.json.JSONObject;

import com.viridisio.cp4.uiinterface.calculate.cube.CubeManager;
import com.viridisio.cp4.uiinterface.calculate.cube.CubeProxy;
import com.viridisio.cp4.uiinterface.calculate.cube.CubeVar;

public class CubeService extends Webservice {
	public static CubeProxy createCube() {
		JSONObject jso = callService("", "get", null);
		CubeProxy cube = CubeManager.getInstance().createCubeProxy();
		return cube;
	}
	public static void commitCubeChanges(CubeProxy cube) {
		JSONObject jso = new JSONObject();
		jso.put("id", cube.getId());
		jso.put("data",cube.getCommitChangesJsonData());
		callService("", "post", jso);
	}
	public static void cancelCubeChanges(CubeProxy cube) {
		
	}
	public static void runOnServer(CubeVar var) {
		
	}
}
