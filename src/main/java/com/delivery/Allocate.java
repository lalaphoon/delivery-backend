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
        //TODO: fix it to be real
        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            JSONArray route1 = new JSONArray();
            route1.put(new JSONObject().put("lat",37.7571 ).put("lng", -122.4866));
            route1.put(new JSONObject().put("lat",37.777630).put("lng", -122.496440));
            route1.put(new JSONObject().put("lat",37.777340 ).put("lng", -122.410350));
            route1.put(new JSONObject().put("lat",37.792 ).put("lng", -122.4052 ));

            JSONArray route2 = new JSONArray();
            route2.put(new JSONObject().put("lat",37.7571 ).put("lng", -122.4866));
            route2.put(new JSONObject().put("lat",37.792 ).put("lng", -122.4052 ));

            JSONArray marker = new JSONArray();
            marker.put(new JSONObject().put("marker_name", "Starting").put("location", new JSONObject().put("lat", 37.777630).put("lng", -122.496440)));
            marker.put(new JSONObject().put("marker_name", "Destination").put("location", new JSONObject().put("lat", 37.777340).put("lng", -122.410350)));


            array.put(new JSONObject()
                    .put("route_id","RT_SASjsdf23j")
                    .put("price" , 98.6)
                    .put("deliver_type", "DRONE")
                    .put("available_time", "2019-04-24 18:00:00")
                    .put("usage_time", "00:15:00")
                    .put("distance", 534.1)
                    .put("route", route1)
            );


            array.put(new JSONObject()
                    .put("route_id","RT_lsjdf242ew")
                    .put("price" , 54.2)
                    .put("deliver_type", "ROBOT")
                    .put("available_time", "2019-04-24 18:00:00")
                    .put("usage_time", "01:01:12")
                    .put("distance", 1534.1)
                    .put("route", route2)
            );

            obj.put("routes", array);
            obj.put("order_id", "OR_1234");
            obj.put("markers", marker);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RpcHelper.writeJsonObject(response, obj);
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
            obj.put("order_id", "OR_1234");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RpcHelper.writeJsonObject(response, obj);
    }
}
