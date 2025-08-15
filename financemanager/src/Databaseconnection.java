import java.sql.Connection;
import java.sql.DriverManager;

public class Databaseconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/finance_db?useSSL=false&serverTimezone=UTC"; // aapka
                                                                                                                // DB
                                                                                                                // URL
    private static final String USER = "root"; // aapka MySQL username
    private static final String PASSWORD = "myroot"; // aapka MySQL password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL driver load karna zaroori hai
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
