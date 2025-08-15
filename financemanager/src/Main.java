import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        // try {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        // System.out.println("Driver loaded!");
        // } catch (ClassNotFoundException e) {
        // System.out.println("Driver not found!");
        // }
        Scanner scanner = new Scanner(System.in);
        FinanceManagerMain fm = new FinanceManagerMain();
        Income income = new Income(0, "", ""); // dummy constructor for calling insert
        income.insertIncome();

        Income.displayAll();

        System.out.println("What do you want to do? (edit/delete): ");
        String action = scanner.nextLine().trim().toLowerCase();
        System.out.println("You selected: " + action);

        System.out.println("Is it for income or expense? ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter record ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (action.equals("edit")) {
            if (type.equals("income")) {
                System.out.print("Enter new amount: ");
                double amount = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter new source: ");
                String source = scanner.nextLine();
                System.out.print("Enter new date (yyyy-mm-dd): ");
                String date = scanner.nextLine();

                fm.editIncomeRecord(id, amount, source, date);
            } else if (type.equals("expense")) {
                System.out.print("Enter new amount: ");
                double amount = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter new category: ");
                String category = scanner.nextLine();
                System.out.print("Enter new date (yyyy-mm-dd): ");
                String date = scanner.nextLine();
                System.out.print("Enter new description: ");
                String description = scanner.nextLine();

                fm.editExpenseRecord(id, amount, category, date, description);
            } else {
                System.out.println("Invalid type!");
            }
        } else if (action.equals("delete")) {
            if (type.equals("income")) {
                fm.deleteIncomeRecord(id);
            } else if (type.equals("expense")) {
                fm.deleteExpenseRecord(id);
            } else {
                System.out.println("Invalid type!");
            }
        } else {
            System.out.println("Invalid action!");
        }
        if (Databasemanager.getConnection() != null) {
            System.out.println("✅ Connected to database successfully.");
        } else {
            System.out.println("❌ Connection failed.");
        }

        scanner.close();
    }
}
