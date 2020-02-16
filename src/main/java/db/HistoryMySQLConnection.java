package db;


import entity.Order;
import org.json.JSONObject;
import rpc.OrderRpcHelper;

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

           String sql = "INSERT INTO history VALUES('OR00004', '1111' , 'M4W1W3', 'M4W1W3', 'DR003', '2005-10-30 T 10:45 UTC', '2005-10-30 T 10:45 UTC', '24.5', '23.00')";
            // Todo : Change this sql to actually read an obj.
           //statement.executeUpdate(sql);
           PreparedStatement stmt = conn.prepareStatement(sql);
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
