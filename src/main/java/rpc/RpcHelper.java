package rpc;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RpcHelper {
    //Writes a JSONArray to http response
    public static void writeJsonArray(HttpServletResponse response, JSONArray array) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print(array);
    }
    // Writes a JSONObject to http response
    public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print(obj);
    }
}
