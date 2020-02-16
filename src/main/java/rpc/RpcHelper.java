package rpc;

import entity.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static JSONObject readJSONObject(HttpServletRequest request) {
        StringBuilder sBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sBuilder.append(line);
            }
            return new JSONObject(sBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }


    public static Order parseHistoryOrder(JSONObject historyOrder) throws JSONException {
        Order.OrderBuilder builder = new Order.OrderBuilder();
        builder.setOrderId(historyOrder.getString("order_id"));
        builder.setUserId(historyOrder.getString("user_id"));
        builder.setFromLoc(historyOrder.getString("location_from"));
        builder.setToLoc(historyOrder.getString("location_to"));
        builder.setDroneId(historyOrder.getString("drone_id"));
        builder.setTimeStart(historyOrder.getString("time_start"));
        builder.setTimeEnd(historyOrder.getString("time_end"));
        builder.setTotalWeight(historyOrder.getDouble("total_weight"));
        builder.setPrice(historyOrder.getDouble("price"));
        return builder.build();
    }

    public static Order parseHistoryOrderResultSet(ResultSet historyOrder) throws JSONException {
        Order.OrderBuilder builder = new Order.OrderBuilder();
        try {
            builder.setOrderId(historyOrder.getString("order_id"));
            builder.setUserId(historyOrder.getString("user_id"));
            builder.setFromLoc(historyOrder.getString("location_from"));
            builder.setToLoc(historyOrder.getString("location_to"));
            builder.setDroneId(historyOrder.getString("drone_id"));
            builder.setTimeStart(historyOrder.getString("time_start"));
            builder.setTimeEnd(historyOrder.getString("time_end"));
            builder.setTotalWeight(historyOrder.getDouble("total_weight"));
            builder.setPrice(historyOrder.getDouble("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
}
