import java.sql.*;
import java.util.Scanner;

public class ViewExpenses {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/finance_db";
        String user = "root";
        String password = "your_password"; // ← put your correct password

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter start date (yyyy-mm-dd): ");
            String startDate = scanner.nextLine();

            System.out.print("Enter end date (yyyy-mm-dd): ");
            String endDate = scanner.nextLine();

            String query = "SELECT * FROM expenses WHERE date BETWEEN ? AND ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nExpenses between " + startDate + " and " + endDate + ":");
            while (rs.next()) {
                System.out.println(
                        rs.getString("date") + " - ₹" + rs.getDouble("amount") + " - " + rs.getString("description"));
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to fetch expenses.");
            e.printStackTrace();
        }
    }
}
