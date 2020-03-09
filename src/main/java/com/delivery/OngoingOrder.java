package com.delivery;

import db.OrderMySQLConnection;
import entity.Order;
import org.json.JSONException;
import org.json.JSONObject;
import rpc.OrderRpcHelper;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OngoingOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject input = RpcHelper.readJSONObject(request);
        try {
            // String
            OrderMySQLConnection connection = new OrderMySQLConnection();
            Order obj = OrderRpcHelper.parseOngoingOrder(input);
            connection.insertOrderRecord(obj);
            connection.close();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            RpcHelper.writeJsonObject(response, new JSONObject().put("result", "SUCCESS"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
