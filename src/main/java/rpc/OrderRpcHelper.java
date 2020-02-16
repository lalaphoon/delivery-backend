package rpc;

import entity.Order;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRpcHelper {
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
