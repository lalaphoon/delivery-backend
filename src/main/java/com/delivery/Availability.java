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

@WebServlet("/availability")
public class Availability extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray array = new JSONArray();
        //TODO: make it fetch the real db for checking delivery availiability of each dispatcher station
        try {
            array.put(new JSONObject()
                    .put("station_id","A")
                    .put("station_name" , "Sunset Recreation Center")
                    .put("lat", 37.7571)
                    .put("lon", -122.4866)
                    .put("drone", 3)
                    .put("robot", 15)
            );

            array.put(new JSONObject()
                    .put("station_id","B")
                    .put("station_name" , "St Mary's Square")
                    .put("lat", 37.7920)
                    .put("lon", -122.4052)
                    .put("drone", 4)
                    .put("robot", 2)
            );

            array.put(new JSONObject()
                    .put("station_id","C")
                    .put("station_name" , "Holly Park")
                    .put("lat", 37.4414)
                    .put("lon", -122.2515)
                    .put("drone", 5)
                    .put("robot", 6)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RpcHelper.writeJsonArray(response, array);
    }
}
