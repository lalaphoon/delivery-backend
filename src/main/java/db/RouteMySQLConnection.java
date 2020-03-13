package db;

import entity.Route;
import entity.RouteInfo;
import org.json.JSONObject;
import utility.RandomString;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteMySQLConnection extends MySQLConnection {
    public void insertRouteRecord(Route route, String orderID) {

        if (conn == null) {
            System.err.println("DB connection failed");
            return;
        }

        try {
            List<RouteInfo> routes = route.getRoutes();
            int index = 0;
            for (RouteInfo i : routes) {
                String sql = "INSERT INTO routes VALUES(?, ?, ?, ?, ?)";

                PreparedStatement stmt = conn.prepareStatement(sql);
                String route_id = "RT_" + RandomString.getAlphaNumericString(7);
                i.setRouteID(route_id);

                stmt.setString(1, route_id);
                stmt.setString(2, orderID);
                stmt.setString(3, i.getEncodedPolyline());
                stmt.setString(4, i.getDeliverType());
                stmt.setDouble(5, i.getPrice());

                stmt.execute();
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject findRouteByID(String route_id) {
        JSONObject obj = new JSONObject();
        if (conn == null) {
            System.err.println("DB connection failed");
            return null;
        }

        try {
            String sql = "SELECT * FROM routes WHERE route_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, route_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                obj.put("route", rs.getString("route"));
                obj.put("deliver_type", rs.getString("type"));
                obj.put("price", rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
