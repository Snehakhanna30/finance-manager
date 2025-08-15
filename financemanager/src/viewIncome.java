public class viewIncome {
    public static void displayIncome() {
        try {
            Income.displayAll();
        } catch (Exception e) {
            System.out.println("Error displaying income: " + e.getMessage());
        }
    }
}
