import java.sql.Connection;
import java.sql.PreparedStatement;

public class Expense {
    private double amount;
    private String category;
    private String description;
    private String date;

    // Constructor with 3 parameters (description empty)
    public Expense(double amount, String category, String date) {
        this.amount = amount;
        this.category = category;
        this.description = "";
        this.date = date;
    }

    // Constructor with 4 parameters (overloaded)
    public Expense(double amount, String category, String description, String date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public void insertExpense() {
        try (Connection conn = Databaseconnection.getConnection()) {
            String sql = "INSERT INTO expenses (amount, category, description, date) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, amount);
            stmt.setString(2, category);
            stmt.setString(3, description);
            stmt.setString(4, date);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Expense added successfully.");
            } else {
                System.out.println("Failed to add expense.");
            }
        } catch (Exception e) {
            System.out.println("Error inserting expense: " + e.getMessage());
        }
    }

    public boolean saveToDB() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveToDB'");
    }

    public static void displayAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayAll'");
    }
}
