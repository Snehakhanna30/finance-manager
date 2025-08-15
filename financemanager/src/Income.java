import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Income {
    private double amount;
    private String source;
    private String date;

    public Income(double amount, String source, String date) {
        this.amount = amount;
        this.source = source;
        this.date = date;
    }

    public boolean saveToDB() {
        String sql = "INSERT INTO income (amount, source, date) VALUES (?, ?, ?)";
        try (Connection conn = Databasemanager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, amount);
            pstmt.setString(2, source);
            pstmt.setString(3, date);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error saving income: " + e.getMessage());
            return false;
        }
    }

    public void insertIncome() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter source: ");
        String source = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Income income = new Income(amount, source, date);
        if (income.saveToDB()) {
            System.out.println("✅ Income inserted successfully.");
        } else {
            System.out.println("❌ Failed to insert income.");
        }
    }

    // ✅ Moved this method inside the class
    public static void displayAll() {
        String sql = "SELECT * FROM income";
        try (Connection conn = Databasemanager.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            System.out.println("📋 Income Records:");
            System.out.println("----------------------------");

            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String source = rs.getString("source");
                String date = rs.getString("date");

                System.out.println("Amount: " + amount);
                System.out.println("Source: " + source);
                System.out.println("Date: " + date);
                System.out.println("----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching income records: " + e.getMessage());
        }
    }
}
