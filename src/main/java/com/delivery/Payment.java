package com.delivery;

import db.DistributorMySQLConnection;
import db.OrderMySQLConnection;
import db.RouteMySQLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment")
public class Payment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        response.setContentType("application/json");

        JSONObject obj = new JSONObject();

        JSONObject input = RpcHelper.readJSONObject(request);
        try {
            //Step 1, use route_id to find route & price & type
            String route_id = input.getString("route_id");
            String order_id = input.getString("order_id");
            String user_id = input.getString("user_id");
            RouteMySQLConnection rconnection = new RouteMySQLConnection();
            JSONObject routeRecord = rconnection.findRouteByID(route_id);
            //"route"
            //"deliver_type"
            //"price"
            rconnection.close();

            //Step 2, drag a deliver(deliver_id) from table A that match type == deliver_type
            String deliver_type = routeRecord.getString("deliver_type");
            DistributorMySQLConnection dconnection = new DistributorMySQLConnection();
            String deliver_id = dconnection.getDeliver(routeRecord);
            dconnection.close();

            //Step 3, update price, route, deliver_id of table order based on order_id
            OrderMySQLConnection oconnection = new OrderMySQLConnection();
            if(deliver_id != null) {
                oconnection.updateOrder(order_id,deliver_id,routeRecord.getDouble("price"), routeRecord.getString("route"));
            }
            obj.put("order_id", order_id);
            obj.put("deliver_id", deliver_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RpcHelper.writeJsonObject(response, obj);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
