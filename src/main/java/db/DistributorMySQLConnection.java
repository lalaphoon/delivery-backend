package db;

import java.sql.PreparedStatement;
import entity.Order;
import org.json.JSONObject;
import rpc.OrderRpcHelper;
import utility.RandomString;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistributorMySQLConnection extends MySQLConnection {

    private String generateSQL(String distriId) {
        return "SELECT * FROM " + distriId + " WHERE type = ? ";
    }

    public int getDroneCounter(String distribution) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return 0;
        }
        int counter = 0;
        try {
            String sql = generateSQL(distribution);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "DRONE");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                counter++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }

    public int getRobotCounter(String distribution) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return 0;
        }
        int counter = 0;
        try {
            String sql = generateSQL(distribution);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "ROBOT");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                counter++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }

}
