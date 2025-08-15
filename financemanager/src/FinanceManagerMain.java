import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FinanceManagerMain {
    private static final Scanner sc = new Scanner(System.in); // ✅ Fixed: was null before

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Personal Finance Manager");

        FinanceManagerMain fm = new FinanceManagerMain();

        // Show summary for May 2025
        fm.showMonthlySummary(5, 2025);
        fm.editIncomeRecord(1, 8000, "Part-time", "2025-05-20");
        fm.deleteIncomeRecord(2);
        fm.editExpenseRecord(3, 1500, "Food", "2025-05-21", "Snacks & lunch");
        fm.deleteExpenseRecord(4);

        while (running) {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Edit Income Record");
            System.out.println("4. Edit Expense Record");
            System.out.println("5. Delete Income Record");
            System.out.println("6. Delete Expense Record");
            System.out.println("7. Show Monthly Summary");
            System.out.println("8. Show Report");
            System.out.println("9. Set Monthly Budget");

            System.out.println("10. Exit");
            System.out.print("👉 Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left after nextInt()

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount: ");
                    double incomeAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter source: ");
                    String source = scanner.nextLine();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String incomeDate = scanner.nextLine();

                    Income income = new Income(incomeAmount, source, incomeDate);
                    income.insertIncome();
                }

                case 2 -> {
                    System.out.print("Enter amount: ");
                    double expenseAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String expenseDate = scanner.nextLine();

                    Expense expense = new Expense(expenseAmount, category, description, expenseDate);
                    expense.insertExpense();
                }

                case 3 -> {
                    System.out.print("Enter income ID to edit: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter new source: ");
                    String newSource = scanner.nextLine();

                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();

                    fm.editIncomeRecord(id, newAmount, newSource, newDate);
                }

                case 4 -> {
                    System.out.print("Enter expense ID to edit: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();

                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    System.out.print("Enter new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();

                    fm.editExpenseRecord(id, newAmount, newCategory, newDate, newDescription);
                }

                case 5 -> {
                    System.out.print("Enter income ID to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    fm.deleteIncomeRecord(id);
                }

                case 6 -> {
                    System.out.print("Enter expense ID to delete: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    fm.deleteExpenseRecord(id);
                }

                case 7 -> {
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year (e.g., 2025): ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    fm.showMonthlySummary(month, year);
                }
                case 8 -> {
                    System.out.println("=== Generate Monthly Report ===");
                    System.out.print("Enter month (1-12): ");
                    int reportMonth = sc.nextInt();

                    System.out.print("Enter year (e.g. 2025): ");
                    int reportYear = sc.nextInt();
                    sc.nextLine(); // Consume the newline

                    Reportgenerator reportGen = new Reportgenerator();
                    reportGen.generateMonthlyReport(reportMonth, reportYear);
                    break;

                }
                case 9 -> {
                    System.out.print("Enter month (1-12): ");
                    int bMonth = scanner.nextInt();
                    System.out.print("Enter year (e.g., 2025): ");
                    int bYear = scanner.nextInt();
                    System.out.print("Enter monthly budget amount: ");
                    double bAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    fm.setBudget(bMonth, bYear, bAmount);
                }

                case 10 -> {
                    System.out.println("👋 Thank you for using the Finance Manager!");
                    running = false;
                }

                default -> System.out.println("⚠️ Invalid choice. Try again.");
            }
        }

        scanner.close();
        sc.close(); // ✅ Close the static scanner too
    }

    public void showMonthlySummary(int month, int year) throws SQLException {
        Connection conn = null;
        PreparedStatement stmtIncome = null;
        PreparedStatement stmtExpense = null;
        ResultSet rsIncome = null;
        ResultSet rsExpense = null;

        double totalIncome = 0;
        double totalExpense = 0;

        try {
            conn = Databaseconnection.getConnection();

            // ✅ Added null check
            if (conn == null) {
                System.out.println("Cannot show monthly summary - database connection failed");
                return;
            }

            // Get total income
            String incomeQuery = "SELECT SUM(amount) FROM income WHERE MONTH(date) = ? AND YEAR(date) = ?";
            stmtIncome = conn.prepareStatement(incomeQuery);
            stmtIncome.setInt(1, month);
            stmtIncome.setInt(2, year);
            rsIncome = stmtIncome.executeQuery();
            if (rsIncome.next()) {
                totalIncome = rsIncome.getDouble(1);
            }

            // Get total expense
            String expenseQuery = "SELECT SUM(amount) FROM expenses WHERE MONTH(date) = ? AND YEAR(date) = ?";
            stmtExpense = conn.prepareStatement(expenseQuery);
            stmtExpense.setInt(1, month);
            stmtExpense.setInt(2, year);
            rsExpense = stmtExpense.executeQuery();
            if (rsExpense.next()) {
                totalExpense = rsExpense.getDouble(1);
            }

            // Show summary
            System.out.println("----- Monthly Summary -----");
            System.out.println("Month: " + month + ", Year: " + year);
            System.out.printf("Total Income:  ₹%.2f\n", totalIncome);
            System.out.printf("Total Expenses: ₹%.2f\n", totalExpense);
            System.out.printf("Net Savings:    ₹%.2f\n", (totalIncome - totalExpense));
            System.out.println("----------------------------");
            // Show budget
            String budgetQuery = "SELECT amount FROM budget WHERE month = ? AND year = ?";
            PreparedStatement budgetStmt = conn.prepareStatement(budgetQuery);
            budgetStmt.setInt(1, month);
            budgetStmt.setInt(2, year);
            ResultSet rsBudget = budgetStmt.executeQuery();

            if (rsBudget.next()) {
                double budgetAmount = rsBudget.getDouble("amount");
                System.out.printf("Budget for this month: ₹%.2f\n", budgetAmount);
                if (totalExpense > budgetAmount) {
                    System.out.println("⚠️ You have exceeded your budget!");
                } else {
                    System.out.println("✅ You are within your budget.");
                }
            } else {
                System.out.println("ℹ️ No budget set for this month.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rsIncome != null)
                    rsIncome.close();
            } catch (Exception e) {
            }
            try {
                if (rsExpense != null)
                    rsExpense.close();
            } catch (Exception e) {
            }
            try {
                if (stmtIncome != null)
                    stmtIncome.close();
            } catch (Exception e) {
            }
            try {
                if (stmtExpense != null)
                    stmtExpense.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        } // Show budget
          // String budgetQuery = "SELECT amount FROM budget WHERE month = ? AND year =
          // ?";
          // PreparedStatement budgetStmt = conn.prepareStatement(budgetQuery);
          // budgetStmt.setInt(1, month);
          // budgetStmt.setInt(2, year);
          // ResultSet rsBudget = budgetStmt.executeQuery();

        // if (rsBudget.next()) {
        // double budgetAmount = rsBudget.getDouble("amount");
        // System.out.printf("Budget for this month: ₹%.2f\n", budgetAmount);
        // if (totalExpense > budgetAmount) {
        // System.out.println("⚠️ You have exceeded your budget!");
        // } else {
        // System.out.println("✅ You are within your budget.");
        // }
        // } else {
        // System.out.println("ℹ️ No budget set for this month.");
        // }
        // if (rsBudget != null)
        // rsBudget.close();
        // if (budgetStmt != null)
        // budgetStmt.close();
        // if (rsIncome != null)
        // rsIncome.close();
        // if (rsExpense != null)
        // rsExpense.close();
        // if (stmtIncome != null)
        // stmtIncome.close();
        // if (stmtExpense != null)
        // stmtExpense.close();
        // if (conn != null)
        // conn.close();

    }

    public void editIncomeRecord(int id, double newAmount, String newSource, String newDate) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Databaseconnection.getConnection();

            // ✅ Added null check
            if (conn == null) {
                System.out.println("Cannot edit income record - database connection failed");
                return;
            }

            String sql = "UPDATE income SET amount = ?, source = ?, date = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, newAmount);
            stmt.setString(2, newSource);
            stmt.setDate(3, java.sql.Date.valueOf(newDate)); // ✅ convert String to java.sql.Date
            stmt.setInt(4, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Income record updated successfully.");
            } else {
                System.out.println("Income record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace(); // Shows detailed error
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    public void deleteIncomeRecord(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Databaseconnection.getConnection();

            // ✅ Added null check
            if (conn == null) {
                System.out.println("Cannot delete income record - database connection failed");
                return;
            }

            String sql = "DELETE FROM income WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Income record deleted successfully.");
            } else {
                System.out.println("Income record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    public void editExpenseRecord(int id, double newAmount, String newCategory, String newDate, String newDescription) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Databaseconnection.getConnection();

            // ✅ Added null check
            if (conn == null) {
                System.out.println("Cannot edit expense record - database connection failed");
                return;
            }

            String sql = "UPDATE expenses SET amount = ?, category = ?, date = ?, description = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, newAmount);
            stmt.setString(2, newCategory);
            stmt.setDate(3, java.sql.Date.valueOf(newDate));
            stmt.setString(4, newDescription);
            stmt.setInt(5, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Expense record updated successfully.");
            } else {
                System.out.println("Expense record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    public void setBudget(int month, int year, double amount) {
        Connection conn = Databaseconnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed.");
            return;
        }

        try {
            // Check if budget already exists
            String checkQuery = "SELECT * FROM budget WHERE month = ? AND year = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, month);
            checkStmt.setInt(2, year);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Update existing budget
                String update = "UPDATE budget SET amount = ? WHERE month = ? AND year = ?";
                PreparedStatement updateStmt = conn.prepareStatement(update);
                updateStmt.setDouble(1, amount);
                updateStmt.setInt(2, month);
                updateStmt.setInt(3, year);
                updateStmt.executeUpdate();
                System.out.println("✅ Budget updated for " + month + "/" + year);
            } else {
                // Insert new budget
                String insert = "INSERT INTO budget (month, year, amount) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insert);
                insertStmt.setInt(1, month);
                insertStmt.setInt(2, year);
                insertStmt.setDouble(3, amount);
                insertStmt.executeUpdate();
                System.out.println("✅ Budget set for " + month + "/" + year);
            }
        } catch (SQLException e) {
            System.out.println("Error setting budget: " + e.getMessage());
        }
    }

    public void deleteExpenseRecord(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Databaseconnection.getConnection();

            // ✅ Added null check
            if (conn == null) {
                System.out.println("Cannot delete expense record - database connection failed");
                return;
            }

            String sql = "DELETE FROM expenses WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Expense record deleted successfully.");
            } else {
                System.out.println("Expense record not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
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