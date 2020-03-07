package db;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;


public class MySQLTableCreation {
    // Run this as Java application to reset db schema.
    public static void main(String[] args) {
        try {
            // Step 1 Connect to MySQL.
            System.out.println("Connecting to " + MySQLDBUtil.URL);
            Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
            Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

            if (conn == null) {
                return;
            }
            // Step 2 Drop tables in case they exist.
            Statement statement = conn.createStatement();
            String sql = "DROP TABLE IF EXISTS categories";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS history"; // all orders has been done
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS orders"; // on going order
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS A";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS B";
            statement.executeUpdate(sql);

            sql = "DROP TABLE IF EXISTS C";
            statement.executeUpdate(sql);


            // Step 3 Create new tables
            sql = "CREATE TABLE users ("
                    + "user_id VARCHAR(255) NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "first_name VARCHAR(255),"
                    + "last_name VARCHAR(255),"
                    + "phone VARCHAR(255),"
                    + "PRIMARY KEY (user_id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE orders ("
                    + "order_id VARCHAR(255) NOT NULL,"
                    + "user_id VARCHAR(255),"
                    + "location_from VARCHAR(255),"
                    + "location_to VARCHAR(255),"
                    + "drone_id VARCHAR(255),"
                   // + "time_start TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "time_start VARCHAR(255),"
                    + "total_weight FLOAT,"
                    + "price FLOAT,"
                    + "PRIMARY KEY (order_id),"
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE history ("
                    + "order_id VARCHAR(255) NOT NULL,"
                    + "user_id VARCHAR(255),"
                    + "location_from VARCHAR(255),"
                    + "location_to VARCHAR(255),"
                    + "drone_id VARCHAR(255),"
                   // + "time_start TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                   // + "time_end TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
                    + "time_start VARCHAR(255),"
                    + "time_end VARCHAR(255),"
                    + "total_weight FLOAT,"
                    + "price FLOAT,"
                    + "PRIMARY KEY (order_id),"
                    + "FOREIGN KEY (user_id) REFERENCES users(user_id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE A ("
                    + "deliver_id VARCHAR(255) NOT NULL,"
                    + "type VARCHAR(255),"
                    + "state VARCHAR(255),"
                    + "PRIMARY KEY (deliver_id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE B ("
                    + "deliver_id VARCHAR(255) NOT NULL,"
                    + "type VARCHAR(255),"
                    + "state VARCHAR(255),"
                    + "PRIMARY KEY (deliver_id)"
                    + ")";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE C ("
                    + "deliver_id VARCHAR(255) NOT NULL,"
                    + "type VARCHAR(255),"
                    + "state VARCHAR(255),"
                    + "PRIMARY KEY (deliver_id)"
                    + ")";
            statement.executeUpdate(sql);


            // Step 4: insert fake user 1111/3229c1097c00d497a0fd282d586be050
            sql = "INSERT INTO users VALUES('1111', '3229c1097c00d497a0fd282d586be050', 'John', 'Smith', '416-111-1111')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO history VALUES('OR00001', '1111' , 'M4W1W3', 'M4W1W3', 'DR001', '2005-10-30 T 10:45 UTC', '2005-10-30 T 10:45 UTC', '13.5', '12.00')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO history VALUES('OR00002', '1111' , 'M4W1W3', 'M4W1W3', 'DR010', '2005-10-30 T 10:45 UTC', '2005-10-30 T 10:45 UTC', '14.5', '45.00')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO history VALUES('OR00003', '1111' , 'M4W1W3', 'M4W1W3', 'DR003', '2005-10-30 T 10:45 UTC', '2005-10-30 T 10:45 UTC', '24.5', '23.00')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO A VALUES('DRONE_dfjaweifjsf', 'DRONE' , 'free')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO A VALUES('DRONE_dfjaweasdfa', 'DRONE' , 'free')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO A VALUES('ROBOT_rdfasdjfjwf', 'ROBOT' , 'free')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO A VALUES('ROBOT_rdfaasdfafs', 'ROBOT' , 'free')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO B VALUES('ROBOT_rdfaasghjfs', 'ROBOT' , 'free')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO B VALUES('ROBOT_rdfaafgjjfh', 'ROBOT' , 'free')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO B VALUES('ROBOT_rdf34tfhhfh', 'ROBOT' , 'free')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO B VALUES('DRONE_rdfgghfhjrf', 'DRONE' , 'free')";
            statement.executeUpdate(sql);

            sql = "INSERT INTO C VALUES('DRONE_rdSDFASDFA', 'DRONE' , 'free')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO C VALUES('DRONE_jlkfjgkjeg', 'DRONE' , 'free')";
            statement.executeUpdate(sql);
            sql = "INSERT INTO C VALUES('ROBOT_fdgkljskon', 'ROBOT' , 'free')";
            statement.executeUpdate(sql);

            conn.close();
            System.out.println("Import done successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
