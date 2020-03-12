package db;

import entity.Route;
import entity.RouteInfo;
import utility.RandomString;

import java.sql.PreparedStatement;
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
            for (RouteInfo i : routes) {
                String sql = "INSERT INTO routes VALUES(?, ?, ?, ?)";

                PreparedStatement stmt = conn.prepareStatement(sql);
                String route_id = "RT_" + RandomString.getAlphaNumericString(7);
                i.setRouteID(route_id);

                stmt.setString(1, route_id);
                stmt.setString(2, orderID);
                stmt.setString(3, i.getEncodedPolyline());
                stmt.setString(4, i.getDeliverType());

                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
