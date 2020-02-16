package com.delivery;

import db.HistoryMySQLConnection;
import entity.Order;
import org.json.JSONArray;
import org.json.JSONObject;
import rpc.RpcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/history")
public class History extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        if (request.getParameter("user_id") != null) {
            String userId = request.getParameter("user_id");
            JSONArray array = new JSONArray();

            HistoryMySQLConnection connection = new HistoryMySQLConnection();
            Set<Order> orders = connection.getHistoryOrders(userId);

            connection.close();

            for (Order order : orders) {
                JSONObject obj = order.toJSONObject();
                array.put(obj);
            }
            RpcHelper.writeJsonArray(response, array);
        }
    }
}
