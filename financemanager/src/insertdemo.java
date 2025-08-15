import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertdemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/finance_db";
        String user = "root";
        String password = "myroot"; // 🔁 Replace this with your real MySQL password

        String sql = "INSERT INTO income (source, amount, date) VALUES (?, ?, ?)";

        try {
            // Step 1: Load the driver (optional for newer versions, but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Connect
            Connection conn = DriverManager.getConnection(url, user, password);

            // Step 3: Prepare the insert
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Freelancing");
            stmt.setDouble(2, 5000.00);
            stmt.setDate(3, java.sql.Date.valueOf("2025-05-07"));

            // Step 4: Execute
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Data inserted successfully into income table!");
            }

            // Step 5: Close
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
