package com.delivery;

import db.OrderMySQLConnection;
import entity.Location;
import entity.Order;
import entity.Route;
import entity.RouteJSONBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rpc.GoogleMapClient;
import rpc.OrderRpcHelper;
import rpc.RpcHelper;
import utility.TimeStamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allocate")
public class Allocate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject input = RpcHelper.readJSONObject(request);
        System.out.println(input);

        //TODO: fix it to be real
//        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        try {

            //Step 0: Create Route obj and generate Route info
            GoogleMapClient gmc = new GoogleMapClient();
            Location location_from = new Location(input.getJSONObject("location_from").getDouble("lat"), input.getJSONObject("location_from").getDouble("lng"), "From");
            Location location_to = new Location(input.getJSONObject("location_to").getDouble("lat"), input.getJSONObject("location_to").getDouble("lng"), "To");
            Route route = gmc.findPath(location_from, location_to);

            // Step 1: read input and compose a /order object with input
            //         input contains user_id, location_from, location_to, total_weight
            //         we should add time_start and price
            input.put("time_start", TimeStamp.getCurrentTimestamp());
            //TODO: remove the price
            input.put("price", 0.0);

            // Step 3: insert a new order record into Order table
            OrderMySQLConnection connection = new OrderMySQLConnection();
            Order order = OrderRpcHelper.parseOngoingOrder(input);
            String orderID = connection.insertOrderRecord(order);
            connection.close();

            // Step 4: insert a list of route data into Route table

            // Step 5: return the response result
            obj.put("routes", RouteJSONBuilder.getRouteOptionsResponse(route));
            obj.put("order_id", orderID);

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

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
