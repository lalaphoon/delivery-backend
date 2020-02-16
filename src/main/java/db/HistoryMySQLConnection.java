package db;


import entity.Order;
import org.json.JSONObject;
import rpc.RpcHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HistoryMySQLConnection extends MySQLConnection {
    public Set<Order> getHistoryOrders(String userId) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return new HashSet<>();
        }

        Set<Order> historyOrder = new HashSet<>();
        try {
            String sql = "SELECT * FROM history WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Order item = RpcHelper.parseHistoryOrderResultSet(rs);
                historyOrder.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyOrder;
    }
}
