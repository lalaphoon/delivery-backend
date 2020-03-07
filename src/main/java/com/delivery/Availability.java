package com.delivery;

import db.DistributorMySQLConnection;
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
            DistributorMySQLConnection connection = new DistributorMySQLConnection();
            int a_drone = connection.getDroneCounter("A");
            int a_robot = connection.getRobotCounter("A");
            int b_drone = connection.getDroneCounter("B");
            int b_robot = connection.getRobotCounter("B");
            int c_drone = connection.getDroneCounter("C");
            int c_robot = connection.getRobotCounter("C");

            connection.close();

            array.put(new JSONObject()
                    .put("station_id","A")
                    .put("station_name" , "Sunset Recreation Center")
                    .put("lat", 37.7571)
                    .put("lng", -122.4866)
                    .put("drone", a_drone)
                    .put("robot", a_robot)
            );

            array.put(new JSONObject()
                    .put("station_id","B")
                    .put("station_name" , "St Mary's Square")
                    .put("lat", 37.7920)
                    .put("lng", -122.4052)
                    .put("drone", b_drone)
                    .put("robot", b_robot)
            );

            array.put(new JSONObject()
                    .put("station_id","C")
                    .put("station_name" , "Holly Park")
                    .put("lat", 37.4414)
                    .put("lng", -122.2515)
                    .put("drone", c_drone)
                    .put("robot", c_robot)
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RpcHelper.writeJsonArray(response, array);
    }
}
