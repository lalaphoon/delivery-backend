package db;


import entity.Order;
import org.json.JSONObject;
import rpc.OrderRpcHelper;
import utility.RandomString;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HistoryMySQLConnection extends MySQLConnection {
    public void insertHistoryRecord(Order item) {
       if (conn == null) {
           System.err.println("DB connection failed");
           return;
       }

       try {
           //('OR00003',
           // '1111' ,
           // 'M4W1W3',
           // 'M4W1W3',
           // 'DR003',
           // '2005-10-30 T 10:45 UTC',
           // '2005-10-30 T 10:45 UTC',
           // '24.5',
           // '23.00')

           String sql = "INSERT INTO history VALUES(?, ? , ?, ?, ?, ?, ?, ?, ? )";

           PreparedStatement stmt = conn.prepareStatement(sql);
           String order_id = "OR_"+ RandomString.getAlphaNumericString(15);

           stmt.setString(1, order_id);
           stmt.setString(2, item.getUserId());
           stmt.setString(3, item.getFromLocString());
           stmt.setString(4, item.getToLocString());
           stmt.setString(5, item.getDroneId());
           stmt.setString(6, item.getTimeStart());
           stmt.setString(7, item.getTimeEnd());
           stmt.setDouble(8, item.getTotalWeight());
           stmt.setDouble(9, item.getPrice());

           stmt.execute();

       } catch (SQLException e) {
           e.printStackTrace();
       }

    }
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
                Order item = OrderRpcHelper.parseHistoryOrderResultSet(rs);
                historyOrder.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyOrder;
    }
}
