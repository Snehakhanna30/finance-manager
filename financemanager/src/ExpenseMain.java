import java.util.Scanner;

public class ExpenseMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter expense category: ");
        String category = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = 0;
        try {
            amount = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount format!");
            return;
        }

        System.out.print("Enter date (yyyy-mm-dd): ");
        String date = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        Expense expense = new Expense(amount, category, date, description);
        if (expense.saveToDB()) {
            System.out.println("✅ Expense saved!");
        } else {
            System.out.println("❌ Failed to save expense.");
        }

        sc.close();
    }

    public static void inputExpense() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputExpense'");
    }
}
