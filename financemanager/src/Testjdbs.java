import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Testjdbs {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/finance_db";
        String user = "root";
        String password = "myroot";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ✅ Force-load the driver
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to the database successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Connection failed!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(" JDBC Driver not found!");
            e.printStackTrace();
        }
    }
}
