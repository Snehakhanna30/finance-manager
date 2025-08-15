
import java.util.Scanner;

public class AddIncome {
    public static void handle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter income amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter income source: ");
        String source = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // You may add validation here if needed

        Income income = new Income(amount, source, date);
        income.insertIncome();
    }
}
