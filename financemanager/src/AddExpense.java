public class AddExpense {
    public static void handle() {
        try {
            Expense.displayAll();
        } catch (Exception e) {
            System.out.println("Error displaying expenses: " + e.getMessage());
        }
    }
}
