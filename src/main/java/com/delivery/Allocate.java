package com.delivery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allocate")
public class Allocate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: fix it to be real
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            array.put(new JSONObject()
                    .put("route_id","RT_SASjsdf23j")
                    .put("price" , 15.6)
                    .put("route", 3)
                    .put("deliver_type", "DRONE")
            );
            array.put(new JSONObject()
                    .put("route_id","RT_lsjdf242ew")
                    .put("price" , 14.2)
                    .put("route", 3)
                    .put("deliver_type", "ROBOT")

            );

            obj.put("routes", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RpcHelper.writeJsonObject(response, obj);
    }
}
