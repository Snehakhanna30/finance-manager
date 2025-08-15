import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reportgenerator {

    public void generateMonthlyReport(int month, int year) {
        Connection conn = null;
        PreparedStatement incomeStmt = null;
        PreparedStatement expenseStmt = null;
        ResultSet incomeRs = null;
        ResultSet expenseRs = null;

        System.out.println("\n========== Monthly Report ==========");
        System.out.printf("Month: %d, Year: %d\n", month, year);

        try {
            conn = Databasemanager.getConnection();

            // Show Income Details
            String incomeQuery = "SELECT id, amount, source, date FROM income WHERE MONTH(date) = ? AND YEAR(date) = ?";
            incomeStmt = conn.prepareStatement(incomeQuery);
            incomeStmt.setInt(1, month);
            incomeStmt.setInt(2, year);
            incomeRs = incomeStmt.executeQuery();

            System.out.println("\n--- Income Records ---");
            while (incomeRs.next()) {
                int id = incomeRs.getInt("id");
                double amount = incomeRs.getDouble("amount");
                String source = incomeRs.getString("source");
                String date = incomeRs.getString("date");

                System.out.printf("ID: %d | Amount: ₹%.2f | Source: %s | Date: %s\n", id, amount, source, date);
            }

            // Show Expense Details
            String expenseQuery = "SELECT id, amount, category, description, date FROM expenses WHERE MONTH(date) = ? AND YEAR(date) = ?";
            expenseStmt = conn.prepareStatement(expenseQuery);
            expenseStmt.setInt(1, month);
            expenseStmt.setInt(2, year);
            expenseRs = expenseStmt.executeQuery();

            System.out.println("\n--- Expense Records ---");
            while (expenseRs.next()) {
                int id = expenseRs.getInt("id");
                double amount = expenseRs.getDouble("amount");
                String category = expenseRs.getString("category");
                String description = expenseRs.getString("description");
                String date = expenseRs.getString("date");

                System.out.printf("ID: %d | Amount: ₹%.2f | Category: %s | Description: %s | Date: %s\n",
                        id, amount, category, description, date);
            }

            System.out.println("\n========== End of Report ==========");

        } catch (SQLException e) {
            System.out.println("⚠️ Error generating report: " + e.getMessage());
        } finally {
            try {
                if (incomeRs != null)
                    incomeRs.close();
            } catch (Exception e) {
            }
            try {
                if (expenseRs != null)
                    expenseRs.close();
            } catch (Exception e) {
            }
            try {
                if (incomeStmt != null)
                    incomeStmt.close();
            } catch (Exception e) {
            }
            try {
                if (expenseStmt != null)
                    expenseStmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }
}
